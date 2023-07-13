package com.api.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.api.movie.base.controllers.BaseController;
import com.api.movie.base.entities.impl.RawPageDtoImpl;
import com.api.movie.dto.UserDto;
import com.api.movie.dto.input.UserInputDto;
import com.api.movie.service.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController extends BaseController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;
    
    public UserController(){
    }

    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody UserInputDto req) {
        try {

            var entity = userService.getUser(req);
            if (entity == null)
                return new ResponseEntity(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(
                    new RawPageDtoImpl<UserDto>(0, 10, entity.size(),
                            true, UserDto.fromEntity(entity)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
