package coffeeShop.controllers;

import coffeeShop.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("/header")
    public String showLinks(){
        return "public/header";
    }
}
