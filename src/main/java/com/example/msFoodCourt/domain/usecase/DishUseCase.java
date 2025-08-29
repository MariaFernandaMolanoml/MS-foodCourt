package com.example.msFoodCourt.domain.usecase;

import com.example.msFoodCourt.domain.api.IDishServicePort;
import com.example.msFoodCourt.domain.exception.*;
import com.example.msFoodCourt.domain.model.Dish;
import com.example.msFoodCourt.domain.model.DishUpdate;
import com.example.msFoodCourt.domain.spi.IDishPersistencePort;
import com.example.msFoodCourt.domain.spi.IRestaurantPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class DishUseCase implements IDishServicePort {


    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveDish(Dish dish, String documentFromToken) {
        validateDish(dish);

        var restaurant = restaurantPersistencePort.findById(dish.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + dish.getRestaurantId()));

        if (!restaurant.getDocumentOwner().equals(documentFromToken)) {
            throw new UnauthorizedOwnerException("You are not the owner of this restaurant");
        }

        if (dish.getActive() == null) {
            dish.setActive(true);
        }

        dishPersistencePort.saveDish(dish);
    }


    @Override
    public Optional<Dish> getDishById(Long id) {
        return Optional.ofNullable(dishPersistencePort.getDish(id));
    }

    @Override
    public List<Dish> getAllDish() {
        return dishPersistencePort.getAllDish();
    }

    @Override
    public Dish getDish(Long id) {
        return dishPersistencePort.getDish(id);
    }

    @Override
    public List<Dish> getDishesByRestaurant(Long restaurantId) {
        return List.of();
    }

    @Override
    public void updateDish(DishUpdate dishUpdate, String documentFromToken) {
        if (dishUpdate.getPrice() == null
                && dishUpdate.getDescription() == null
                && dishUpdate.getActive() == null) {
            throw new UpdateDishException();
        }

        if (!dishPersistencePort.existById(dishUpdate.getId())) {
            throw new DishNotFoundException("Dish not found with id: " + dishUpdate.getId());
        }

        Dish dish = dishPersistencePort.getDish(dishUpdate.getId());

        var restaurant = restaurantPersistencePort.findById(dish.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(
                        "Restaurant not found with id: " + dish.getRestaurantId()
                ));

        if (!restaurant.getDocumentOwner().equals(documentFromToken)) {
            throw new UnauthorizedOwnerException("You are not the owner of this restaurant");
        }

        if (dishUpdate.getPrice() != null) {
            if (dishUpdate.getPrice() <= 0) {
                throw new DishPriceNotValidException();
            }
            dish.setPrice(dishUpdate.getPrice());
        }

        if (dishUpdate.getDescription() != null && !dishUpdate.getDescription().isBlank()) {
            dish.setDescription(dishUpdate.getDescription());
        }

        if (dishUpdate.getActive() != null) {
            dish.setActive(dishUpdate.getActive());
        }

        dishPersistencePort.updateDish(dish);
    }

    @Override
    public void deleteDish(Long id) {
        dishPersistencePort.deleteDish(id);
    }

    private void validateDish(Dish dish) {
        if (dish.getName() == null || dish.getName().isBlank()) {
            throw new DishNameNotFoundException();
        }
        if (dish.getDescription() == null || dish.getDescription().isBlank()) {
            throw new DishDescriptionNotFoundException();
        }
        if (dish.getPrice() == null || dish.getPrice() <= 0) {
            throw new DishPriceNotValidException();
        }
        if (dish.getRestaurantId() == null) {
            throw new DishRestaurantNotFoundException();
        }
        if (dish.getCategory() == null) {
            throw new DishCategoryNotFoundException();
        }
    }
    @Override
    public Page<Dish> findAll(Pageable pageable) {
        return dishPersistencePort.findAll(pageable);
    }
}
