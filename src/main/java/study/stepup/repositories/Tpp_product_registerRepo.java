package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Tpp_product_register;

@Repository
public interface Tpp_product_registerRepo extends JpaRepository<Tpp_product_register, Long> {
    @Query(value = "SELECT count(1) FROM tpp_product_register tpp WHERE tpp.product_id=?1 and tpp.type=?2", nativeQuery = true)
    int countByPT(Long productId, String type);
}
