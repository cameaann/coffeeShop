package coffeeShop.services;

import coffeeShop.models.Cart;
import coffeeShop.models.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CartService {
    private Cart cart;

    public CartService() {
        cart = new Cart();
        cart.setDateCreated(LocalDate.now());
    }

    public void addProduct(Long productId, String productName, BigDecimal productPrice){
        final Cart cart = getCart();

        final Optional<CartItem> cartItemWithProduct = getCartItemByProductId(cart, productId);
        if (cartItemWithProduct.isPresent()) {
            final CartItem cartItem = cartItemWithProduct.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProductId(productId);
            cartItem.setProductName(productName);
            cartItem.setPrice(productPrice);
            cartItem.setQuantity(1);
            cart.getItems().add(cartItem);
        }

    }

    private static Optional<CartItem> getCartItemByProductId(Cart cart, Long productId) {
        final Optional<CartItem> cartItemWithProduct = cart.getItems().stream()
                .filter(item -> Objects.equals(item.getProductId(), productId))
                .findFirst();
        return cartItemWithProduct;
    }

    public Cart getCart() {
        return cart;
    }

    public void increase(long productId){
        final CartItem cartItem = getCartItemByProductId(cart, productId).orElseThrow();
        cartItem.setQuantity(cartItem.getQuantity() + 1);
    }

    public void decrease(long productId){
        final CartItem cartItem = getCartItemByProductId(cart, productId).orElseThrow();
        if(cartItem.getQuantity()==1){
            deleteProduct(productId);
        }
        cartItem.setQuantity(cartItem.getQuantity() - 1);
    }

    public void deleteProduct(long productId){
        final CartItem cartItem = getCartItemByProductId(cart, productId).orElseThrow();
        cart.getItems().remove(cartItem);
    }

}
