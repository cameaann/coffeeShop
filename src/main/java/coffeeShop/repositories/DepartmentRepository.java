package coffeeShop.repositories;

import coffeeShop.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAllByParent(Department parent);
}
