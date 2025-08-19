package com.example.msFoodCourt.domain.usecase;

import com.example.msFoodCourt.domain.exception.*;
import com.example.msFoodCourt.domain.model.Restaurant;
import com.example.msFoodCourt.domain.model.User;
import com.example.msFoodCourt.domain.spi.IRestaurantPersistencePort;
import com.example.msFoodCourt.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    private IRestaurantPersistencePort restaurantPersistencePort;
    private IUserPersistencePort userPersistencePort;
    private RestaurantUseCase restaurantUseCase;

    @BeforeEach
    void setUp() {
        restaurantPersistencePort = Mockito.mock(IRestaurantPersistencePort.class);
        userPersistencePort = Mockito.mock(IUserPersistencePort.class);
        restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, userPersistencePort);
    }

    @Test
    void createRestaurant_Success() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("La Parrilla");
        restaurant.setNit("123456");
        restaurant.setAddress("Calle 123");
        restaurant.setPhone("3001234567");
        restaurant.setUrlLogo("http://logo.png");
        restaurant.setDocumentOwner("111");

        User owner = new User();
        owner.setDocument("111");
        owner.setName("Carlos");
        owner.setLastName("Perez");
        owner.setEmail("carlos@mail.com");
        owner.setRole("PROPIETARIO");

        when(userPersistencePort.getUserByDocument("111")).thenReturn(owner);

        restaurantUseCase.createRestaurant(restaurant);

        verify(restaurantPersistencePort, times(1)).save(restaurant);
    }

    @Test
    void createRestaurant_Fails_WhenOwnerDoesNotExist() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurante");
        restaurant.setNit("123456");
        restaurant.setAddress("Calle 1");
        restaurant.setPhone("3001234567");
        restaurant.setUrlLogo("http://logo.png");
        restaurant.setDocumentOwner("999");

        when(userPersistencePort.getUserByDocument("999")).thenReturn(null);

        assertThrows(OwnerNotFoundException.class, () -> restaurantUseCase.createRestaurant(restaurant));
        verify(restaurantPersistencePort, never()).save(any());
    }

    @Test
    void createRestaurant_Fails_WhenOwnerIsNotPropietario() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurant");
        restaurant.setNit("123456");
        restaurant.setAddress("Calle 1");
        restaurant.setPhone("3001234567");
        restaurant.setUrlLogo("http://logo.png");
        restaurant.setDocumentOwner("222");

        User user = new User();
        user.setDocument("222");
        user.setName("Ana");
        user.setLastName("Gomez");
        user.setEmail("ana@mail.com");
        user.setRole("CLIENTE");

        when(userPersistencePort.getUserByDocument("222")).thenReturn(user);

        assertThrows(OwnerNotFoundException.class, () -> restaurantUseCase.createRestaurant(restaurant));
        verify(restaurantPersistencePort, never()).save(any());
    }

    @Test
    void createRestaurant_Fails_WhenNameIsOnlyNumbers() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("12345");
        restaurant.setNit("123456");
        restaurant.setAddress("Calle 1");
        restaurant.setPhone("3001234567");
        restaurant.setUrlLogo("http://logo.png");
        restaurant.setDocumentOwner("333");

        User owner = new User();
        owner.setDocument("333");
        owner.setName("Pedro");
        owner.setLastName("Lopez");
        owner.setEmail("pedro@mail.com");
        owner.setRole("PROPIETARIO");

        when(userPersistencePort.getUserByDocument("333")).thenReturn(owner);

        assertThrows(InvalidNameException.class, () -> restaurantUseCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_Fails_WhenNitIsNotNumeric() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurant");
        restaurant.setNit("ABC123");
        restaurant.setAddress("Calle 1");
        restaurant.setPhone("3001234567");
        restaurant.setUrlLogo("http://logo.png");
        restaurant.setDocumentOwner("444");

        User owner = new User();
        owner.setDocument("444");
        owner.setName("Juan");
        owner.setLastName("Diaz");
        owner.setEmail("juan@mail.com");
        owner.setRole("PROPIETARIO");

        when(userPersistencePort.getUserByDocument("444")).thenReturn(owner);

        assertThrows(InvalidNitException.class, () -> restaurantUseCase.createRestaurant(restaurant));
    }

    @Test
    void createRestaurant_Fails_WhenPhoneIsInvalid() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurant");
        restaurant.setNit("123456");
        restaurant.setAddress("Calle 1");
        restaurant.setPhone("123-456-789");
        restaurant.setUrlLogo("http://logo.png");
        restaurant.setDocumentOwner("555");

        User owner = new User();
        owner.setDocument("555");
        owner.setName("Maria");
        owner.setLastName("Suarez");
        owner.setEmail("maria@mail.com");
        owner.setRole("PROPIETARIO");

        when(userPersistencePort.getUserByDocument("555")).thenReturn(owner);

        assertThrows(InvalidPhoneException.class, () -> restaurantUseCase.createRestaurant(restaurant));
    }

    @Test
    void getAllRestaurants_ReturnsList() {
        Restaurant r1 = new Restaurant();
        r1.setName("R1");
        r1.setNit("111");
        r1.setAddress("Calle A");
        r1.setPhone("3001111111");
        r1.setUrlLogo("url1");
        r1.setDocumentOwner("101");

        Restaurant r2 = new Restaurant();
        r2.setName("R2");
        r2.setNit("222");
        r2.setAddress("Calle B");
        r2.setPhone("3002222222");
        r2.setUrlLogo("url2");
        r2.setDocumentOwner("102");

        List<Restaurant> restaurants = Arrays.asList(r1, r2);

        when(restaurantPersistencePort.findAll()).thenReturn(restaurants);

        List<Restaurant> result = restaurantUseCase.getAllRestaurants();

        assertEquals(2, result.size());
        verify(restaurantPersistencePort, times(1)).findAll();
    }
}
