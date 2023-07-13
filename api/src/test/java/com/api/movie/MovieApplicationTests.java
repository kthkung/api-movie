package com.api.movie;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.movie.dto.input.UserInputDto;
import com.api.movie.service.UserService;

@SpringBootTest
class MovieApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
    public void list() {
        UserInputDto dtoQuery = new UserInputDto();
        dtoQuery.setId(1L);
        var result = userService.getUser(dtoQuery);
        assertNotEquals(result, null);
    }

}
