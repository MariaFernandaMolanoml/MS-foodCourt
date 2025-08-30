package com.example.foodcourt.domain.spi;

import com.example.foodcourt.domain.model.User;

public interface IUserPersistencePort {
    User getUserByDocument(String document);
}
