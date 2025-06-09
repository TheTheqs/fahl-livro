package com.fahllivro.backend.security;

import com.fahllivro.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

    private final User user;

    //Métodos
    //GrantedAuthority é obrigatório para implementação de UserDetail, mas não usaremos em nível MVP.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getSenha();
    }

    public String getUsername() {
        return user.getEmail(); //escolhendo o email como padrão, por ser único.
    }

    //Os métodos seguintes são padrão para possíveis usos futuros.
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
}
