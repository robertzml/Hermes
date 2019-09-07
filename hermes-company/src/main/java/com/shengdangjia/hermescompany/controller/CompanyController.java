package com.shengdangjia.hermescompany.controller;

import com.shengdangjia.hermescompany.model.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Value("${server.port}")
    String port = "";

    @RequestMapping("/find")
    public Company FindByName(@RequestParam(value = "name", defaultValue = "jack") String name) {
        try {
            Company c = new Company(1, name);
            c.setPort(port);

            InetAddress ia = InetAddress.getLocalHost();
            c.setIp(ia.getHostAddress());
            c.setHostname(ia.getHostName());

            return c;
        }
        catch (Exception e) {
            Company c = new Company(1, name);
            c.setPort(port);

            return c;
        }
    }
}
