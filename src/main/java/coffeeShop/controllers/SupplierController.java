package coffeeShop.controllers;

import coffeeShop.services.ProductService;
import coffeeShop.services.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupplierController {
    private SupplierService supplierService;
    private ProductService productService;

    public SupplierController(SupplierService supplierService, ProductService productService) {
        this.supplierService = supplierService;
        this.productService = productService;
    }

    @GetMapping("/suppliers")
    public String getSuppliers(Model model) {
        model.addAttribute("suppliers", this.supplierService.list());
        model.addAttribute("errorMessage", this.supplierService.getErrorMessage());
        return "admin/suppliers";
    }

    @PostMapping("/suppliers")
    public String createSupplier(@RequestParam String name,
                                 @RequestParam String contactPerson,
                                 @RequestParam String contactPersonEmail) {
        if (!name.isEmpty() && !contactPerson.isEmpty() && !contactPersonEmail.isEmpty()) {
            this.supplierService.add(name, contactPerson, contactPersonEmail);
            return "redirect:/suppliers";
        }
        return "redirect:/suppliers";
    }

    @GetMapping("/supplier-edit/{supplierId}")
    public String getSupplier(Model model,  @PathVariable(value = "supplierId") long supplierId){
        model.addAttribute("supplier", this.supplierService.getOne(supplierId));
        return "admin/supplier-edit";
    }

    @PostMapping("/supplier-edit/{supplierId}")
    public String updateSupplier(@PathVariable(value = "supplierId") long id,
                                 @RequestParam String name,
                                 @RequestParam String contactPerson,
                                 @RequestParam String contactPersonEmail){
        this.supplierService.change(name, contactPerson, contactPersonEmail, id);

        return "redirect:/supplier-edit/{supplierId}";
    }

    @PostMapping("/suppliers/{supplierId}")
    public String delete(@PathVariable(value = "supplierId") long supplierId) {
        if(productService.getProductsBySupplier(supplierId).isEmpty()){
            this.supplierService.remove(supplierId);
        }
        else {
            supplierService.setErrorMessage("Ei ole mahdollista poistaa toimittaja, koska tietokannassa on vielä tämän toimitajan tuotteita.");
        }
        return "redirect:/suppliers";
    }
}
