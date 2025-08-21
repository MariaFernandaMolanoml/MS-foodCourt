package com.example.msFoodCourt.infrastructure.configuration;

import com.example.msFoodCourt.domain.api.IDishServicePort;
import com.example.msFoodCourt.domain.api.IRestaurantServicePort;
import com.example.msFoodCourt.domain.spi.IDishPersistencePort;
import com.example.msFoodCourt.domain.spi.IRestaurantPersistencePort;
import com.example.msFoodCourt.domain.spi.IUserPersistencePort;
import com.example.msFoodCourt.domain.usecase.DishUseCase;
import com.example.msFoodCourt.domain.usecase.RestaurantUseCase;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.RestaurantUserFeignAdapter;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.client.UserFeignClient;
import com.example.msFoodCourt.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.example.msFoodCourt.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

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
    private final IDishPersistencePort dishPersistencePort;

    public BeanConfiguration(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort);
    }
}
