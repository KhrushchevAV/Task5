package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Account_pool;

@Repository
public interface Account_poolRepo extends JpaRepository<Account_pool, Long> {
    @Query(value="SELECT ap.id, " +
            " ap.branch_code, " +
            " ap.currency_code, " +
            " ap.mdm_code, " +
            " ap.priority_code, " +
            " ap.registry_type_code " +
            "FROM account_pool ap " +
            "WHERE ap.branch_code=?1 " +
            " and ap.currency_code=?2 " +
            " and ap.mdm_code=?3 " +
            " and ap.priority_code=?4 " +
            " and ap.registry_type_code=?5 ",
        nativeQuery = true)
    Account_pool findByCodes(String branch, String cur, String mdm, String priority, String type);
}
