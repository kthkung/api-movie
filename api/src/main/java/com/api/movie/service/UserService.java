package com.api.movie.service;

import java.util.List;

import com.api.movie.dto.input.UserInputDto;
import com.api.movie.entities.User;

public interface UserService {
    List<User> getUser(UserInputDto req);
}
