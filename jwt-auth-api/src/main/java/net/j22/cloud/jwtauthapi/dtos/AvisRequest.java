package net.j22.cloud.jwtauthapi.dtos;

import lombok.*;
import net.j22.cloud.jwtauthapi.dao.entities.Customer;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class AvisRequest {

    private String content;

    private Customer customer;

}
