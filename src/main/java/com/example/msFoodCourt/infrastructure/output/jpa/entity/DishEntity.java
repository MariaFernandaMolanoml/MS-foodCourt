package com.example.msFoodCourt.infrastructure.output.jpa.entity;

import com.example.msFoodCourt.domain.model.Category;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String urlPhoto;
    private Long restaurantId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    private Boolean active;
}
