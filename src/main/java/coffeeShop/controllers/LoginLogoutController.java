package coffeeShop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {
//    private final AuthenticationManager authenticationManager;
//
//    public LoginController(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    @GetMapping("/login")
    public String login(){
        return "public/login";
    }

    @GetMapping("/logout")
    public String logout(){return "public/home";}

//    @PostMapping("/login")
//    public ResponseEntity<Void>  login(@RequestBody LoginRequest loginRequest) {
//        UsernamePasswordAuthenticationToken authenticationRequest =
//                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
//        Authentication authenticationResponse =
//                this.authenticationManager.authenticate(authenticationRequest);
////       return authenticationResponse.isAuthenticated()
//        return ResponseEntity.ok().build();
//    }

//
//    public record LoginRequest(String username, String password) {
//        public String getUsername() {
//            return username;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//    }
}
