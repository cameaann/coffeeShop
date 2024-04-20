package coffeeShop.services;

import coffeeShop.entities.Department;
import coffeeShop.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Iterable<Department> list(){
        return departmentRepository.findAll();
    }
}
