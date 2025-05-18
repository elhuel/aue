package org.example.coworking_rent.client;

import org.example.coworking_rent.dto.AuthUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    name = "auth-service",
    url = "${auth.service.url}"
)
public interface AuthClient {
    
    @PostMapping("/validate")
    AuthUserResponse validateToken(
        @RequestHeader("Authorization") String token
    );
}