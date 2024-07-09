package study.stepup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.stepup.entities.Account;
import study.stepup.entities.Account_pool;
import study.stepup.entities.Tpp_product_register;
import study.stepup.entities.Tpp_ref_product_register_type;
import study.stepup.exceptions.BadRequestException;
import study.stepup.exceptions.NotFoundException;
import study.stepup.model.ProductRegister;
import study.stepup.repositories.AccountRepo;
import study.stepup.repositories.Account_poolRepo;
import study.stepup.repositories.Tpp_product_registerRepo;
import study.stepup.repositories.Tpp_ref_product_register_typeRepo;

import java.util.HashMap;

@Service
public class ProductRegisterService {
    private ProductRegister requestBody;

    @Autowired
    Tpp_product_registerRepo prRepo;

    @Autowired
    Tpp_ref_product_register_typeRepo tppRefProductRegisterTypeRepo;
    Tpp_ref_product_register_type prType;

    @Autowired
    AccountRepo accountRepo;
    Account account;

    @Autowired
    Account_poolRepo accountPoolRepo;
    Account_pool accountPool;

    public void setProductRegister(ProductRegister pr) {
        this.requestBody = pr;
    }

    public void check() {
        /*
        Очень просится все такие проверки на дубликаты и связанные таблицы реализовать
        прямо в базе на PostgreSQL. Это было бы лаконичнее и оптимальнее!
        А сервис просто вызвал бы хранимую процедуру.
        */

        // 1 обязательность
        if (requestBody.instanceId == null) {
            throw new NotFoundException("Не заполнено обязательное поле instanceId");
        }

        // 2 повторы
        if (prRepo.countByPT(requestBody.instanceId, requestBody.registryTypeCode) > 0) {
            throw new BadRequestException("Параметр registryTypeCode тип регистра "+ requestBody.registryTypeCode+" уже существует для ЭП с ИД "+ requestBody.instanceId+".");
        }

        // 3 проверка типа по коду в tpp_ref_product_register_type
        prType = tppRefProductRegisterTypeRepo.getByValue(requestBody.registryTypeCode);
        if (prType == null) {
            throw new NotFoundException("Код Продукта "+ requestBody.registryTypeCode+" не найдено в Каталоге продуктов");
        }

        // 4 Найти значение номера счета
        accountPool = accountPoolRepo.findByCodes(
            requestBody.branchCode,
            requestBody.currencyCode,
            requestBody.mdmCode,
            requestBody.priorityCode,
            requestBody.registryTypeCode
        );
        if (accountPool == null) {
            throw new NotFoundException("Не найден пул счетов");
        }

        account = accountRepo.findFirstByAP(accountPool.id);
        if (account == null) {
            throw new NotFoundException("Не найден счет");
        }
    }

    public Object createRegister() {
        Tpp_product_register tppProductRegister = new Tpp_product_register();
        tppProductRegister.product_id = requestBody.instanceId;
        tppProductRegister.type = requestBody.registryTypeCode;
        tppProductRegister.account = account.id;
        tppProductRegister.currency_code = requestBody.currencyCode;
        tppProductRegister.state = "";
        tppProductRegister.account_number = account.account_number;
        prRepo.save(tppProductRegister);
        // ответ
        HashMap<String, Object> hm = new HashMap<>();
        HashMap<String, String> accId = new HashMap<>();
        accId.put("accountId", tppProductRegister.id.toString());
        hm.put("data", accId);
        return hm;
    }

    public Object execute() {
        check();
        return createRegister();
    }
}
