import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Dummy data user
    private final String validUsername = "testuser";
    private final String validPassword = "testpassword";
    private String validToken = null;

    // class response
    static class AuthResponse {
        public String status;
        public String message;
        public String data;

        public AuthResponse(String status, String message, String data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }
    }

    // Endpoint Login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestParam String username, @RequestParam String password) {
        if (validUsername.equals(username) && validPassword.equals(password)) {
            validToken = "mocked-token-" + System.currentTimeMillis();
            return ResponseEntity.ok(new AuthResponse("SUCCESS", "Login successful", validToken));
        }
        return ResponseEntity.ok(new AuthResponse("FAILURE", "Invalid username or password", null));
    }

    // Endpoint Authenticate
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestParam String token) {
        if (validToken != null && validToken.equals(token)) {
            return ResponseEntity.ok(new AuthResponse("SUCCESS", "Token is valid", "Authenticated"));
        }
        return ResponseEntity.ok(new AuthResponse("FAILURE", "Invalid token", null));
    }
}
