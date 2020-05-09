package pl.sgnit.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrationController {

    @GetMapping("/register")
    public String initRegister() {
        return "register";
    }
}
