package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Tpp_ref_product_register_type;

import java.util.List;

@Repository
public interface Tpp_ref_product_register_typeRepo extends JpaRepository<Tpp_ref_product_register_type, Long> {
    Tpp_ref_product_register_type getByValue(String val);

    @Query(value =
        "SELECT t.internal_id, " +
                "t.value, " +
                "t.register_type_name, " +
                "t.product_class_code, " +
                "t.register_type_start_date, " +
                "t.register_type_end_date," +
                "t.account_type " +
        "FROM tpp_ref_product_register_type t " +
        "WHERE t.product_class_code=?1 " +
        " and t.account_type = 'Клиентский' ",
        nativeQuery = true)
    List<Tpp_ref_product_register_type> getByCodeCl(String code);
}
