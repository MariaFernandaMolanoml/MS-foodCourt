package com.example.msFoodCourt.domain.spi;

import com.example.msFoodCourt.domain.model.User;

public interface IUserPersistencePort {
    User getUserByDocument(String document);
}
