package com.example.msFoodCourt.infrastructure.output.jpa.adapter.client;

import com.example.msFoodCourt.application.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-user", url = "http://localhost:8090/users")
public interface UserFeignClient {

    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);
}
