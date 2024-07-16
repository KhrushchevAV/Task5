package study.stepup.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@ToString
public class Tpp_ref_product_register_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long internal_id;
    public String value;
    public String register_type_name;
    public String product_class_code;
    public LocalDateTime register_type_start_date;
    public LocalDateTime register_type_end_date;
    public String account_type;
}
/*
CREATE TABLE IF NOT EXISTS tpp_ref_product_register_type
        (
            internal_id serial PRIMARY KEY ,
            value VARCHAR(100) UNIQUE NOT NULL,
            register_type_name VARCHAR(100) NOT NULL,
            product_class_code VARCHAR(100) NOT NULL,
            register_type_start_date TIMESTAMP,
            register_type_end_date TIMESTAMP,
            account_type VARCHAR(100)
);
ALTER TABLE tpp_ref_product_register_type
ADD FOREIGN KEY (product_class_code) REFERENCES tpp_ref_product_class (value);
ALTER TABLE tpp_ref_product_register_type
ADD FOREIGN KEY (account_type) REFERENCES tpp_ref_account_type (value);
*/

