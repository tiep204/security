package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class webController {
    @GetMapping(value = {"/","/home"})
    public String homePage(){
        return "home";
    }
    @GetMapping("/user")
    public String helloPage(){
        return "user";
    }
    @GetMapping("/admin")
    public String adminRole(){
        return "admin";
    }
}
