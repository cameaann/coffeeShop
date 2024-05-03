package coffeeShop.dto;

import coffeeShop.models.CartItem;
import coffeeShop.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long id;
    private Long userId;
    private Integer quantity;
    private Product product;


    public CartDto(CartItem cartItem){
        this.setId(cartItem.getId());
        this.setUserId(cartItem.getUserId());
        this.setQuantity(cartItem.getQuantity());
        this.setProduct(cartItem.getProduct());
    }

    public String toString(){
        return "CartDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", productName=" + product.getName() +
                '}';
    }

}

