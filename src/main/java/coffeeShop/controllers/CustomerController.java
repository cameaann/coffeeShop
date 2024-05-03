package coffeeShop.controllers;

import coffeeShop.services.CustomerService;
import coffeeShop.validation.CustomerForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("public/success-registration").setViewName("public/success-registration");
//    }

    @GetMapping("/customer-registration")
    public String viewFormRegisterCustomer(CustomerForm customerForm) {
        return "public/customer-registration";
    }

    @PostMapping("/customer-registration")
    public String registerCustomer(@Valid CustomerForm customerForm, BindingResult bindingResult, Model model,
                                   @RequestParam String name, @RequestParam String email) {
        if (bindingResult.hasErrors() || !this.customerService.isUniqueEmail(email)) {
            if(!this.customerService.isUniqueEmail(email)){
                bindingResult.rejectValue("email", "error.email", "User with such email exists!");
            }
            return "public/customer-registration";
        } else {
//            this.customerService.add(name, email);
            return "redirect:/success-registration";
        }
    }
}
