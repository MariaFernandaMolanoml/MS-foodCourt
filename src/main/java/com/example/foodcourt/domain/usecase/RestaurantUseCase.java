package com.example.foodcourt.domain.usecase;

import com.example.foodcourt.domain.api.IRestaurantServicePort;
import com.example.foodcourt.domain.exception.DuplicateNitException;
import com.example.foodcourt.domain.exception.InvalidNameException;
import com.example.foodcourt.domain.exception.InvalidNitException;
import com.example.foodcourt.domain.exception.InvalidPhoneException;
import com.example.foodcourt.domain.exception.MissingFieldsException;
import com.example.foodcourt.domain.exception.OwnerNotFoundException;
import com.example.foodcourt.domain.model.Restaurant;
import com.example.foodcourt.domain.model.User;
import com.example.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.example.foodcourt.domain.spi.IUserPersistencePort;

import java.util.List;

import static com.example.foodcourt.domain.utils.constant.Constants.ROLE_OWNER;

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
        validateNumeric(restaurant.getNit());
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
            throw new MissingFieldsException();
        }
    }

    private void validateOwnerDocument(User user) {
        if (user == null) {
            throw new OwnerNotFoundException();
        }
        if (!ROLE_OWNER.equalsIgnoreCase(user.getRole())) {
            throw new OwnerNotFoundException();
        }
    }

    private void validateName(String name) {
        if (name.matches("\\d+")) {
            throw new InvalidNameException();
        }
    }

    private void validateNumeric(String value) {
        if (!value.matches("\\d+")) {
            throw new InvalidNitException();
        }
    }

    private void validatePhone(String phone) {
        if (!phone.matches("\\+?\\d{1,13}")) {
            throw new InvalidPhoneException();
        }
    }
}