package com.api.movie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.movie.dto.input.UserInputDto;
import com.api.movie.entities.User;
import com.api.movie.repo.UserRepo;
import com.api.movie.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public List<User> getUser(UserInputDto req) {
        var query = applicationContext.getBean(UserRepo.class);
        return query.searchUser(req);
    }
}
