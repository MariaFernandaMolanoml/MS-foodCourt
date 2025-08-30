package com.example.foodcourt.infrastructure.output.jpa.adapter;

import com.example.foodcourt.domain.model.Dish;
import com.example.foodcourt.domain.spi.IDishPersistencePort;
import com.example.foodcourt.infrastructure.output.jpa.mapper.IDishEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public void saveDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public List<Dish> getAllDish() {
        return dishEntityMapper.toDishList(dishRepository.findAll());
    }

    @Override
    public Dish getDish(Long id) {
        return dishRepository.findById(id)
                .map(dishEntityMapper::toDish)
                .orElseThrow(() -> new RuntimeException("El plato con ID " + id + " no existe"));
    }

    @Override
    public void updateDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public boolean existById(Long id) {
        return dishRepository.existsById(id);
    }

    @Override
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> findByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dishRepository.findByCategoryId(categoryId, pageable)
                .map(dishEntityMapper::toDish)
                .getContent();
    }

    @Override
    public Page<Dish> findAll(Pageable pageable) {
        return dishRepository.findAll(pageable)
                .map(dishEntityMapper::toDish);
    }
}
