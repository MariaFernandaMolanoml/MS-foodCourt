package com.example.msFoodCourt.domain.usecase;

import com.example.msFoodCourt.domain.exception.*;
import com.example.msFoodCourt.domain.model.Dish;
import com.example.msFoodCourt.domain.model.DishUpdate;
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
        dish.setCategory(1L);
        dish.setActive(true);
    }

    // -------------------- TESTS EXISTENTES --------------------

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
        assertThrows(UnsupportedOperationException.class, () -> dishUseCase.updateDish(dish));
    }

    @Test
    void deleteDish_ShouldCallPersistence() {
        dishUseCase.deleteDish(1L);
        verify(dishPersistencePort, times(1)).deleteDish(1L);
    }

    @Test
    void updateDish_WithValidDescriptionAndPrice_ShouldUpdate() {
        DishUpdate update = new DishUpdate();
        update.setId(1L);
        update.setDescription("Nueva descripción");
        update.setPrice(25.0);

        when(dishPersistencePort.existById(1L)).thenReturn(true);
        when(dishPersistencePort.getDish(1L)).thenReturn(dish);

        dishUseCase.updateDish(update);

        assertEquals("Nueva descripción", dish.getDescription());
        assertEquals(25.0, dish.getPrice());
        verify(dishPersistencePort).updateDish(dish);
    }

    @Test
    void updateDish_WithOnlyPrice_ShouldUpdatePrice() {
        DishUpdate update = new DishUpdate();
        update.setId(1L);
        update.setPrice(30.0);

        when(dishPersistencePort.existById(1L)).thenReturn(true);
        when(dishPersistencePort.getDish(1L)).thenReturn(dish);

        dishUseCase.updateDish(update);

        assertEquals(30.0, dish.getPrice());
        assertEquals("Delicious pizza", dish.getDescription()); // la descripción se mantiene
        verify(dishPersistencePort).updateDish(dish);
    }

    @Test
    void updateDish_ShouldThrowException_WhenBothFieldsNull() {
        DishUpdate update = new DishUpdate();
        update.setId(1L);

        assertThrows(UpdateDishException.class, () -> dishUseCase.updateDish(update));
        verify(dishPersistencePort, never()).updateDish(any());
    }

    @Test
    void updateDish_ShouldThrowException_WhenDishNotFound() {
        DishUpdate update = new DishUpdate();
        update.setId(99L);
        update.setDescription("Nueva desc");

        when(dishPersistencePort.existById(99L)).thenReturn(false);

        assertThrows(DishNotFoundException.class, () -> dishUseCase.updateDish(update));
        verify(dishPersistencePort, never()).updateDish(any());
    }
}
