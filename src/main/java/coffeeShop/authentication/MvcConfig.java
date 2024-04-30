package coffeeShop.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("public/home");
        registry.addViewController("/").setViewName("public/home");
        registry.addViewController("/products").setViewName("public/products");
        registry.addViewController("/product-page").setViewName("public/product-page");
        registry.addViewController("/login").setViewName("public/login");
        registry.addViewController("/administrator").setViewName("public/administrator");
        registry.addViewController("/suppliers").setViewName("public/suppliers");
        registry.addViewController("/manufacturers").setViewName("public/manufacturers");
        registry.addViewController("/departments").setViewName("public/departments");
        registry.addViewController("/products-admin").setViewName("public/products-admin");



    }
}
