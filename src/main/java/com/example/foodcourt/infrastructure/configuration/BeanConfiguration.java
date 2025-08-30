package com.example.foodcourt.infrastructure.configuration;


import com.example.foodcourt.domain.api.IDishServicePort;
import com.example.foodcourt.domain.api.IDishUserServicePort;
import com.example.foodcourt.domain.api.IFoodCourtServicePort;
import com.example.foodcourt.domain.api.IOrderServicePort;
import com.example.foodcourt.domain.api.IRestaurantEmployeeServicePort;
import com.example.foodcourt.domain.api.IRestaurantServicePort;
import com.example.foodcourt.domain.spi.ICategoryPersistencePort;
import com.example.foodcourt.domain.spi.IDishPersistencePort;
import com.example.foodcourt.domain.spi.IOrderPersistencePort;
import com.example.foodcourt.domain.spi.IRestaurantEmployeePersistencePort;
import com.example.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.example.foodcourt.domain.spi.IUserPersistencePort;
import com.example.foodcourt.domain.usecase.DishUseCase;
import com.example.foodcourt.domain.usecase.DishUserUseCase;
import com.example.foodcourt.domain.usecase.FoodCourtUseCase;
import com.example.foodcourt.domain.usecase.OrderUseCase;
import com.example.foodcourt.domain.usecase.RestaurantEmployeeUseCase;
import com.example.foodcourt.domain.usecase.RestaurantUseCase;
import com.example.foodcourt.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.example.foodcourt.infrastructure.output.jpa.adapter.DishJpaAdapter;
import com.example.foodcourt.infrastructure.output.jpa.adapter.OrderJpaAdapter;
import com.example.foodcourt.infrastructure.output.jpa.adapter.RestaurantEmployeeJpaAdapter;
import com.example.foodcourt.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.example.foodcourt.infrastructure.output.jpa.adapter.RestaurantUserFeignAdapter;
import com.example.foodcourt.infrastructure.output.jpa.adapter.client.UserFeignClient;
import com.example.foodcourt.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.mapper.IDishEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.repository.ICategoryRepository;
import com.example.foodcourt.infrastructure.output.jpa.repository.IDishRepository;
import com.example.foodcourt.infrastructure.output.jpa.repository.IOrderRepository;
import com.example.foodcourt.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import com.example.foodcourt.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final UserFeignClient userFeignClient;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new RestaurantUserFeignAdapter(userFeignClient);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository,categoryEntityMapper);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(dishRepository,dishEntityMapper);
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort(){
        return new RestaurantEmployeeJpaAdapter(restaurantEmployeeRepository);
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort(), restaurantPersistencePort());
    }

    @Bean
    public IDishUserServicePort dishUserServicePort(){
        return new DishUserUseCase(dishPersistencePort());
    }

    @Bean
    public IFoodCourtServicePort foodCourtServicePort(){
        return new FoodCourtUseCase(restaurantPersistencePort());
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort(), dishPersistencePort(),restaurantPersistencePort());
    }

    @Bean
    public IRestaurantEmployeeServicePort restaurantEmployeeServicePort(){
        return new RestaurantEmployeeUseCase(restaurantEmployeePersistencePort(),restaurantPersistencePort(),userPersistencePort());
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort(),userPersistencePort());
    }
}
