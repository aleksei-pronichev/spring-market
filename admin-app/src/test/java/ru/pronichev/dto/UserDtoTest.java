package ru.pronichev.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class UserDtoTest {

    @Test
    void empty() {
        var userDto = UserDto.empty();
        assertEquals(UserDto.class, userDto.getClass());
        assertNull(userDto.getId());
        assertNull(userDto.getAge());
        assertNull(userDto.getUsername());
        assertNull(userDto.getPassword());
        assertNull(userDto.getEmail());
    }
}