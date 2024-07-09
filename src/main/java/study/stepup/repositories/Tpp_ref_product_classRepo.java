package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Tpp_ref_product_class;

@Repository
public interface Tpp_ref_product_classRepo extends JpaRepository<Tpp_ref_product_class, Long> {
}
