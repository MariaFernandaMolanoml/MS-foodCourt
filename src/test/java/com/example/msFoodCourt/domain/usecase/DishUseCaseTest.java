package com.example.msFoodCourt.domain.usecase;

import com.example.msFoodCourt.domain.exception.*;
import com.example.msFoodCourt.domain.model.Dish;
import com.example.msFoodCourt.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    private Dish dish;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dish = new Dish();
        dish.setId(1L);
        dish.setName("Pizza");
        dish.setDescription("Delicious pizza");
        dish.setPrice(20.0);
        dish.setRestaurantId(1L);
        dish.setCategoryId(1L);
        dish.setActive(true);
    }

    @Test
    void saveDish_ShouldCallPersistence_WhenDishIsValid() {
        dishUseCase.saveDish(dish);
        verify(dishPersistencePort, times(1)).saveDish(dish);
    }

    @Test
    void saveDish_ShouldSetActiveTrue_WhenActiveIsNull() {
        dish.setActive(null);
        dishUseCase.saveDish(dish);
        assertTrue(dish.getActive());
        verify(dishPersistencePort).saveDish(dish);
    }

    @Test
    void saveDish_ShouldThrowException_WhenNameIsNull() {
        dish.setName(null);
        assertThrows(DishNameNotFoundException.class, () -> dishUseCase.saveDish(dish));
    }

    @Test
    void saveDish_ShouldThrowException_WhenDescriptionIsBlank() {
        dish.setDescription("");
        assertThrows(DishDescriptionNotFoundException.class, () -> dishUseCase.saveDish(dish));
    }

    @Test
    void saveDish_ShouldThrowException_WhenPriceIsInvalid() {
        dish.setPrice(0.0);
        assertThrows(DishPriceNotValidException.class, () -> dishUseCase.saveDish(dish));
    }

    @Test
    void getDish_ShouldReturnDish() {
        when(dishPersistencePort.getDish(1L)).thenReturn(dish);
        Dish result = dishUseCase.getDish(1L);
        assertNotNull(result);
        assertEquals("Pizza", result.getName());
    }

    @Test
    void getAllDish_ShouldReturnListOfDishes() {
        List<Dish> dishes = Arrays.asList(dish, dish);
        when(dishPersistencePort.getAllDish()).thenReturn(dishes);
        List<Dish> result = dishUseCase.getAllDish();
        assertEquals(2, result.size());
    }

    @Test
    void updateDish_ShouldCallPersistence_WhenDishIsValid() {
        dishUseCase.updateDish(dish);
        verify(dishPersistencePort, times(1)).updateDish(dish);
    }

    @Test
    void deleteDish_ShouldCallPersistence() {
        dishUseCase.deleteDish(1L);
        verify(dishPersistencePort, times(1)).deleteDish(1L);
    }
    @Test
    void updateDish_ShouldThrowDishNameNotFoundException() {
        dish.setName("");
        assertThrows(DishNameNotFoundException.class, () -> dishUseCase.updateDish(dish));
    }

    @Test
    void updateDish_ShouldThrowDishDescriptionNotFoundException() {
        dish.setDescription("");
        assertThrows(DishDescriptionNotFoundException.class, () -> dishUseCase.updateDish(dish));
    }

    @Test
    void updateDish_ShouldThrowDishPriceNotValidException() {
        dish.setPrice(0.0);
        assertThrows(DishPriceNotValidException.class, () -> dishUseCase.updateDish(dish));
    }

    @Test
    void updateDish_ShouldThrowDishRestaurantNotFoundException() {
        dish.setRestaurantId(null);
        assertThrows(DishRestaurantNotFoundException.class, () -> dishUseCase.updateDish(dish));
    }

    @Test
    void updateDish_ShouldThrowDishCategoryNotFoundException() {
        dish.setCategoryId(null);
        assertThrows(DishCategoryNotFoundException.class, () -> dishUseCase.updateDish(dish));
    }
}

