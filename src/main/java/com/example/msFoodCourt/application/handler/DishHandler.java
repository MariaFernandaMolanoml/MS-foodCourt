package com.example.msFoodCourt.application.handler;

import com.example.msFoodCourt.application.dto.DishRequest;
import com.example.msFoodCourt.application.dto.DishResponse;
import com.example.msFoodCourt.application.dto.DishUpdateRequest;
import com.example.msFoodCourt.application.mapper.DishRequestMapper;
import com.example.msFoodCourt.application.mapper.DishResponseMapper;
import com.example.msFoodCourt.domain.api.IDishServicePort;
import com.example.msFoodCourt.domain.model.Dish;
import com.example.msFoodCourt.domain.model.DishUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final DishRequestMapper dishRequestMapper;
    private final DishResponseMapper dishResponseMapper;

    @Override
    public void saveDish(DishRequest dishRequest) {
        Dish dish = dishRequestMapper.toDish(dishRequest);
        dishServicePort.saveDish(dish);
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
    public void updateDish(DishRequest dishRequest) {
        Dish dish = dishRequestMapper.toDish(dishRequest);
        dishServicePort.updateDish(dish);
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

        dishServicePort.updateDish(dishUpdate);
    }
}
