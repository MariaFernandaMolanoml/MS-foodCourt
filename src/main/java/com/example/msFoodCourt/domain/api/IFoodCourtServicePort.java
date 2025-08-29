package com.example.msFoodCourt.domain.api;

import com.example.msFoodCourt.domain.model.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IFoodCourtServicePort {
    Page<Restaurant> listFoodCourts(int page, int size);
    Page<Restaurant> getAllRestaurantsOrderedByName(int page, int size);
}