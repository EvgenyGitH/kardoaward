package com.kardoaward;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Пользователи", description = "Взаимодействие с пользователями")
public class UserController {

    private final Map<String, UserDto> repository;

    public UserController(Map<String, UserDto> repository) {
        this.repository = repository;
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus registerUser(@RequestBody UserDto userDto) {
        repository.put(userDto.getKey(), userDto);
        return HttpStatus.OK;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus updateUser(@RequestBody UserDto userDto) {
        if (!repository.containsKey(userDto.getKey())) return HttpStatus.NOT_FOUND;
        repository.put(userDto.getKey(), userDto);
        return HttpStatus.OK;
    }

    @GetMapping(value = "{key}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getSimpleDto(@PathVariable("key") String key) {
        return ResponseEntity.ok(repository.get(key));
    }

}