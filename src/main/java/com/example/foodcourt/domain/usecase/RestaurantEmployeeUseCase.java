package com.example.foodcourt.domain.usecase;

import com.example.foodcourt.domain.api.IRestaurantEmployeeServicePort;
import com.example.foodcourt.domain.exception.InvalidRoleException;
import com.example.foodcourt.domain.exception.RestaurantNotFoundException;
import com.example.foodcourt.domain.exception.UnauthorizedOwnerException;
import com.example.foodcourt.domain.exception.UserNotFoundException;
import com.example.foodcourt.domain.model.Restaurant;
import com.example.foodcourt.domain.model.RestaurantEmployee;
import com.example.foodcourt.domain.model.User;
import com.example.foodcourt.domain.spi.IRestaurantEmployeePersistencePort;
import com.example.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.example.foodcourt.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

import static com.example.foodcourt.domain.utils.constant.Constants.ROLE_EMPLOYEE;

@RequiredArgsConstructor
public class RestaurantEmployeeUseCase implements IRestaurantEmployeeServicePort {

    private final IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    @Override
    public void saveRestaurantEmployee(RestaurantEmployee restaurantEmployee, String documentFromToken) {

        Restaurant restaurant = restaurantPersistencePort.findByNit(restaurantEmployee.getRestaurantNit())
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with NIT: " + restaurantEmployee.getRestaurantNit()));

        if (!restaurant.getDocumentOwner().equals(documentFromToken)) {
            throw new UnauthorizedOwnerException("You are not the owner of this restaurant");
        }
        User employee = userPersistencePort.getUserByDocument(restaurantEmployee.getEmployeeDocument());
        validateEmployeeDocument(employee);
        restaurantEmployeePersistencePort.saveRestaurantEmployee(restaurantEmployee);
    }
    @Override
    public String findRestaurantNitByEmployeeDocument(String employeeDocument) {
        return restaurantEmployeePersistencePort.findRestaurantNitByEmployeeDocument(employeeDocument);
    }

    private void validateEmployeeDocument(User user) {
        if (user == null) {
            throw new UserNotFoundException("The entered document does not exist in the Users service");
        }
        if (!ROLE_EMPLOYEE.equalsIgnoreCase(user.getRole())) {
            throw new InvalidRoleException("The user is not an EMPLOYEE");
        }
    }
}
