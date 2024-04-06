package com.test.token.jwtoken.dto.auth;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {

    private String email;

    private String password;

}

