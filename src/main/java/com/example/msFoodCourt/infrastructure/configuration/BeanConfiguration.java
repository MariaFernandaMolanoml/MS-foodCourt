package com.example.msFoodCourt.infrastructure.configuration;

import com.example.msFoodCourt.domain.api.IRestaurantServicePort;
import com.example.msFoodCourt.domain.spi.IRestaurantPersistencePort;
import com.example.msFoodCourt.domain.usecase.RestaurantUseCase;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.client.RestaurantUserFeignAdapter;
import com.example.msFoodCourt.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.example.msFoodCourt.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Configuration
@EnableFeignClients(basePackages = "com.example.msFoodCourt.infrastructure.output.jpa.adapter.client")
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
    public IRestaurantServicePort restaurantServicePort(IRestaurantPersistencePort persistencePort) {
        return new RestaurantUseCase(persistencePort);
    }
}
