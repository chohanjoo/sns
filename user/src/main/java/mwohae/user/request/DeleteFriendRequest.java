package mwohae.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteFriendRequest {
    public String user_id;
    public String friend_id;
}
