package coffeeShop.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.validation.annotation.Validated;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer extends AbstractPersistable<Long> {
    private String name;
    private String email;

//    private ArrayList<Purchase> purchaseHistory;
}
