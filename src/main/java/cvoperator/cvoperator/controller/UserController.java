package cvoperator.cvoperator.controller;

import cvoperator.cvoperator.entity.User;
import cvoperator.cvoperator.model.LoginRequest;
import cvoperator.cvoperator.model.Loginresponse;
import cvoperator.cvoperator.service.AuthService;
import cvoperator.cvoperator.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public UserController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if(authService.registrtion(user)){
            return ResponseEntity.ok("Utente registrato con successo ");
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Errore nell'aggiunta dell'utente");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Loginresponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        boolean check = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if(check){
            Loginresponse loginresponse = new Loginresponse();
            loginresponse.setMessage("Utente loggato");
            loginresponse.setToken(jwtUtil.generateToken(loginRequest.getEmail()));
            return ResponseEntity.ok(loginresponse);
        }else {
            Loginresponse loginresponse = new Loginresponse();
            loginresponse.setMessage("Errore");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(loginresponse);
        }
    }

    @GetMapping("/info")
    public ResponseEntity<User> getUser(@RequestParam String email) {
        User user = authService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        authService.deleteUser(id);
        return ResponseEntity.ok("Utente eliminato");
    }
}
