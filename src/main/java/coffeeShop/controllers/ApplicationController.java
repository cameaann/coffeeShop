package coffeeShop.controllers;

//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

    @GetMapping("/")
    public String Main(){
        return "public/home";
    }

    @GetMapping("/administrator")
    public String showAdmin(){
        return "public/administrator";
    }

}
