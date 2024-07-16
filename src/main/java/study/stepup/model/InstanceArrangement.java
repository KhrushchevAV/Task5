package study.stepup.model;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class InstanceArrangement {
    public String generalAgreementId;                   // ID доп.Ген.соглашения
    public String supplementaryAgreementId;             // ID доп.соглашения
    public String arrangementType;                      // Тип соглашения
    public Long schedulerJobId;                         // Идентификатор периодичности учета
    @NotNull
    public String number;                               // Номер ДС	Number
    @NotNull
    public LocalDateTime openingDate;                   // Дата начала сделки
    @NotNull
    public LocalDateTime closingDate;                   // Дата окончания сделки
    public LocalDateTime cancelDate;                    // Дата отзыва сделки
    public Long validityDuration;                       // Срок действия сделки
    public String cancellationReason;                   // Причина расторжения
    public String status;                               // Состояние/статус
    public LocalDateTime interestCalculationDate;       // Начисление начинается с (дата)
    public Float interestRate;                          // Процент начисления на остаток
    public Float coefficient;                           // Коэффициент
    public String coefficientAction;                    // Действие коэффициента
    public Float minimumInterestRate;                   // Минимум по ставке
    public String minimumInterestRateCoefficient;       // Коэффициент по минимальной ставке
    public String minimumInterestRateCoefficientAction; // Действие коэффициента по минимальной ставке
    public Float maximalInterestRate;                    // Максимум по ставке
    public Float maximalInterestRateCoefficient;         // Коэффициент по максимальной ставке
    public String maximalInterestRateCoefficientAction;  // Действие коэффициента по максимальной ставке
}