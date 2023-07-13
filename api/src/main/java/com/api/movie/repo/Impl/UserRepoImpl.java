package com.api.movie.repo.impl;

import java.util.List;

import org.hibernate.query.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;

import com.api.movie.base.entities.impl.RawQueryImpl;
import com.api.movie.dto.input.UserInputDto;
import com.api.movie.entities.User;
import com.api.movie.entities.impl.UserImpl;
import com.api.movie.repo.UserRepo;

import lombok.Getter;


@Getter
public class UserRepoImpl extends RawQueryImpl implements UserRepo  {

    @Override
    public List<User> searchUser(UserInputDto req) {
        List<User> list = listResult(UserImpl.class,
                "getUser", query -> {
                    query.setParameter("id",
                            new TypedParameterValue(StandardBasicTypes.LONG, req.getId()));
                    return query;
                });
        return list;
    }

    @Override
    public User singleResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'singleResult'");
    }

    @Override
    public List<User> listResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listResult'");
    }

    @Override
    public long countResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countResult'");
    }

    @Override
    public User create(User entity, String creatorUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public int update(User entity, String updaterUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int getFirstResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFirstResult'");
    }

    @Override
    public UserRepo setFirstResult(int firstResult) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFirstResult'");
    }

    @Override
    public int getMaxResults() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxResults'");
    }

    @Override
    public UserRepo setMaxResults(int maxResults) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMaxResults'");
    }

    
}
