package coffeeShop.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer extends AbstractPersistable<Long> {
    @NotNull(message = "required field!")
    @Size(min = 2, message = "Name should contain at least 2 character.")
    private String name;

    @NotNull
    private String email;

//    private ArrayList<Purchase> purchaseHistory;
}
