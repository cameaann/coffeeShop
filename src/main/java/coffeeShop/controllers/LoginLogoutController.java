package coffeeShop;

import coffeeShop.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {

    private CartService cartService;

    public LoginLogoutController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("cart", cartService.getCart());
        return "public/login";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute("cart", cartService.getCart());
        return "public/home";}

}
