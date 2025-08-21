package com.example.msFoodCourt.infrastructure.input.rest;

import com.example.msFoodCourt.application.dto.DishRequest;
import com.example.msFoodCourt.application.dto.DishResponse;
import com.example.msFoodCourt.application.handler.IDishHandler;
import com.example.msFoodCourt.infrastructure.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseEntity<Void> updateDish(@PathVariable Long id,
                                           @Valid @RequestBody DishRequest dishRequest) {
        dishRequest.setId(id);
        dishHandler.updateDish(dishRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishHandler.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
