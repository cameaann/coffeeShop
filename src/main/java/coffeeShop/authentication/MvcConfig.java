package coffeeShop.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("public/home");
        registry.addViewController("/").setViewName("public/home");
        registry.addViewController("/customer-registration").setViewName("public/customer-registration");
        registry.addViewController("/success-registration").setViewName("public/success-registration");
        registry.addViewController("/products").setViewName("public/products");
        registry.addViewController("/product-page/{id}").setViewName("public/product-page");
        registry.addViewController("/login").setViewName("public/login");
        registry.addViewController("/suppliers").setViewName("admin/suppliers");
        registry.addViewController("/manufacturers").setViewName("admin/manufacturers");
        registry.addViewController("/departments").setViewName("admin/departments");
        registry.addViewController("/products-admin").setViewName("admin/products-admin");

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("../fragments/**")
                .addResourceLocations("classpath:/templates/fragments/**");
        registry.addResourceHandler("../public/**")
                .addResourceLocations("classpath:/templates/public/**");
        registry.addResourceHandler("../admin/**")
                .addResourceLocations("classpath:/templates/admin/**");

    }
}
