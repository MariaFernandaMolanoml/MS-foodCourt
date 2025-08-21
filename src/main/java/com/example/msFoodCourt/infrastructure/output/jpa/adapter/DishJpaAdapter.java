package com.example.msFoodCourt.infrastructure.output.jpa.adapter;

import com.example.msFoodCourt.domain.model.Dish;
import com.example.msFoodCourt.domain.spi.IDishPersistencePort;
import com.example.msFoodCourt.infrastructure.output.jpa.mapper.IDishEntityMapper;
import com.example.msFoodCourt.infrastructure.output.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
                .orElseThrow(() -> new RuntimeException("Dish not found"));
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
}
