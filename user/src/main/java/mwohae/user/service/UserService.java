package mwohae.user.service;

import mwohae.user.dto.FriendDto;
import mwohae.user.dto.ProfileDto;
import mwohae.user.dto.UserDto;
import mwohae.user.request.CreateFriendRequest;
import mwohae.user.request.DeleteFriendRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    public List<UserDto> retrieveAllUser();
    public List<FriendDto> retrieveRecommendFriends(String user_id);
    public Collection<GrantedAuthority> getAuthorities(String user_id);

    public ProfileDto retrieveUserProfile(String user_id);

    public List<FriendDto> retrieveUserFriends(String user_id);
    public void createUserFriend(CreateFriendRequest request);
    public void deleteUserFriend(DeleteFriendRequest request);
}
