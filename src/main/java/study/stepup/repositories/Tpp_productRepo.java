package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Tpp_product;

@Repository
public interface Tpp_productRepo extends JpaRepository<Tpp_product, Long> {

}
