package coffeeShop.controllers;

import coffeeShop.services.ManufacturerService;
import coffeeShop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ManufacturerController {
    private ManufacturerService manufacturerService;
    private ProductService productService;
    public ManufacturerController(ManufacturerService manufacturerService,
                                  ProductService productService) {
        this.manufacturerService = manufacturerService;
        this.productService = productService;
    }

    @GetMapping("/manufacturers")
    public String getManufacturers(Model model){
        model.addAttribute("manufacturers", manufacturerService.list());
        model.addAttribute("errorMessage", manufacturerService.getErrorMessage());

        return "admin/manufacturers";
    }

    @PostMapping("/manufacturers")
    public String createSupplier(Model model, @RequestParam String name,
                                 @RequestParam String url) {
        model.addAttribute("errorMessage", manufacturerService.getErrorMessage());
        if (!name.isEmpty() && !url.isEmpty()) {
            this.manufacturerService.add(name, url);
            return "redirect:/manufacturers";
        }
        return "redirect:/manufacturers";
    }

    @GetMapping("/manufacturer-edit/{manufacturerId}")
    public String getManufacturer(Model model,  @PathVariable(value = "manufacturerId") long manufacturerId){
        model.addAttribute("manufacturer", this.manufacturerService.getOne(manufacturerId));
        return "admin/manufacturer-edit";
    }

    @PostMapping("/manufacturer-edit/{manufacturerId}")
    public String updateManufacturer(@PathVariable(value = "manufacturerId") long id,
                                 @RequestParam String name,
                                 @RequestParam String url){
        this.manufacturerService.change(name, url, id);

        return "redirect:/manufacturer-edit/{manufacturerId}";
    }

    @PostMapping("/manufacturers/{manufacturerId}")
    public String delete(@PathVariable(value = "manufacturerId") long manufacturerId) {
        if(productService.getProductsByManufacturer(manufacturerId).isEmpty()){
            this.manufacturerService.remove(manufacturerId);
        }
        else {
            manufacturerService.setErrorMessage("Ei ole mahdollista poistaa valmistajaa, koska tietokannassa on vielä tämän valmistajan tuotteita.");
        }
        return "redirect:/manufacturers";
    }

    @GetMapping("/clearErrorMessage")
    @ResponseBody
    public void clearErrorMessage() {
        manufacturerService.clearErrorMessage();
    }
}
