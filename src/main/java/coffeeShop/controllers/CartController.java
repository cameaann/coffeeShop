package coffeeShop.controllers;

import coffeeShop.models.Cart;
import coffeeShop.models.CartItem;
import coffeeShop.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CartController {

    private CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model){
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        return "public/cart";
    }

    @PostMapping("/cart")
    public String addProduct(@RequestParam long depId,
                             @RequestParam long productId,
                             @RequestParam String name,
                             @RequestParam BigDecimal price){
        cartService.addProduct(productId, name, price);
        return "redirect:/products?id="+depId;
    }


    @PostMapping("/cart/increase/{productId}")
    public String increaseQuantity(@PathVariable long productId){
        cartService.increase(productId);
        return "redirect:/cart";
    }
    @PostMapping("/cart/decrease/{productId}")
    public String decreaseQuantity(@PathVariable long productId){
        cartService.decrease(productId);
        return "redirect:/cart";
    }

}
