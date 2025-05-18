package org.example.coworking_rent.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Configuration
public class FeignConfig {
    
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    public static class FeignErrorDecoder implements ErrorDecoder {
        
        @Override
        public Exception decode(String methodKey, Response response) {
            return switch (response.status()) {
                case 401 -> new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, 
                    "Authentication failed"
                );
                case 404 -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Resource not found"
                );
                default -> new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal server error"
                );
            };
        }
    }
}