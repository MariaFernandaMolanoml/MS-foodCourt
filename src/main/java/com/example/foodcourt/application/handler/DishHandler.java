package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.DishListDto;
import com.example.foodcourt.application.dto.DishRequest;
import com.example.foodcourt.application.dto.DishResponse;
import com.example.foodcourt.application.dto.DishUpdateRequest;
import com.example.foodcourt.application.mapper.DishListMapper;
import com.example.foodcourt.application.mapper.DishRequestMapper;
import com.example.foodcourt.application.mapper.DishResponseMapper;
import com.example.foodcourt.domain.api.IDishServicePort;
import com.example.foodcourt.domain.exception.MissingRequestContextException;
import com.example.foodcourt.domain.model.Dish;
import com.example.foodcourt.domain.model.DishUpdate;
import com.example.foodcourt.domain.utils.constant.Constants;
import com.example.foodcourt.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final DishRequestMapper dishRequestMapper;
    private final DishResponseMapper dishResponseMapper;
    private final DishListMapper dishListMapper;
    private final JwtUtil jwtUtil;

    @Override
    public void saveDish(DishRequest dishRequest) {
        Dish dish = dishRequestMapper.toDish(dishRequest);
        String token = extractTokenFromRequest();
        String documentFromToken = jwtUtil.getDocument(token);

        dishServicePort.saveDish(dish, documentFromToken);
    }

    @Override
    public List<DishResponse> getAllDishes() {
        List<Dish> dishes = dishServicePort.getAllDish();
        return dishResponseMapper.toResponseList(dishes);
    }

    @Override
    public DishResponse getDish(Long id) {
        Dish dish = dishServicePort.getDish(id);
        return dishResponseMapper.toResponse(dish);
    }

    @Override
    public void deleteDish(Long id) {
        dishServicePort.deleteDish(id);
    }

    @Override
    public List<DishResponse> getDishesByRestaurant(Long restaurantId) {
        List<Dish> dishes = dishServicePort.getDishesByRestaurant(restaurantId);
        return dishResponseMapper.toResponseList(dishes);
    }

    @Override
    public void updateDish(Long id, DishUpdateRequest dishUpdateRequest) {
        DishUpdate dishUpdate = new DishUpdate();
        dishUpdate.setId(id);
        dishUpdate.setDescription(dishUpdateRequest.getDescription());
        dishUpdate.setPrice(dishUpdateRequest.getPrice());
        dishUpdate.setActive(dishUpdateRequest.getActive());

        String token = extractTokenFromRequest();
        String documentFromToken = jwtUtil.getDocument(token);

        dishServicePort.updateDish(dishUpdate, documentFromToken);
    }

    @Override
    public Page<DishListDto> listDishes(int page, int size) {
        var pageable = PageRequest.of(page, size);
        var dishes = dishServicePort.findAll(pageable);

        var dtoList = dishes.getContent()
                .stream()
                .map(dishListMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, dishes.getTotalElements());
    }

    private String extractTokenFromRequest() {
        var requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new MissingRequestContextException("No request context available");
        }

        var request = requestAttributes.getRequest();
        String authHeader = request.getHeader(Constants.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(Constants.BEARER)) {
            throw new MissingRequestContextException("Missing or invalid Authorization header");
        }

        return authHeader.substring(Constants.BEARER.length()).trim();
    }
}
