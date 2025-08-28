package com.example.msFoodCourt.domain.spi;

import com.example.msFoodCourt.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    User getUserByDocument(String document);
}
