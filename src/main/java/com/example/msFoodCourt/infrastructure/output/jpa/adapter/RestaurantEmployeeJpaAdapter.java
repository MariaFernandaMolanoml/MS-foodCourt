package com.example.msFoodCourt.infrastructure.output.jpa.adapter;

import com.example.msFoodCourt.domain.model.RestaurantEmployee;
import com.example.msFoodCourt.domain.spi.IRestaurantEmployeePersistencePort;
import com.example.msFoodCourt.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import com.example.msFoodCourt.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantEmployeeJpaAdapter implements IRestaurantEmployeePersistencePort {

    private final IRestaurantEmployeeRepository repository;

    @Override
    public void saveRestaurantEmployee(RestaurantEmployee restaurantEmployee) {
        RestaurantEmployeeEntity entity = new RestaurantEmployeeEntity();
        entity.setRestaurantNit(restaurantEmployee.getRestaurantNit());
        entity.setEmployeeDocument(restaurantEmployee.getEmployeeDocument());
        repository.save(entity);
    }
}
