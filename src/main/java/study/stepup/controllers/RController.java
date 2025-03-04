package study.stepup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.stepup.model.InstanceRequestBody;
import study.stepup.model.ProductRegister;
import study.stepup.services.InstanceCreateService;
import study.stepup.services.ProductRegisterService;

@RestController
public class RController {

    @Autowired
    ProductRegisterService prs;
    @Autowired
    InstanceCreateService instanceCreateService;

    // Продуктовый регистр
    @PostMapping("/corporate-settlement-account/create")
    public ResponseEntity<?> accountCreate(@RequestBody ProductRegister pr) {
        prs.setProductRegister(pr);
        Object answer = prs.execute();
        return new ResponseEntity<>(answer, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/corporate-settlement-instance/create")
    public ResponseEntity<?> instanceCreate(@RequestBody InstanceRequestBody requestBody) {
        instanceCreateService.setRequestBody(requestBody);
        Object answer = instanceCreateService.execute();
        return  new ResponseEntity<>(answer, new HttpHeaders(), HttpStatus.CREATED);
    }
}
