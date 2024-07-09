package study.stepup.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tpp_ref_product_class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long internal_id;
    String value;
    String gbi_code;
    String gbi_name;
    String product_row_code;
    String product_row_name;
    String subclass_code;
    String subclass_name;
}
//CREATE TABLE IF NOT EXISTS tpp_ref_product_class
//        (
//                internal_id serial PRIMARY KEY ,
//                value VARCHAR(100) UNIQUE NOT NULL,
//gbi_code VARCHAR(50),
//gbi_name VARCHAR(100),
//product_row_code VARCHAR(50),
//product_row_name VARCHAR(100),
//subclass_code VARCHAR(50),
//subclass_name VARCHAR(100)
//);
