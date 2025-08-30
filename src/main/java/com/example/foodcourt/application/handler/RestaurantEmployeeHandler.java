package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.RestaurantEmployeeRequestDto;
import com.example.foodcourt.application.mapper.IRestaurantEmployeeRequestMapper;
import com.example.foodcourt.domain.api.IRestaurantEmployeeServicePort;
import com.example.foodcourt.domain.exception.CustomAuthenticationException;
import com.example.foodcourt.domain.utils.constant.Constants;
import com.example.foodcourt.infrastructure.security.JwtUtil;
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
        if (requestAttributes == null) {
            throw new CustomAuthenticationException(Constants.NO_REQUEST_CONTEXT);
        }

        var request = requestAttributes.getRequest();
        String authHeader = request.getHeader(Constants.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(Constants.BEARER)) {
            throw new CustomAuthenticationException(Constants.INVALID_AUTH_HEADER);
        }

        String token = authHeader.substring(Constants.BEARER.length());
        String documentFromToken = jwtUtil.getDocument(token);

        restaurantEmployeeServicePort.saveRestaurantEmployee(
                restaurantEmployeeRequestMapper.toRestaurantEmployee(requestDto),
                documentFromToken
        );
    }
}
