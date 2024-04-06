package com.test.token.jwtoken.dto.auth;

import com.test.token.jwtoken.entities.modules.Role;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String userId;

    private String name;

    private String email;

    private String about;

    private Set<Role> roles;

}
