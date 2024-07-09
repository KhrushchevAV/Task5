package study.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import study.stepup.entities.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    @Query(value="SELECT a.id, " +
            "a.account_pool_id, " +
            "a.account_number, " +
            "a.bussy " +
            "FROM account a " +
            "WHERE a.account_pool_id=?1 and a.bussy=false " +
            "FETCH FIRST 1 ROW ONLY", nativeQuery = true)
    Account findFirstByAP(Long accountPoolId);
}
