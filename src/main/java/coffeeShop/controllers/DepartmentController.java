package coffeeShop.controllers;

import coffeeShop.services.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("departments", this.departmentService.list());
        return "public/departments";
    }

    @PostMapping("/departments")
    public String createDepartment(@RequestParam String name, @RequestParam Long departmentIDP){

        return "redirect:/departments";
    }
}
