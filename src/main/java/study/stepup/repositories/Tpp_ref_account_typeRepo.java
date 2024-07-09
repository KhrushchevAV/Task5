package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Tpp_ref_account_type;

@Repository
public interface Tpp_ref_account_typeRepo extends JpaRepository<Tpp_ref_account_type, Long> {
}
