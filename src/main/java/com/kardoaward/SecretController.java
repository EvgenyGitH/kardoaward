package com.kardoaward;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/secret")
public class SecretController {

    private final Map<String, UserDto> repository;

    public SecretController(Map<String, UserDto> repository) {
        this.repository = repository;
    }

    @GetMapping(value = "destroy")
    public HttpStatus destroy() {
        repository.clear();
        return HttpStatus.OK;
    }

}
