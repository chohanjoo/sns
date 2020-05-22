package mwohae.auth.dao;

import mwohae.auth.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;

@UserRepository
public interface UserDao {
    public UserDto retrieveUserById(String user_id);
    public void createUser(UserDto userDto);
    public void createAuthority(UserDto user);
    public void createUserProfile(String user_id);
    public List<GrantedAuthority> retrieveAuthority(String user_id);

    public void deleteUser(String user_id);
    public void deleteAuthority(String user_id);

}
