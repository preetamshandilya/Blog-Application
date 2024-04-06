package com.test.token.jwtoken.services.auth;

import com.test.token.jwtoken.dto.auth.UserRequestDto;
import com.test.token.jwtoken.dto.auth.UserResponseDto;
import com.test.token.jwtoken.entities.modules.Role;
import com.test.token.jwtoken.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

public interface AuthService {
    UserResponseDto registerUser(UserRequestDto userDto) throws ResourceNotFoundException;

    UserResponseDto updateUser(UserRequestDto userDto, Integer userId, Set<Role> roles) throws ResourceNotFoundException;

    UserResponseDto getUserById(Integer userId) throws ResourceNotFoundException;

    List<UserResponseDto> getAllUsers();

    String deleteUserById(Integer userId) throws ResourceNotFoundException;

    UserResponseDto registerAdmin(UserRequestDto userDto)throws ResourceNotFoundException;

    List<UserResponseDto> searchUserByName(String keyword) throws ResourceNotFoundException;

}
