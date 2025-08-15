package com.example.msFoodCourt.infrastructure.output.jpa.adapter;

import com.example.msFoodCourt.domain.model.Restaurant;
import com.example.msFoodCourt.domain.spi.IRestaurantPersistencePort;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.client.RestaurantUserFeignAdapter;
import com.example.msFoodCourt.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.example.msFoodCourt.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository repository;
    private final RestaurantEntityMapper mapper;
    private final RestaurantUserFeignAdapter userFeignAdapter;

    @Override
    public void save(Restaurant restaurant) {
        repository.save(mapper.toEntity(restaurant));
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toRestaurant);
    }

    @Override
    public boolean existsOwnerById(Long idOwner) {
        return userFeignAdapter.existsOwnerById(idOwner);
    }

    @Override
    public boolean existsByNit(String nit) {
        return repository.existsByNit(nit);
    }

    @Override
    public List<Restaurant> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toRestaurant)
                .toList();
    }
}
