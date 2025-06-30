package sistema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perfil.Perfil;
import sistema.UserRepository;
import sistema.models.LoginRequest;
import sistema.models.RegisterRequest;
import sistema.models.LoginResponse;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = {"http://localhost:3000", "https://seu-frontend.vercel.app"})
public class UsuarioController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Optional<Perfil> perfil = userRepository.autenticarUsuario(request.getEmail(), request.getSenha());
            
            if (perfil.isPresent()) {
                LoginResponse response = new LoginResponse();
                response.setSuccess(true);
                response.setMessage("Login realizado com sucesso!");
                response.setUsuario(perfil.get());
                return ResponseEntity.ok(response);
            } else {
                LoginResponse response = new LoginResponse();
                response.setSuccess(false);
                response.setMessage("Email ou senha incorretos!");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setMessage("Erro interno: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            boolean sucesso = userRepository.cadastrarUsuario(
                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                request.getTelefone(),
                request.getGenero()
            );
            
            if (sucesso) {
                return ResponseEntity.ok("Usuário cadastrado com sucesso!");
            } else {
                return ResponseEntity.badRequest().body("Erro ao cadastrar usuário!");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno: " + e.getMessage());
        }
    }

    @GetMapping("/perfil/{email}")
    public ResponseEntity<?> getPerfil(@PathVariable String email) {
        try {
            // Implementar busca por email
            return ResponseEntity.ok("Perfil encontrado");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno: " + e.getMessage());
        }
    }
} 