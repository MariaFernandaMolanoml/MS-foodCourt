package com.example.foodcourt.infrastructure.output.jpa.adapter;

import com.example.foodcourt.domain.model.RestaurantEmployee;
import com.example.foodcourt.domain.spi.IRestaurantEmployeePersistencePort;
import com.example.foodcourt.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import com.example.foodcourt.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;

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
    @Override
    public String findRestaurantNitByEmployeeDocument(String employeeDocument) {
        return repository.findByEmployeeDocument(employeeDocument)
                .map(RestaurantEmployeeEntity::getRestaurantNit)
                .orElseThrow(() -> new RuntimeException("No restaurant found for employee document " + employeeDocument));
    }
}
