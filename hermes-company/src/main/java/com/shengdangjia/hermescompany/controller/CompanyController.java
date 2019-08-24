package com.shengdangjia.hermescompany.controller;

import com.shengdangjia.hermescompany.model.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @Value("${server.port}")
    String port = "";

    @RequestMapping("/find")
    public Company FindByName(@RequestParam(value = "name", defaultValue = "jack") String name) {
        Company c = new Company(1, name);
        c.setPort(port);

        return c;
    }
}
