package coffeeShop.entities;

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
public class Department extends AbstractPersistable<Long> {
    private String name;
    private Long departmentIDP;

    @OneToMany(mappedBy = "department")
    private List<Product>products = new ArrayList<>();
}
