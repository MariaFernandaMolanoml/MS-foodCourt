package com.example.msFoodCourt.domain.utils;

import com.example.msFoodCourt.domain.utils.constant.CategoryConstants;

public final class CategoryResolver {

    private CategoryResolver() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static Long getCategoryId(String categoryName) {
        return switch (categoryName.toUpperCase()) {
            case CategoryConstants.FAST_FOOD -> CategoryConstants.FAST_FOOD_ID;
            case CategoryConstants.ORIENTAL_FOOD -> CategoryConstants.ORIENTAL_FOOD_ID;
            case CategoryConstants.ITALIAN_FOOD -> CategoryConstants.ITALIAN_FOOD_ID;
            case CategoryConstants.INDIAN_FOOD -> CategoryConstants.INDIAN_FOOD_ID;
            case CategoryConstants.HEALTHY_FOOD -> CategoryConstants.HEALTHY_FOOD_ID;
            case CategoryConstants.DESSERTS -> CategoryConstants.DESSERTS_ID;
            case CategoryConstants.MEXICAN_FOOD -> CategoryConstants.MEXICAN_FOOD_ID;
            case CategoryConstants.AMERICAN_FOOD -> CategoryConstants.AMERICAN_FOOD_ID;
            case CategoryConstants.BEVERAGES -> CategoryConstants.BEVERAGES_ID;
            case CategoryConstants.BREAKFAST -> CategoryConstants.BREAKFAST_ID;
            case CategoryConstants.SEAFOOD -> CategoryConstants.SEAFOOD_ID;
            case CategoryConstants.BAKERY -> CategoryConstants.BAKERY_ID;
            default -> throw new IllegalArgumentException("Category not found: " + categoryName);
        };
    }
}
