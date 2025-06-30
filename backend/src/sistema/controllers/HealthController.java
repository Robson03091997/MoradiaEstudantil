package sistema.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "https://seu-frontend.netlify.app"})
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Sistema de Locação de Imóveis");
        response.put("version", "1.0.0");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "Sistema de Locação de Imóveis");
        response.put("description", "API para sistema de locação de imóveis");
        response.put("version", "1.0.0");
        response.put("author", "MC322 - Unicamp");
        response.put("endpoints", new String[]{
            "/api/health",
            "/api/usuarios/login",
            "/api/usuarios/register",
            "/api/locais",
            "/api/estatisticas"
        });
        return response;
    }
} 