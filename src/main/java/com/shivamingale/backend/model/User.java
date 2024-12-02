package com.shivamingale.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String mobile;

    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String JWTToken;

    // Implement methods from UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Returning the role as an authority (you can extend this logic as needed)
        return Collections.singletonList(() -> "ROLE_" + role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;  // Using email as the username, you can use any unique field here
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Implement any logic if required
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Implement any logic if required
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Implement any logic if required
    }

    @Override
    public boolean isEnabled() {
        return true;  // Implement any logic if required (e.g., checking if the account is active)
    }
}
