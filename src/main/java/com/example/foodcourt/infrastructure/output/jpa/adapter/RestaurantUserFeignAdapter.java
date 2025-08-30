package com.example.foodcourt.infrastructure.output.jpa.adapter;

import com.example.foodcourt.application.dto.UserResponse;
import com.example.foodcourt.domain.model.User;
import com.example.foodcourt.domain.spi.IUserPersistencePort;
import com.example.foodcourt.infrastructure.output.jpa.adapter.client.UserFeignClient;
import lombok.RequiredArgsConstructor;

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
