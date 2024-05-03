package coffeeShop.controllers;

import coffeeShop.services.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManufacturerController {
    private ManufacturerService manufacturerService;
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/manufacturers")
    public String getManufacturers(Model model){
        model.addAttribute("manufacturers", manufacturerService.list());
        return "admin/manufacturers";
    }

    @PostMapping("/manufacturers")
    public String createSupplier(@RequestParam String name,
                                 @RequestParam String url) {
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
//                                 @RequestParam ArrayList<Product>products){
        this.manufacturerService.change(name, url, id);

        return "redirect:/manufacturer-edit/{manufacturerId}";
    }

    @PostMapping("/manufacturers/{manufacturerId}")
    public String delete(@PathVariable(value = "manufacturerId") long manufacturerId) {
        this.manufacturerService.remove(manufacturerId);
        return "redirect:/manufacturers";
    }

}
