package coffeeShop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {
    private List<CartItem> cartItems;
    private BigDecimal totalCost;

}