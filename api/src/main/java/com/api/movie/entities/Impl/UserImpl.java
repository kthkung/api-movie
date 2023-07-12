package com.api.movie.entities.Impl;

import com.api.movie.entities.User;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@NamedNativeQueries({
        @NamedNativeQuery(name = "getUser",
                query = " SELECT id, username, email"
                        + " FROM tb_user"
                        + " WHERE id = :id",
                resultClass = UserImpl.class),
})

@Entity
public class UserImpl implements User {

        @Id
        @Column(name = "id")
        private Long id;

        @Column(name = "username")
        private String username;

        @Column(name = "email")
        private String email;

}
