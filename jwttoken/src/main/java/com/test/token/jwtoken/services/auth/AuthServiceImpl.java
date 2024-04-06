package com.test.token.jwtoken.services.auth;
import com.test.token.jwtoken.entities.modules.Role;
import com.test.token.jwtoken.entities.modules.User;
import com.test.token.jwtoken.repositories.RoleRepository;
import org.modelmapper.ModelMapper;

import com.test.token.jwtoken.dto.auth.UserRequestDto;
import com.test.token.jwtoken.dto.auth.UserResponseDto;
import com.test.token.jwtoken.exception.ResourceNotFoundException;
import com.test.token.jwtoken.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);



    @Override
    public UserResponseDto registerUser(@RequestBody UserRequestDto userDto) throws ResourceNotFoundException {
        User user = this.modelMapper.map(userDto, User.class);

        Set<Role> roles = user.getRoles();
        Role role = this.roleRepository.findByRoleName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User saved_user = userRepository.save(user);

        return this.toUserDto(saved_user);

    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userDto, Integer userId,Set<Role> roles) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(roles);

        User updatedUser = userRepository.save(user);

        return this.toUserDto(updatedUser);

    }

    @Override
    public UserResponseDto getUserById(Integer userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        return this.toUserDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> listOfUsers = userRepository.findAll();

        return listOfUsers.stream().map(this::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteUserById(Integer userId) throws ResourceNotFoundException {

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            return "User not found !!";
        }

        userRepository.deleteById(userId);
        return "User Deleted Successfully";

    }

    @Override
    public UserResponseDto registerAdmin(UserRequestDto userDto) throws ResourceNotFoundException {
        User user = this.modelMapper.map(userDto,User.class);

        Set<Role> roles = user.getRoles();
        Role userRole = this.roleRepository.findByRoleName("ROLE_USER");

        Role adminRole = this.roleRepository.findByRoleName("ROLE_ADMIN");
        roles.add(userRole);
        roles.add(adminRole);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User saved_user = userRepository.save(user);

        return this.toUserDto(saved_user);

    }

    @Override
    public List<UserResponseDto> searchUserByName(String keyword) throws ResourceNotFoundException {
        List<User> findByKeyword = userRepository.findByNameContaining(keyword);
        return findByKeyword.stream().map(this::toUserDto).collect(Collectors.toList());
    }

    //Util method

    public UserResponseDto toUserDto(User user) {

        return modelMapper.map(user, UserResponseDto.class);

    }
}
