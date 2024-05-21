package coffeeShop.controllers;

import coffeeShop.services.CartService;
import coffeeShop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    private CartService cartService;
    private ProductService productService;

    public ApplicationController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String Main(Model model){
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("coffeeProducts", productService.getProductsByDep(7L));
        model.addAttribute("departmentId", 7L);
        return "public/home";
    }


}
