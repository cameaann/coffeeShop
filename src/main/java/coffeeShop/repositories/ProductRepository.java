package coffeeShop.repositories;

import coffeeShop.models.Department;
import coffeeShop.models.Manufacturer;
import coffeeShop.models.Product;
import coffeeShop.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByDepartment(Department department);
    List<Product> findAllByDepartmentAndManufacturer(Department department, Manufacturer manufacturer);
    List<Product> findAllByManufacturer(Manufacturer manufacturer);
    List<Product> findAllBySupplier(Supplier supplier);

}
