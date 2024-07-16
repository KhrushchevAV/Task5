package study.stepup.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import jakarta.validation.constraints.NotNull;

public class InstanceRequestBody {
    public Long instanceId;
    @NotNull
    public String productType;          // Тип Экземпляра Продукта
    @NotNull
    public String productCode;          // Код продукта в каталоге продуктов
    @NotNull
    public String registerType;         // Тип регистра
    @NotNull
    public String mdmCode;              // Код Клиента (mdm)
    @NotNull
    public String contractNumber;       // Номер договора
    @NotNull
    public LocalDateTime contractDate;  // Дата заключения договора обслуживания
    @NotNull
    public Long priority;                       // Приоритет
    public Float interestRatePenalty;           // Штрафная процентная ставка
    public Float minimalBalance;                // Неснижаемый остаток
    public Float thresholdAmount;               // Пороговая сумма
    public String accountingDetails;            // Реквизиты выплаты
    public String rateType;                     // Выбор ставки в зависимости от суммы
    public Float taxPercentageRate;             // Ставка налогообложения
    public Float technicalOverdraftLimitAmount; // Сумма лимита технического овердрафта
    @NotNull
    public Long contractId;                     // ID Договора
    @NotNull
    public String branchCode;                   // Код филиала
    @NotNull
    public String isoCurrencyCode;              // Код валюты
    @NotNull
    public String urgencyCode;                  // Код срочности договора
    public Long referenceCode;                  // Код точки продаж
    public HashMap<String, List<InstanceRequestBodyAdditionalProperties>> additionalProperties;   // массив дополнительных признаков для сегмента КИБ(VIP), добавлять по мере необходимости? на сегодня могут быть переданы 2 пары ключ/значение
    public List<InstanceArrangement> instanceArrangement;                   // массив Доп.Соглашений
}

