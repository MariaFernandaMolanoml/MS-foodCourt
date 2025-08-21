package com.example.msFoodCourt.infrastructure.input.rest;

import com.example.msFoodCourt.application.dto.DishRequest;
import com.example.msFoodCourt.application.dto.DishResponse;
import com.example.msFoodCourt.application.dto.DishUpdateRequest;
import com.example.msFoodCourt.application.handler.IDishHandler;
import com.example.msFoodCourt.domain.exception.DishUpdateNotAllowedException;
import com.example.msFoodCourt.infrastructure.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @PostMapping
    public ResponseEntity<ApiResponse> saveDish(@Valid @RequestBody DishRequest dishRequest) {
        dishHandler.saveDish(dishRequest);

        ApiResponse response = new ApiResponse(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                "Created",
                "Dish created successfully"
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DishResponse>> getAllDishes() {
        return ResponseEntity.ok(dishHandler.getAllDishes());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> getDish(@PathVariable Long id) {
        return ResponseEntity.ok(dishHandler.getDish(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateDish(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        // Solo se permiten estos
        Set<String> allowed = Set.of("description", "price");

        for (String key : updates.keySet()) {
            if (!allowed.contains(key)) {
                throw new DishUpdateNotAllowedException("Solo se puede modificar precio y descripción.");
            }
        }

        DishUpdateRequest dto = new DishUpdateRequest();
        if (updates.containsKey("description")) {
            dto.setDescription((String) updates.get("description"));
        }
        if (updates.containsKey("price")) {
            dto.setPrice(Double.valueOf(updates.get("price").toString()));
        }

        dishHandler.updateDish(id, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Successfully updated dish");
        response.put("dishId", id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishHandler.deleteDish(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurant/{restaurantId}/dishes")
    public ResponseEntity<List<DishResponse>> getDishesByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(dishHandler.getDishesByRestaurant(restaurantId));
    }
}
