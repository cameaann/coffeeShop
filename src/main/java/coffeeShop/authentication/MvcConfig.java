package coffeeShop.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("public/home");
        registry.addViewController("/").setViewName("public/home");
        registry.addViewController("/customer-registration").setViewName("public/customer-registration");
        registry.addViewController("/success-registration").setViewName("public/success-registration");
        registry.addViewController("/products").setViewName("public/products");
        registry.addViewController("/product-page").setViewName("public/product-page");
        registry.addViewController("/login").setViewName("public/login");
        registry.addViewController("/administrator").setViewName("admin/administrator");
        registry.addViewController("/suppliers").setViewName("admin/suppliers");
        registry.addViewController("/manufacturers").setViewName("admin/manufacturers");
        registry.addViewController("/departments").setViewName("admin/departments");
        registry.addViewController("/products-admin").setViewName("admin/products-admin");



    }
}
