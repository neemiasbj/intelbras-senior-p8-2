package br.com.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class HttpConfig {
    
    private static final List<String> ALLOWED_HEADERS = List.of("Access-Control-Allow-Origin", "x-requested-with");
    
    private static final List<String> ALLOWED_METHODS = List.of("POST", "GET", "PUT", "DELETE");
    
    private static final List<String> ALLOWED_ALL = List.of("http://127.0.0.1:8081", "http://127.0.0.1:8082", "http://144.24.171.248:8095",
                                                            "http://144.24.171.248:8096", "https://demos.springdoc.org");
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ALLOWED_ALL);
        configuration.setAllowedMethods(ALLOWED_METHODS);
        configuration.setAllowedHeaders(ALLOWED_HEADERS);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
}
