package com.app.security;

import com.app.model.ClienteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
@AllArgsConstructor
public final class CustomUserDetails implements UserDetails {

    private String nome;
    private String username;
    private String password;
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public static CustomUserDetails valueOf(ClienteEntity entity) {
        return new CustomUserDetailsBuilder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .build();
    }
}