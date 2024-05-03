package coffeeShop.models;

import coffeeShop.dto.CartDto;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem extends AbstractPersistable <Long> {
  private long userId;
  private long productId;
  private Date createdDate;

  private Product product;

  private int quantity;

  public CartItem(CartDto cartDto, Product product){
     this.productId = cartDto.getUserId();
     this.quantity = cartDto.getQuantity();
     this.product = product;
     this.createdDate = new Date();
  }
}
