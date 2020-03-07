package mwohae.post.dao;

import mwohae.post.dto.FriendDto;
import mwohae.post.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@UserRepository
public interface UserDao {
    public List<FriendDto> retrieveUserFriends(String user_id);
    public UserDto retrieveUserById(String user_id);
    public List<GrantedAuthority> retrieveAuthority(String user_id);
}
