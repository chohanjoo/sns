package mwohae.user.dao;

import mwohae.user.dto.FriendDto;
import mwohae.user.dto.ProfileDto;
import mwohae.user.dto.UserDto;
import mwohae.user.request.DeleteFriendRequest;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

@UserRepository
public interface UserDao {
    public List<UserDto> retrieveAllUser();
    public List<FriendDto> retrieveRecommendFriends(Map<String,Object> map);
    public UserDto retrieveUserById(String user_id);

    public ProfileDto retrieveUserProfile(String user_id);
    public List<GrantedAuthority> retrieveAuthority(String user_id);

    public List<FriendDto> retrieveUserFriends(String user_id);
    public FriendDto retrieveFriend(String user_id, String friend_id);
    public void createUserFriend(FriendDto friendDto);
    public void deleteUserFriend(DeleteFriendRequest request);
}
