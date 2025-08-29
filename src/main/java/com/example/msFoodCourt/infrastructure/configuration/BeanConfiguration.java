package com.example.msFoodCourt.infrastructure.configuration;

import com.example.msFoodCourt.domain.api.IFoodCourtServicePort;
import com.example.msFoodCourt.domain.api.IRestaurantEmployeeServicePort;
import com.example.msFoodCourt.domain.api.IRestaurantServicePort;
import com.example.msFoodCourt.domain.spi.IDishPersistencePort;
import com.example.msFoodCourt.domain.spi.IRestaurantEmployeePersistencePort;
import com.example.msFoodCourt.domain.spi.IRestaurantPersistencePort;
import com.example.msFoodCourt.domain.spi.IUserPersistencePort;
import com.example.msFoodCourt.domain.usecase.DishUseCase;
import com.example.msFoodCourt.domain.usecase.FoodCourtUseCase;
import com.example.msFoodCourt.domain.usecase.RestaurantEmployeeUseCase;
import com.example.msFoodCourt.domain.usecase.RestaurantUseCase;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.RestaurantEmployeeJpaAdapter;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.RestaurantUserFeignAdapter;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.client.UserFeignClient;
import com.example.msFoodCourt.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.example.msFoodCourt.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import com.example.msFoodCourt.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IDishPersistencePort dishPersistencePort;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(
            IRestaurantRepository repository,
            RestaurantEntityMapper mapper,
            RestaurantUserFeignAdapter userFeignAdapter
    ) {
        return new RestaurantJpaAdapter(repository, mapper, userFeignAdapter);
    }

    @Bean
    public IUserPersistencePort userPersistencePort( UserFeignClient userFeignClient){
        return new RestaurantUserFeignAdapter(userFeignClient);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(IRestaurantPersistencePort persistencePort, IUserPersistencePort userPersistencePort) {
        return new RestaurantUseCase(persistencePort,userPersistencePort);
    }

    public BeanConfiguration(IDishPersistencePort dishPersistencePort, IRestaurantEmployeeRepository restaurantEmployeeRepository) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantEmployeeRepository = restaurantEmployeeRepository;
    }

    @Bean
    public IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort() {
        return new RestaurantEmployeeJpaAdapter(restaurantEmployeeRepository);
    }

    @Bean
    public IRestaurantEmployeeServicePort restaurantEmployeeServicePort(IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort,
                                                                        IRestaurantPersistencePort restaurantPersistencePort,
                                                                        IUserPersistencePort userPersistencePort) {
        return new RestaurantEmployeeUseCase(
                restaurantEmployeePersistencePort,
                restaurantPersistencePort,
                userPersistencePort
        );
    }

    @Bean
    public DishUseCase dishUseCase(IDishPersistencePort dishPersistencePort,
                                   IRestaurantPersistencePort restaurantPersistencePort) {
        return new DishUseCase(dishPersistencePort, restaurantPersistencePort);
    }
    @Bean
    public IFoodCourtServicePort foodCourtServicePort(IRestaurantPersistencePort restaurantPersistencePort) {
        return new FoodCourtUseCase(restaurantPersistencePort);
    }
}
