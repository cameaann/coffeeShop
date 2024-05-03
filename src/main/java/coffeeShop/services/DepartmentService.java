package coffeeShop.services;

import coffeeShop.models.Department;
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

    public void add(String name, Long parentId){
        Department department = new Department();
        department.setName(name);
        if (parentId != null) {
            Department parent = departmentRepository.findById(parentId).orElse(null);
            department.setParent(parent);
        }
        departmentRepository.save(department);
    }

    public Department getOne(Long id){
        return departmentRepository.getReferenceById(id);
    }
}
