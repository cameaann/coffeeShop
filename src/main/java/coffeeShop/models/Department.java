package coffeeShop.models;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Department parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Department> children = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private List<Product> products = new ArrayList<>();

//    @Override
    public String toString() {
        return "name='" + name;
    }
}
