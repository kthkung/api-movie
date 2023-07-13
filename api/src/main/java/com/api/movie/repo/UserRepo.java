package com.api.movie.repo;

import java.util.List;

import com.api.movie.base.entities.BaseQuery;
import com.api.movie.base.entities.Query;
import com.api.movie.dto.input.UserInputDto;
import com.api.movie.entities.User;
import com.api.movie.entities.impl.UserImpl;

public interface UserRepo extends Query<UserRepo, User, UserImpl>, BaseQuery<UserRepo> {
    List<User> searchUser(UserInputDto req);
}
