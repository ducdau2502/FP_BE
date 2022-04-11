package gethigh.fp_be.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gethigh.fp_be.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDetailImpl implements UserDetails {

    private static final Long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public AccountDetailImpl(Long id,
                             String username,
                             String email,
                             String password,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static AccountDetailImpl build(Account account) {
        List<GrantedAuthority> authorities = account.getRoles().stream()
                .map(accountRole -> new SimpleGrantedAuthority(accountRole.getName().name()))
                .collect(Collectors.toList());
        return new AccountDetailImpl(
                account.getId(),
                account.getUsername(),
                account.getEmail(),
                account.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
