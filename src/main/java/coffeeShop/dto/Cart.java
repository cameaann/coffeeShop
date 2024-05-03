package coffeeShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {
    private List<CartDto> cartItems;
    private BigDecimal totalCost;

}
