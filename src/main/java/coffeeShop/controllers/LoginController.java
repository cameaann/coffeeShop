package coffeeShop;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
public class LoginController {
//    private final AuthenticationManager authenticationManager;
//
//    public LoginController(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    @GetMapping("/login")
    public String login(){
        return "public/login";
    }

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
