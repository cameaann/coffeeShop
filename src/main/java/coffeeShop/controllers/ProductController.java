package coffeeShop.controllers;

import coffeeShop.models.Department;
import coffeeShop.models.Product;
import coffeeShop.services.DepartmentService;
import coffeeShop.services.ManufacturerService;
import coffeeShop.services.ProductService;
import coffeeShop.services.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    private ProductService productService;
    private SupplierService supplierService;

    private DepartmentService departmentService;

    private ManufacturerService manufacturerService;

    public ProductController(ProductService productService,
                             SupplierService supplierService,
                             DepartmentService departmentService,
                             ManufacturerService manufacturerService) {
        this.productService = productService;
        this.supplierService = supplierService;
        this.departmentService = departmentService;
        this.manufacturerService = manufacturerService;
    }


    //Methods for work with products in Admin page
    @GetMapping("/products-admin")
    public String getProducts(Model model) {
        model.addAttribute("departments", departmentService.list());
        model.addAttribute("suppliers", supplierService.list());
        model.addAttribute("manufacturers", manufacturerService.list());
        model.addAttribute("products", productService.list());

        return "admin/products-admin";
    }

    @GetMapping(path = "image/{id}", produces = "images/jpeg")
    @ResponseBody
    public byte[] getImage(@PathVariable long id) {
        return productService.getOne(id).getProductImg();
    }

    @PostMapping("/products-admin")
    public String addProduct(@RequestParam String name, @RequestParam String description,
                             @RequestParam String price, @RequestParam(value = "file", required = false) MultipartFile file,
                             @RequestParam Long departmentId, @RequestParam Long supplierId,
                             @RequestParam Long manufacturerId) throws IOException {

        productService.add(name, description, price,
                file, departmentId, supplierId, manufacturerId);

        return "redirect:/products-admin";
    }

    @GetMapping("/product-edit/{productId}")
    public String getProduct(Model model, @PathVariable(value = "productId") long productId) {
        Product product = this.productService.getOne(productId);
        model.addAttribute("product", product);

        model.addAttribute("departments", departmentService.list());
        model.addAttribute("suppliers", supplierService.list());
        model.addAttribute("manufacturers", manufacturerService.list());
        return "admin/product-edit";
    }

    @PostMapping("/product-edit/{productId}")
    public String updateProduct(@PathVariable(value = "productId") long id,
                                @RequestParam String name, @RequestParam String description,
                                @RequestParam String price, @RequestParam(value = "file", required = false) MultipartFile file,
                                @RequestParam Long departmentId, @RequestParam Long supplierId,
                                @RequestParam Long manufacturerId) throws IOException {
        this.productService.change(id, name, description, price, file,
                departmentId, supplierId, manufacturerId);

        return "redirect:/product-edit/{productId}";
    }


    @PostMapping("/products-admin/{productId}")
    public String delete(@PathVariable(value = "productId") long productId) {
        this.productService.remove(productId);
        return "redirect:/products-admin";
    }

    //    Methods for client page
    @GetMapping("/products")
    public String showProducts(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               @RequestParam("id") long departmentId,
                               @Param("manufacturerId") String manufacturerId) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(18);

        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize),
                departmentId, manufacturerId);
        Department department = departmentService.getOne(departmentId);

        model.addAttribute("departmentName", department.getName());
        model.addAttribute("productPage", productPage);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("manufacturers", this.manufacturerService.list());
        if (manufacturerId != null && !manufacturerId.equals("all")){
            int selectedManufacturerId = Integer.parseInt(manufacturerId);
            model.addAttribute("selectedManufacturerId", selectedManufacturerId);
        }


        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "public/products";
    }

    @GetMapping("/product-page/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") long productId,
                                 @Param(value = "depId") long depId) {
        model.addAttribute("depId", depId);
        model.addAttribute("product", productService.getOne(productId));
        return "public/product-page";
    }

}
