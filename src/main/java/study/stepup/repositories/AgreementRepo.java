package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Agreement;

import java.util.List;

@Repository
public interface AgreementRepo extends JpaRepository<Agreement, Long> {

    List<Agreement> findByNumber(String number);

    // список agreement.id по звдвнному product_id
    @Query(value="SELECT agr.id FROM agreement agr WHERE agr.product_id = ?1", nativeQuery = true)
    List<Long> getAgreementIdList(Long productId);
}
