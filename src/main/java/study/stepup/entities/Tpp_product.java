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
    public Long id;
    public Long product_code_id;
    public Long client_id;
    public String type;
    public String number;
    public Long priority;
    public LocalDateTime date_of_conclusion;
    public LocalDateTime start_date_time;
    public LocalDateTime end_date_time;
    public Long days;
    public Float penalty_rate;
    public Float nso;
    public Float threshold_amount;
    public String requisite_type;
    public String interest_rate_type;
    public Float tax_rate;
    public String reasone_close;
    public String state;
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
