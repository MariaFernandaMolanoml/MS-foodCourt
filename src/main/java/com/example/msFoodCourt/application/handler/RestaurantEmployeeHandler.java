package com.example.msFoodCourt.application.handler;

import com.example.msFoodCourt.application.dto.RestaurantEmployeeRequestDto;
import com.example.msFoodCourt.application.mapper.IRestaurantEmployeeRequestMapper;
import com.example.msFoodCourt.domain.api.IRestaurantEmployeeServicePort;
import com.example.msFoodCourt.domain.utils.constant.Constants;
import com.example.msFoodCourt.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@RequiredArgsConstructor
public class RestaurantEmployeeHandler implements IRestaurantEmployeeHandler {

    private final IRestaurantEmployeeServicePort restaurantEmployeeServicePort;
    private final IRestaurantEmployeeRequestMapper restaurantEmployeeRequestMapper;
    private final JwtUtil jwtUtil;

    @Override
    public void saveRestaurantEmployee(RestaurantEmployeeRequestDto requestDto) {
        var requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        var request = requestAttributes.getRequest();
        String authHeader = request.getHeader(Constants.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(Constants.BEARER)) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String documentFromToken = jwtUtil.getDocument(token);

        restaurantEmployeeServicePort.saveRestaurantEmployee(
                restaurantEmployeeRequestMapper.toRestaurantEmployee(requestDto),
                documentFromToken
        );
    }
}