package com.example.msFoodCourt.infrastructure.output.jpa.adapter;

import com.example.msFoodCourt.domain.exception.RestaurantNotFoundException;
import com.example.msFoodCourt.domain.model.Restaurant;
import com.example.msFoodCourt.domain.spi.IRestaurantPersistencePort;
import com.example.msFoodCourt.infrastructure.output.jpa.entity.RestaurantEntity;
import com.example.msFoodCourt.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.example.msFoodCourt.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    @Override
    public Optional<Restaurant> findById(Long id) {
        return Optional.ofNullable(repository.findById(id)
                .map(mapper::toRestaurant)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + id)));
    }

    @Override
    public Optional<Restaurant> findByNit(String nit) {
        return repository.findByNit(nit)
                .map(mapper::toRestaurant);
    }

    @Override
    public Page<Restaurant> findAllOrderedByName(int page, int size) {
        Page<RestaurantEntity> entities = repository.findAll(
                PageRequest.of(page, size, Sort.by("name").ascending())
        );
        return entities.map(mapper::toRestaurant);
    }

    @Override
    public Page<Restaurant> findAllWithPagination(int page, int size) {
        Page<RestaurantEntity> entities = repository.findAll(
                PageRequest.of(page, size)
        );
        return entities.map(mapper::toRestaurant);
    }
}
