package coffeeShop.controllers;

import coffeeShop.models.Department;
import coffeeShop.services.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public String showDepartments(Model model){
        final Iterable<Department> departments = this.departmentService.list();
        model.addAttribute("departments", departments);
        return "admin/departments";
    }

    @PostMapping("/departments")
    public String createDepartment(@RequestParam String name, @RequestParam String parentId){
        Long parId;
        if(parentId.isEmpty()){
            parId = null;
        } else{
            parId = Long.parseLong(parentId);
        }
        if(!name.isEmpty()){
            this.departmentService.add(name, parId);
            return "redirect:/departments";
        }
        return "redirect:/departments";
    }
}
