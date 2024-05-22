package coffeeShop.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;

    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String USERNAME = "Admin";
    public static final String PASSWORD = "Admin";

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {


        // http builder configurations for authorize requests and form login
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/home", "/customer-registration", "/success-registration",
                                "/products", "/product-page/{id}", "/login", "/cart", "/home/cart", "/product/cart",
                                "/cart/increase/{itemId}", "/cart/decrease/{itemId}", "/vip-customers").permitAll()
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
//                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/header.html", "/head.html").permitAll()
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/image/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage(LOGIN_URL)
                        .permitAll())
                .logout(logout -> logout.logoutSuccessHandler(logoutSuccessHandler).permitAll())
                .headers(headers -> headers.disable());
//                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")));
        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username(USERNAME)
                .password(PASSWORD)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

}
