package study.stepup.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tpp_ref_account_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long internal_id;
    String value;
}
//CREATE TABLE IF NOT EXISTS tpp_ref_account_type
//        (
//                internal_id serial PRIMARY KEY ,
//                value VARCHAR(100) UNIQUE NOT NULL
//);

