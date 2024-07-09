package study.stepup.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long account_pool_id;
    public String account_number;
    public Boolean bussy;
}
/*
CREATE TABLE IF NOT EXISTS account(
        id serial PRIMARY KEY,
        account_pool_id integer,
        account_number VARCHAR(25),
        bussy BOOLEAN
);

ALTER TABLE account
ADD FOREIGN KEY (account_pool_id) REFERENCES account_pool (id);
*/

