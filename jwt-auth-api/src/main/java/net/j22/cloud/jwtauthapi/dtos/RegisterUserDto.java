package net.j22.cloud.jwtauthapi.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;
}