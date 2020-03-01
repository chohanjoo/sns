package sns.message.dto;

import lombok.Getter;
import lombok.Setter;
import sns.message.request.CreateFriendRequest;

@Getter
@Setter
public class FriendDto {
    private int id;
    private String user_id;
    private String friend_id;

    public static FriendDto create(CreateFriendRequest request){
        FriendDto friendDto = new FriendDto();

        friendDto.setUser_id(request.getUser_id());
        friendDto.setFriend_id(request.getFriend_id());

        return friendDto;
    }
}
