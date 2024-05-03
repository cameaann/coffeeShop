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
public class Manufacturer extends AbstractPersistable<Long> {
    private String name;
    private String url;
    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products = new ArrayList<>();
}
