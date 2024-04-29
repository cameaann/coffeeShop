package coffeeShop.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ApplicationController {

    @GetMapping("/")
    public String Main(){
        return "public/index";
    }

    @GetMapping("/administrator")
    public String showAdmin(){
        return "public/administrator";
    }

//    @GetMapping("/products")
//    public String showProducts(){
//        return "public/products";
//    }



}
