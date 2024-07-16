package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Tpp_product_register;

import java.util.List;

@Repository
public interface Tpp_product_registerRepo extends JpaRepository<Tpp_product_register, Long> {

    // количесто записей по заданным product_id & type
    @Query(value = "SELECT count(1) FROM tpp_product_register tpp WHERE tpp.product_id=?1 and tpp.type=?2", nativeQuery = true)
    int countByPT(Long productId, String type);

    // список id регистров по данному продукту
    @Query(value = "SELECT r.is FROM tpp_product_register r WHERE r.product_id = ?1", nativeQuery = true)
    List<Long> getRegisterIdList(Long productId);
}
