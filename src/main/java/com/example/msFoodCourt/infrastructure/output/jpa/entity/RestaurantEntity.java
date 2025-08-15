package com.example.msFoodCourt.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field is mandatory")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "This field is mandatory")
    @Pattern(regexp = "\\d+", message = "NIT must be numeric")
    @Column(nullable = false)
    private String nit;

    @NotNull(message = "This field is mandatory")
    @Column(nullable = false)
    private String address;

    @NotNull(message = "This field is mandatory")
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "Phone must be up to 13 digits and may include '+' at the start")
    @Column(nullable = false, length = 13)
    private String phone;

    @NotNull(message = "This field is mandatory")
    @Column(nullable = false)
    private String urlLogo;

    @NotNull(message = "This field is mandatory")
    @Column(nullable = false)
    private Long idOwner;
}
