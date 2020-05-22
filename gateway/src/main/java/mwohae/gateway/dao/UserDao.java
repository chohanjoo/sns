package mwohae.gateway.dao;

import mwohae.gateway.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@UserRepository
public interface UserDao {
    public UserDto retrieveUserById(String user_id);
    public List<GrantedAuthority> retrieveAuthority(String user_id);
}
