package mwohae.auth.service;

import mwohae.auth.dto.UserDto;
import mwohae.auth.request.CreateUserRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

public interface UserSecurityService extends UserDetailsService {
    public Collection<GrantedAuthority> getAuthorities(String user_id);
    public UserDto readUser(String user_id);
    public PasswordEncoder passwordEncoder();

    public void createUser(CreateUserRequest request);
    public void createAdmin(CreateUserRequest request);
    public UserDto retrieveUserById(String user_id);
}
