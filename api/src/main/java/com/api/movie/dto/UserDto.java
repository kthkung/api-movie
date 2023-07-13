package com.api.movie.dto;

import java.util.ArrayList;
import java.util.List;

import com.api.movie.base.entities.impl.RawDtoImpl;
import com.api.movie.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDto extends RawDtoImpl {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

    public static UserDto fromEntity(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public static List<UserDto> fromEntity(List<User> list) {
        List<UserDto> resultList = new ArrayList<UserDto>();
        for (var model : list) {
            resultList.add(fromEntity(model));
        }
        return resultList;
    }
}
