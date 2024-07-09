package study.stepup.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tpp_product_register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long product_id;
    public String type;
    public Long account;
    public String currency_code;
    public String state;
    public String account_number;
}

/*
CREATE TABLE IF NOT EXISTS tpp_product_register
        (
        id serial PRIMARY KEY ,
        product_id BIGINT,
        type VARCHAR(100) NOT NULL,
        account BIGINT,
        currency_code VARCHAR(30),
        state VARCHAR(50),
        account_number VARCHAR(25)
);

ALTER TABLE tpp_product_register
ADD FOREIGN KEY (type) REFERENCES tpp_ref_product_register_type (value);
*/


