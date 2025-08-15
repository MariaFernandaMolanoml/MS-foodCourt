package com.example.msFoodCourt.domain.usecase;

import com.example.msFoodCourt.domain.api.IRestaurantServicePort;
import com.example.msFoodCourt.domain.model.Restaurant;
import com.example.msFoodCourt.domain.spi.IRestaurantPersistencePort;
import com.example.msFoodCourt.infrastructure.exception.BadRequestException;
import com.example.msFoodCourt.infrastructure.exception.NotFoundException;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort persistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        validateRequiredFields(restaurant);
        validateOwnerId(restaurant.getIdOwner());
        validateName(restaurant.getName());
        validateNumeric(restaurant.getNit(), "NIT");
        validatePhone(restaurant.getPhone());

        persistencePort.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return persistencePort.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return persistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant not found with ID " + id));
    }

    private void validateRequiredFields(Restaurant r) {
        if (r.getName() == null || r.getNit() == null || r.getAddress() == null ||
                r.getPhone() == null || r.getUrlLogo() == null || r.getIdOwner() == null) {
            throw new BadRequestException("All fields are required");
        }
    }

    private void validateOwnerId(Long idOwner) {
        if (!persistencePort.existsOwnerById(idOwner)) {
            throw new NotFoundException("The owner ID does not correspond to a user with the OWNER role");
        }
    }

    private void validateName(String name) {
        if (name.matches("\\d+")) {
            throw new BadRequestException("The name cannot contain only numbers");
        }
    }

    private void validateNumeric(String value, String field) {
        if (!value.matches("\\d+")) {
            throw new BadRequestException(field + " must be numeric");
        }
    }

    private void validatePhone(String phone) {
        if (!phone.matches("\\+?\\d{1,13}")) {
            throw new BadRequestException("Invalid phone format");
        }
    }
}
