package mwohae.auth.service;

import mwohae.auth.dto.UserDto;
import mwohae.auth.request.CreateUserRequest;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface UserService {
    public void createUser(CreateUserRequest request);
    public void createAdmin(CreateUserRequest request);
    public UserDto retrieveUserById(String user_id);

    public Collection<GrantedAuthority> getAuthorities(String user_id);

}
