package net.j22.cloud.jwtauthapi.services;

import lombok.AllArgsConstructor;
import net.j22.cloud.jwtauthapi.dao.repository.CustomerRepository;
import net.j22.cloud.jwtauthapi.dtos.CustomUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final CustomerRepository customerRepository;
    public List<CustomUser> allUsers() {
        List<CustomUser> users =

                customerRepository.findAll()

                        .stream()
                        .map(customer ->
                                CustomUser.builder()
                                        .customer(customer)
                                        .build())

                        .collect(Collectors.toList());
        return users;
    }
}
