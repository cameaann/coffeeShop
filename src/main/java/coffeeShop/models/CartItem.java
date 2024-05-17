package coffeeShop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {
    private Long id;
    private int quantity;

    private long productId;
    private String productName;
    private BigDecimal price;

    public CartItem(long productId, String productName, BigDecimal productPrice, Integer quantity){
        this.quantity = quantity;
        this.productId = productId;
        this.productName = productName;
        this.price = productPrice;
    }

}

