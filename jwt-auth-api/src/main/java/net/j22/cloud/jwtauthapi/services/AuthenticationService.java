package net.j22.cloud.jwtauthapi.services;

import lombok.AllArgsConstructor;
import net.j22.cloud.jwtauthapi.dao.entities.Customer;
import net.j22.cloud.jwtauthapi.dao.repository.CustomerRepository;
import net.j22.cloud.jwtauthapi.dtos.CustomUser;
import net.j22.cloud.jwtauthapi.dtos.LoginUserDto;
import net.j22.cloud.jwtauthapi.dtos.RegisterUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthenticationService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public CustomUser signup(RegisterUserDto input) {
        Customer customer = Customer.builder()
                .email(input.getEmail())
                .password(

                        passwordEncoder.encode(input.getPassword()))

                .fullName(input.getFullName())
                .build();
        customer = customerRepository.save(customer);
        return CustomUser.builder().customer(customer).build();
    }
    public CustomUser authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        Customer customer = customerRepository.findByEmail(

                input.getEmail()).orElseThrow();

        return CustomUser.builder().customer(customer).build();
    }
}
