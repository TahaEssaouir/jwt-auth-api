package net.j22.cloud.jwtauthapi.dtos;

import lombok.*;
import net.j22.cloud.jwtauthapi.dao.entities.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomUser implements UserDetails {
    private Customer customer;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    public String getPassword() {
        return customer.getPassword();
    }
    @Override
    public String getUsername() {
        return customer.getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public Integer getId() {
        return customer.getId();
    }
}
