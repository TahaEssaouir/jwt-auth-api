package net.j22.cloud.jwtauthapi.configs;

import net.j22.cloud.jwtauthapi.dao.repository.CustomerRepository;
import net.j22.cloud.jwtauthapi.dtos.CustomUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfiguration {
    private final CustomerRepository userRepository;
    public ApplicationConfiguration(CustomerRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Bean
    UserDetailsService userDetailsService() {
        return username -> CustomUser.builder()
                .customer(userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")))
                .build() ;
    }
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
