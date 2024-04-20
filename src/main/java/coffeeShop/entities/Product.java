package coffeeShop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product extends AbstractPersistable<Long> {
    private String name;
    private String description;
    private BigDecimal price;
    private String productImg;

    @ManyToOne
    private Department department;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Manufacturer manufacturer;
}
