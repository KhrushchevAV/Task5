package study.stepup.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.stepup.entities.Agreement;
import study.stepup.entities.Tpp_product;
import study.stepup.entities.Tpp_product_register;
import study.stepup.entities.Tpp_ref_product_register_type;
import study.stepup.exceptions.BadRequestException;
import study.stepup.exceptions.NotFoundException;
import study.stepup.model.InstanceArrangement;
import study.stepup.model.InstanceRequestBody;
import study.stepup.repositories.AgreementRepo;
import study.stepup.repositories.Tpp_productRepo;
import study.stepup.repositories.Tpp_product_registerRepo;
import study.stepup.repositories.Tpp_ref_product_register_typeRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class InstanceCreateService {
    private InstanceRequestBody requestBody;
    @Autowired
    private Tpp_productRepo tppProductRepo;
    @Autowired
    private AgreementRepo agreementRepo;
    @Autowired
    private Tpp_ref_product_register_typeRepo tppRefProductRegisterTypeRepo;
    @Autowired
    private Tpp_product_registerRepo tppProductRegisterRepo;

    // создаваемый/редактируемый ЭП
    private Tpp_product tppProduct;

    public void setRequestBody(InstanceRequestBody requestBody) {
        this.requestBody = requestBody;
    }


    public void check() {
        /*
        * Многолетний опыт в ЦФТ подсказывает всю бизнес логику реализовать в базе средствами PostgreSQL
        * отсюда только вызвать и показать пользователю результат/исключение.
        * Делать это на джаве, ну СИЛЬНО мгногословнее.
        * Например, чтобы проверить поле на не null, достаточно при создании таблицы уназать NOT NULL
        * и всё ноль строк кода, но никакими средстваи туда null уже не записать.
        * Чтобы "проверить" на дубли - соотвественно UNIQUE
        * и всё СУБД сама проверит и выдаст правильный эксэпшн.
        * В учебной задаче по курсу Java, еще можно понять... Но, похоже, так и в реальной жизни мучаются...
        * :-(
        * */

        // 1. обязательность полей
        validate();

        // 2. создание нового экземпляра
        if (requestBody.instanceId == null) {

            // 1.1 Проверка таблицы ЭП (tpp_product) на дубли.
            List<Tpp_product> lp = tppProductRepo.findByNumber(requestBody.contractNumber);
            if (lp.size() > 0) {
                throw new BadRequestException("Параметр ContractNumber № договора " + requestBody.contractNumber + " уже существует");
            }

            // 1.2 Проверка таблицы ДС (agreement) на дубли
            for(InstanceArrangement ia : requestBody.instanceArrangement) {
                List<Agreement> la = agreementRepo.findByNumber(ia.number);
                if (la.size() > 0) {
                    throw new BadRequestException("Параметр № Дополнительного соглашения (сделки) Number "+ia.number+" уже существует");
                }
            }

            // 1.3 По КодуПродукта найти связные записи в Каталоге Типа регистра
            // Request.Body.ProductCode == tpp_ref_product_class.value
            // среди найденных записей отобрать те, у которых
            // tpp_ref_product_register_type.account_type имеет значение  “Клиентский”
            List<Tpp_ref_product_register_type> tppRefProductRegisterTypes = tppRefProductRegisterTypeRepo.getByCodeCl(requestBody.productCode);
            if (tppRefProductRegisterTypes.size() < 1) {
                throw new NotFoundException("КодПродукта "+requestBody.productCode+" не найдено в Каталоге продуктов tpp_ref_product_class");
            }

            // 1.4
            // Добавить строку в таблицу tpp_product, заполнить согласно Request.Body:
            // Сформировать/Запомнить новый ИД ЭП tpp_product.id,
            tppProduct = new Tpp_product();
            tppProduct.id = null;
            tppProduct.product_code_id = null;
            tppProduct.client_id = Long.valueOf(requestBody.mdmCode);
            tppProduct.type = null;
            tppProduct.number = requestBody.contractNumber;
            tppProduct.priority = requestBody.priority;
            tppProduct.date_of_conclusion = requestBody.contractDate;
            tppProduct.start_date_time = requestBody.contractDate;
            tppProduct.end_date_time = null;
            tppProduct.days = null;
            tppProduct.penalty_rate = requestBody.interestRatePenalty;
            tppProduct.nso = null;
            tppProduct.threshold_amount = requestBody.thresholdAmount;
            tppProduct.requisite_type = requestBody.registerType;
            tppProduct.interest_rate_type = requestBody.rateType;
            tppProduct.tax_rate = requestBody.taxPercentageRate;
            tppProduct.reasone_close = null;
            tppProduct.state = null;
            tppProductRepo.save(tppProduct);

            // Шаг 1.5
            // Добавить в таблицу ПР (tpp_product_registry) строки, заполненные:
            // •	Id - ключ таблицы
            // •	product_id - ссылка на таблицу ЭП, где tpp_product.id  == tpp_product_registry.product_id
            // •	type – тип ПР (лицевого/внутрибанковского счета)
            // •	account_id – ид счета
            // •	currency_code – код валюты счета
            // •	state – статус счета, enum (0, Закрыт/1, Открыт/2, Зарезервирован/3, Удалён)
            //            (см. задачу на создание продуктового регистра по методу corporate-settlement-account/create)
            for(Tpp_ref_product_register_type rt : tppRefProductRegisterTypes) {
                Tpp_product_register register = new Tpp_product_register();
                register.product_id = tppProduct.id;
                register.type = rt.value;
                register.account = null;
                register.currency_code = requestBody.isoCurrencyCode;
                register.state = "2";
                register.account_number = null;
                tppProductRegisterRepo.save(register);
            }
        }

        // изменяется состав ДС // сделка (доп.Соглашение)
        // requestBody.instanceId != null
        else {

            // Шаг 2.1
            // Проверка таблицы ЭП (tpp_product) на существование ЭП. Для этого необходимо отобрать строки по условию
            // tpp_product.id == Request.Body. instanceId
            tppProduct = tppProductRepo.getReferenceById(requestBody.instanceId);
            if (tppProduct == null) {
                // Если запись не найдена
                //•	вернуть Статус: 404/Not Found, Текст: Экземпляр продукта с параметром instanceId <значение> не найден.
                throw new NotFoundException("Экземпляр продукта с параметром instanceId "+requestBody.instanceId+" не найден.");
            }

            // Шаг 2.2
            // Проверка таблицы ДС (agreement) на дубли
            // Отобрать записи по условию agreement.number == Request.Body.Arrangement[N].Number
            // Если записи найдены
            // •	вернуть Статус: 400/Bad Request, Текст: Параметр № Дополнительного соглашения (сделки) Number <значение> уже существует для ЭП с ИД  <значение1>.
            for (InstanceArrangement ia : requestBody.instanceArrangement) {
                List<Agreement> agrList = agreementRepo.findByNumber(ia.number);
                if (agrList.size() > 0) {
                    throw new BadRequestException("Параметр № Дополнительного соглашения (сделки) Number <значение> уже существует для ЭП с ИД "+ia.number);
                }
            }

            // Шаг 8.
            //•	Добавить строку в таблицу ДС (agreement)
            //•	заполнить соотв. поля ДС согласно составу Request.Body, см. массив Arrangement[…]
            //•	сформировать agreement.Id , связать с таблицей ЭП по ИД ЭП, где tpp_product.id  == agreement.product_id
            for (InstanceArrangement ia : requestBody.instanceArrangement) {
                Agreement agr = new Agreement();
                agr.product_id = tppProduct.id;
                agr.general_agreement_id = ia.generalAgreementId;
                agr.supplementary_agreement_id = ia.supplementaryAgreementId;
                agr.arrangement_type = ia.arrangementType;
                agr.sheduler_job_id = ia.schedulerJobId;
                agr.number = ia.number;
                agr.opening_date = ia.openingDate;
                agr.closing_date = ia.closingDate;
                agr.cancel_date = ia.cancelDate;
                agr.validity_duration = ia.validityDuration;
                agr.cancellation_reason = ia.cancellationReason;
                agr.status = ia.status;
                agr.interest_calculation_date = ia.interestCalculationDate;
                agr.interest_rate = ia.interestRate;
                agr.coefficient = ia.coefficient;
                agr.coefficient_action = ia.coefficientAction;
                agr.minimum_interest_rate = ia.minimumInterestRate;
                agr.minimum_interest_rate_coefficient = ia.maximalInterestRateCoefficient;
                agr.minimum_interest_rate_coefficient_action = ia.minimumInterestRateCoefficientAction;
                agr.maximal_interest_rate = ia.maximalInterestRate;
                agr.maximal_interest_rate_coefficient = ia.maximalInterestRateCoefficient;
                agr.maximal_interest_rate_coefficient_action = ia.minimumInterestRateCoefficientAction;
                agreementRepo.save(agr);
            }

        }
    }


    // проверка на обязательность по аннотациям @NotNull
    void validate() {
        List<String> errLst = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // requestBody
        Set<ConstraintViolation<InstanceRequestBody>> violations = validator.validate(requestBody);
        if(!violations.isEmpty())
            for (ConstraintViolation<InstanceRequestBody> violation : violations) {
                if (violation.getMessage().contains("blank") || violation.getMessage().contains("null"))
                    errLst.add("Не заполнен обязательны параметр: " + violation.getPropertyPath().toString());
            }

        // массив instanceArrangement
        for (InstanceArrangement ia : requestBody.instanceArrangement) {
            Set<ConstraintViolation<InstanceArrangement>> violationsIA = validator.validate(ia);
                if(!violationsIA.isEmpty())
                    for (ConstraintViolation<InstanceArrangement> violation : violationsIA) {
                        if (violation.getMessage().contains("blank") || violation.getMessage().contains("null"))
                            errLst.add("Не заполнен обязательны параметр: " + violation.getPropertyPath().toString());
                    }
        }

        // были ошибки
        if (!errLst.isEmpty())
            throw new BadRequestException(errLst.toString());
    }


    public Object createAnswer() {
        HashMap<String,Object> responseBody = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        data.put("instanceId", tppProduct.id);
        data.put("registerId", tppProductRegisterRepo.getRegisterIdList(tppProduct.id));
        data.put("supplementaryAgreementId", agreementRepo.getAgreementIdList(tppProduct.id));
        responseBody.put("data", data);
        return  responseBody;
    }

    public Object execute() {
        check();
        return createAnswer();
    }
}
