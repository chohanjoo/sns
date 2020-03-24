package mwohae.generator.dao;

import mwohae.generator.dto.FriendDto;
import mwohae.generator.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@UserRepository
public interface UserDao {
    public List<UserDto> retrieveAllUser();
    public UserDto retrieveUserById(String user_id);
    public void createUser(UserDto userDto);
    public void createAuthority(UserDto user);
    public void createUserProfile(String user_id);

    public FriendDto retrieveFriend(String user_id, String friend_id);
    public void createUserFriend(FriendDto friendDto);

    public List<GrantedAuthority> retrieveAuthority(String user_id);

}
