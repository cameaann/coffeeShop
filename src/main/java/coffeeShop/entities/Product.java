package coffeeShop.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;
import java.util.Arrays;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product extends AbstractPersistable<Long> {
    private String name;
    private String description;
    private BigDecimal price;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] productImg;
    private String productImgName;

    @ManyToOne
    private Department department;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Manufacturer manufacturer;

}
