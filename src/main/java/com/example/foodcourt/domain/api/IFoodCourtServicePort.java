package com.example.foodcourt.domain.api;

import com.example.foodcourt.domain.model.Restaurant;
import org.springframework.data.domain.Page;

public interface IFoodCourtServicePort {
    Page<Restaurant> listFoodCourts(int page, int size);
    Page<Restaurant> getAllRestaurantsOrderedByName(int page, int size);
}