package coffeeShop.controllers;

import coffeeShop.models.Customer;
import coffeeShop.services.CartService;
import coffeeShop.services.CustomerService;
import coffeeShop.validation.CustomerForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerService customerService;
    private CartService cartService;

    public CustomerController(CustomerService customerService, CartService cartService) {
        this.customerService = customerService;
        this.cartService = cartService;
    }

    @GetMapping("/customer-registration")
    public String viewFormRegisterCustomer(CustomerForm customerForm, Model model) {
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("customerForm", customerForm);
        return "public/customer-registration";
    }

    @GetMapping("/success-registration")
    public String viewSuccessForm(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "public/success-registration";
    }

    @PostMapping("/customer-registration")
    public String registerCustomer(@Valid CustomerForm customerForm, BindingResult bindingResult,
                                   @RequestParam String name, @RequestParam String email, Model model) {
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("customerForm", customerForm);
        if (bindingResult.hasErrors() || !this.customerService.isUniqueEmail(email)) {
            if(!this.customerService.isUniqueEmail(email)){
                bindingResult.rejectValue("email", "error.email", "User with such email exists!");
            }
            return "public/customer-registration";
        } else {
            this.customerService.add(name, email);
            return "redirect:/success-registration";
        }
    }

    @GetMapping("/vip-customers")
    public String showVipCustomers(Model model){
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "admin/vip-customers";
    }
}
