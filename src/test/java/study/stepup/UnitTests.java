package study.stepup;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.stepup.entities.Account;
import study.stepup.entities.Account_pool;
import study.stepup.entities.Tpp_product_register;
import study.stepup.entities.Tpp_ref_product_register_type;
import study.stepup.repositories.AccountRepo;
import study.stepup.repositories.Account_poolRepo;
import study.stepup.repositories.Tpp_product_registerRepo;
import study.stepup.repositories.Tpp_ref_product_register_typeRepo;

import java.util.List;

@SpringBootTest
public class UnitTests {

    @Autowired
    Tpp_product_registerRepo repo;

    @Autowired
    Account_poolRepo accountPoolRepo;
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    Tpp_ref_product_register_typeRepo tppRefProductRegisterTypeRepo;

    @Test
    public void TestTppProductRegisterRepo() {
        int cnt = repo.countByPT(1L, "NO_TYPE");
        Assertions.assertEquals(0, cnt);
    }

    @Test
    public void TestAccount() {
        Account_pool ap = accountPoolRepo.findByCodes("0022", "800", "15", "00", "03.012.002_47533_ComSoLd");
        Assertions.assertNotNull(ap);
        System.out.println(ap);
        Account a = accountRepo.findFirstByAP(ap.id);
        System.out.println(a);
        Assertions.assertNotNull(a);
    }

    @Test
    public void TestRegisterType() {
        List<Tpp_ref_product_register_type> tpp = tppRefProductRegisterTypeRepo.getByCodeCl("03.012.002");
        System.out.println(tpp);
        Assertions.assertEquals(1, tpp.size());
    }
}
