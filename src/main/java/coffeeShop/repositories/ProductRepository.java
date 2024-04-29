package coffeeShop.repositories;

import coffeeShop.entities.Department;
import coffeeShop.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByDepartment(Department department);
}
