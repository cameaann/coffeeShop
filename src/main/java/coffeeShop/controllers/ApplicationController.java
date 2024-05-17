package coffeeShop.controllers;

import coffeeShop.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    private CartService cartService;

    public ApplicationController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String Main(Model model){
        model.addAttribute("cart", cartService.getCart());
        return "public/home";
    }


}
