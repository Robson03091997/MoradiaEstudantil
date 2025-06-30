package sistema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SistemaLocacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaLocacaoApplication.class, args);
        System.out.println("🚀 Sistema de Locação de Imóveis iniciado!");
        System.out.println("🌐 API disponível em: http://localhost:8080");
        System.out.println("📚 Documentação: http://localhost:8080/swagger-ui.html");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "https://seu-frontend.vercel.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
} 