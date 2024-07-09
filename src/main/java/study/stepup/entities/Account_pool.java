package study.stepup.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Account_pool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String branch_code;
    public String currency_code;
    public String mdm_code;
    public String priority_code;
    public String registry_type_code;
}
/*
CREATE TABLE IF NOT EXISTS account_pool(
    id serial PRIMARY KEY,
    branch_code VARCHAR(50),
    currency_code VARCHAR(30),
    mdm_code VARCHAR(50),
    priority_code VARCHAR(30),
    registry_type_code VARCHAR(50)
);
*/
