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
    Long id;
    Long product_id;
    String general_agreement_id;
    String supplementary_agreement_id;
    String arrangement_type;
    Long sheduler_job_id;
    String number;
    LocalDateTime opening_date;
    LocalDateTime closing_date;
    LocalDateTime cancel_date;
    Long validity_duration;
    String cancellation_reason;
    String status;
    LocalDateTime interest_calculation_date;
    Double interest_rate;
    Double coefficient;
    String coefficient_action;
    Double minimum_interest_rate;
    Double minimum_interest_rate_coefficient;
    String minimum_interest_rate_coefficient_action;
    Double maximal_interest_rate;
    Double maximal_interest_rate_coefficient;
    String maximal_interest_rate_coefficient_action;
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
