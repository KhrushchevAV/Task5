package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Agreement;

@Repository
public interface AgreementRepo extends JpaRepository<Agreement, Long> {
}
