package com.api.movie.entities.impl;

import com.api.movie.entities.User;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@NamedNativeQueries({
        @NamedNativeQuery(name = "getUser",
                query = " SELECT * FROM tb_user",
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

        @Column(name = "password")
        private String password;

        @Column(name = "created_at")
        private String createdAt;

        @Column(name = "updated_at")
        private String updatedAt;

}
