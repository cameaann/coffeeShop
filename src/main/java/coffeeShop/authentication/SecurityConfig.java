//package coffeeShop.authentication;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    public static final String LOGIN_URL = "/login";
//    public static final String LOGOUT_URL = "/logout";
//    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
//    public static final String DEFAULT_SUCCESS_URL = "/admin-panel";
//    public static final String USERNAME = "foo";
//    public static final String PASSWORD = "foo";
//    @Bean
//    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
//
//
//        // http builder configurations for authorize requests and form login
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/", "/products", "/login", "/suppliers", "/h2-console").permitAll()
//                        .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(form -> form
//                        .loginPage(LOGIN_URL)
//                        .loginProcessingUrl(LOGIN_URL)
//                        .failureUrl(LOGIN_FAIL_URL)
//                        .usernameParameter(USERNAME)
//                        .passwordParameter(PASSWORD)
//                        .defaultSuccessUrl(DEFAULT_SUCCESS_URL));
////                .csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//        ProviderManager providerManager = new ProviderManager(authenticationProvider);
//        providerManager.setEraseCredentialsAfterAuthentication(false);
//
//        return providerManager;
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//        .username("foo")
//                .password("foo")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
////        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
////    @Bean
////    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
////        return new HandlerMappingIntrospector();
////    }
//
//
//}
