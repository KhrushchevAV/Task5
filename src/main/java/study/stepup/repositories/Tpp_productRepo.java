package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Tpp_product;

import java.util.List;

@Repository
public interface Tpp_productRepo extends JpaRepository<Tpp_product, Long> {
    List<Tpp_product> findByNumber(String number);
}
