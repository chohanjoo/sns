package mwohae.auth.service;

import mwohae.auth.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

public interface UserSecurityService extends UserDetailsService {
    Collection<GrantedAuthority> getAuthorities(String user_id);
    public UserDto readUser(String user_id);
    public void createUser(UserDto user);
    public PasswordEncoder passwordEncoder();
}
