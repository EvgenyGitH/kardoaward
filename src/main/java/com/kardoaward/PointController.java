package com.kardoaward;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/user/point")
public class PointController {

    private final Map<String, UserDto> repository;

    public PointController(Map<String, UserDto> repository) {
        this.repository = repository;
    }

    @PostMapping("{key}")
    public HttpStatus changePoints(
            @PathVariable String key,
            @RequestParam("point") Long point,
            @RequestParam("type") String type
    ) {
        final UserDto userDto = repository.get(key);
        userDto.setPoints(
                "plus".equalsIgnoreCase(type)
                        ? userDto.getPoints() + point
                        : userDto.getPoints() - point
        );
        return HttpStatus.OK;
    }

}
