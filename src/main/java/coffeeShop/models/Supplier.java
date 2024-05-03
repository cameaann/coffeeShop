package coffeeShop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier extends AbstractPersistable<Long> {
    private String name;
    private String contactPerson;
    private String contactPersonEmail;

    @OneToMany(mappedBy = "supplier")
    List <Product>products = new ArrayList<>();
}
