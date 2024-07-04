package study.stepup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RController {

    @Autowired
    AccountService accountService;

    @GetMapping("/corporate-settlement-account/create")
    public String accountCreate() {
        return "account created";
    }

    @GetMapping("/corporate-settlement-instance/create")
    public String instanceCreate() {
        return "instance created";
    }
}
