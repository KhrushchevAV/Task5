package study.stepup.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long product_id;
    public String general_agreement_id;
    public String supplementary_agreement_id;
    public String arrangement_type;
    public Long sheduler_job_id;
    public String number;
    public LocalDateTime opening_date;
    public LocalDateTime closing_date;
    public LocalDateTime cancel_date;
    public Long validity_duration;
    public String cancellation_reason;
    public String status;
    public LocalDateTime interest_calculation_date;
    public Float interest_rate;
    public Float coefficient;
    public String coefficient_action;
    public Float minimum_interest_rate;
    public Float minimum_interest_rate_coefficient;
    public String minimum_interest_rate_coefficient_action;
    public Float maximal_interest_rate;
    public Float maximal_interest_rate_coefficient;
    public String maximal_interest_rate_coefficient_action;
}

/*
CREATE TABLE IF NOT EXISTS agreement
(
	id serial PRIMARY KEY,
	product_id integer,
	general_agreement_id VARCHAR(50),
	supplementary_agreement_id VARCHAR(50),
	arrangement_type VARCHAR(50),
	sheduler_job_id BIGINT,
	number VARCHAR(50),
    opening_date TIMESTAMP,
    closing_date TIMESTAMP,
    cancel_date TIMESTAMP,
    validity_duration BIGINT,
    cancellation_reason VARCHAR(100),
    status VARCHAR(50),
    interest_calculation_date TIMESTAMP,
    interest_rate DECIMAL,
    coefficient DECIMAL,
    coefficient_action VARCHAR(50),
    minimum_interest_rate DECIMAL,
    minimum_interest_rate_coefficient DECIMAL,
    minimum_interest_rate_coefficient_action VARCHAR(50),
    maximal_interest_rate DECIMAL,
    maximal_interest_rate_coefficient DECIMAL,
    maximal_interest_rate_coefficient_action VARCHAR(50)
);

ALTER TABLE agreement
ADD FOREIGN KEY (product_id) REFERENCES tpp_product (id);
 */
