package com.example.msFoodCourt.infrastructure.output.jpa.adapter.client;

import com.example.msFoodCourt.application.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantUserFeignAdapter {

    private final UserFeignClient userFeignClient;

    public boolean existsOwnerById(Long idOwner) {
        try {
            UserResponse user = userFeignClient.getUserById(idOwner);
            return user != null && "OWNER".equalsIgnoreCase(user.getRole());
        } catch (Exception e) {
            return false;
        }
    }
}
