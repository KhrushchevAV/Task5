package study.stepup.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Tpp_product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long product_code_id;
    Long client_id;
    String type;
    String number;
    Long priority;
    LocalDateTime date_of_conclusion;
    LocalDateTime start_date_time;
    LocalDateTime end_date_time;
    Long days;
    Double penalty_rate;
    Double nso;
    Double threshold_amount;
    String requisite_type;
    String interest_rate_type;
    Double tax_rate;
    String reasone_close;
    String state;
}

/*
CREATE TABLE IF NOT EXISTS tpp_product
    (
    id serial PRIMARY KEY,
--	agreement_id BIGINT,
    product_code_id BIGINT,
    client_id BIGINT,
    type VARCHAR(50),
    number VARCHAR(50),
    priority BIGINT,
    date_of_conclusion TIMESTAMP,
    start_date_time TIMESTAMP,
    end_date_time TIMESTAMP,
    days BIGINT,
    penalty_rate DECIMAL,
    nso DECIMAL,
    threshold_amount DECIMAL,
    requisite_type VARCHAR(50),
    interest_rate_type VARCHAR(50),
    tax_rate DECIMAL,
    reasone_close VARCHAR(100),
    state VARCHAR(50)
);
*/
