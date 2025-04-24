package net.j22.cloud.jwtauthapi.dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
}
