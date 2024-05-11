package coffeeShop.services;

import coffeeShop.models.Department;
import coffeeShop.models.Manufacturer;
import coffeeShop.models.Product;
import coffeeShop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ManufacturerService manufacturerService;

//    final private List<Product> products = new ArrayList<>();
    public Iterable<Product> list() {
        return productRepository.findAll();
    }

    @SneakyThrows
    @Transactional
    public void add(String name, String description, String price,
                    MultipartFile img,
                    Long departmentId, Long supplierId,
                    Long manufacturerId) {
        Product product = new Product();
        Product readyProduct = setProductProperties(name, description, price, img,
                departmentId, supplierId, manufacturerId, product);
        productRepository.save(readyProduct);
    }

    private Product setProductProperties(String name, String description,
                                      String price, MultipartFile img,
                                      Long departmentId, Long supplierId,
                                      Long manufacturerId, Product product) throws IOException {
        product.setName(name);
        product.setDescription(description);
        product.setPrice(new BigDecimal(price));

        if (!img.isEmpty() && isAllowedImageType(img.getContentType())) {
                product.setProductImg(img.getBytes());
                product.setProductImgName(img.getOriginalFilename());
        }
        product.setDepartment(departmentService.getOne(departmentId));
        product.setSupplier(supplierService.getOne(supplierId));
        product.setManufacturer(manufacturerService.getOne(manufacturerId));
        return product;
    }

    private boolean isAllowedImageType(String contentType) {
        return contentType != null && (contentType.startsWith("image/jpeg") ||
                contentType.startsWith("image/png") ||
                contentType.startsWith("image/webp") ||
                contentType.startsWith("image/gif"));
    }


    public Product getOne(Long id){
        return productRepository.getReferenceById(id);
    }

    @Transactional
    public void change(Long id, String name, String description, String price,
                       MultipartFile img,
                       Long departmentId, Long supplierId,
                       Long manufacturerId) throws IOException{
        Product product = this.productRepository.getReferenceById(id);
        Product readyProduct = setProductProperties(name, description, price, img, departmentId,
                supplierId, manufacturerId, product);

        this.productRepository.save(readyProduct);
    }

    @Transactional
    public void remove(long id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Supplier with ID " + id + " does not exist");
        }
    }

    public Page<Product> findPaginated(Pageable pageable, long departmentId, String brandId) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> products = new ArrayList<>();
        Department department = departmentService.getOne(departmentId);
        List<Department>departmentTree = getDepartmentsTree(department);

        if(brandId != null && !brandId.equals("all")){
            Manufacturer manufacturer = manufacturerService.getOne(Long.parseLong(brandId));
            for (Department dep : departmentTree) {
                products.addAll(productRepository.findAllByDepartmentAndManufacturer(dep, manufacturer));
            }
        }else{
            for (Department dep : departmentTree) {
                products.addAll(productRepository.findAllByDepartment(dep));
            }
        }

        List<Product> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        return new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize),
                products.size());
    }

    private List<Department> getDepartmentsTree(Department department){
        List<Department> departments = new ArrayList<>();
        departments.add(department);
        for (Department child : department.getChildren()) {
            departments.addAll(getDepartmentsTree(child));
        }
        return departments;
    }


}
