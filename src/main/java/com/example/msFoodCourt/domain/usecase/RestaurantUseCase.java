package com.example.msFoodCourt.domain.usecase;

import com.example.msFoodCourt.domain.api.IRestaurantServicePort;
import com.example.msFoodCourt.domain.exception.*;
import com.example.msFoodCourt.domain.model.Restaurant;
import com.example.msFoodCourt.domain.model.User;
import com.example.msFoodCourt.domain.spi.IRestaurantPersistencePort;
import com.example.msFoodCourt.domain.spi.IUserPersistencePort;

import java.util.List;

import static com.example.msFoodCourt.domain.utils.constant.Constants.ROLE_OWNER;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort persistencePort;
    private final IUserPersistencePort userPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort persistencePort, IUserPersistencePort userPersistencePort) {
        this.persistencePort = persistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        User user = userPersistencePort.getUserByDocument(restaurant.getDocumentOwner());

        validateRequiredFields(restaurant);
        validateOwnerDocument(user);
        validateName(restaurant.getName());
        validateNumeric(restaurant.getNit(), "NIT");
        validatePhone(restaurant.getPhone());

        if (persistencePort.existsByNit(restaurant.getNit())) {
            throw new DuplicateNitException("A restaurant with NIT " + restaurant.getNit() + " already exists");
        }

        persistencePort.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return persistencePort.findAll();
    }

    @Override
    public Restaurant getRestaurantByNit(String nit) {
        return null;
    }

    private void validateRequiredFields(Restaurant r) {
        if (r.getName() == null || r.getNit() == null || r.getAddress() == null ||
                r.getPhone() == null || r.getUrlLogo() == null || r.getDocumentOwner() == null) {
            throw new MissingFieldsException("All fields are required");
        }
    }

    private void validateOwnerDocument(User user) {
        if (user == null) {
            throw new OwnerNotFoundException("The entered document does not exist in the Users service");
        }
        if (!ROLE_OWNER.equalsIgnoreCase(user.getRole())) {
            throw new OwnerNotFoundException("The entered document does not correspond to an OWNER user");
        }
    }

    private void validateName(String name) {
        if (name.matches("\\d+")) {
            throw new InvalidNameException("The name cannot contain only numbers");
        }
    }

    private void validateNumeric(String value, String field) {
        if (!value.matches("\\d+")) {
            throw new InvalidNitException(field + " must be numeric");
        }
    }

    private void validatePhone(String phone) {
        if (!phone.matches("\\+?\\d{1,13}")) {
            throw new InvalidPhoneException("Invalid phone format");
        }
    }
}