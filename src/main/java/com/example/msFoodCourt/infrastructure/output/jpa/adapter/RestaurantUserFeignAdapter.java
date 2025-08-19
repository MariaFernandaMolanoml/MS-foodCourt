package com.example.msFoodCourt.infrastructure.output.jpa.adapter;

import com.example.msFoodCourt.application.dto.UserResponse;
import com.example.msFoodCourt.domain.model.User;
import com.example.msFoodCourt.domain.spi.IUserPersistencePort;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantUserFeignAdapter implements IUserPersistencePort {

    private final UserFeignClient userFeignClient;

    @Override
    public User getUserByDocument(String document) {
        UserResponse userResponse = userFeignClient.getUserByDocument(document);
        return toDomainUserInfo(userResponse);
    }

    public User toDomainUserInfo(UserResponse userResponse) {
        return new User(userResponse.getId(),
                userResponse.getName(),
                userResponse.getLastName(),
                userResponse.getEmail(),
                userResponse.getDocument(),
                userResponse.getPhone(),
                userResponse.getRole());
    }
}
