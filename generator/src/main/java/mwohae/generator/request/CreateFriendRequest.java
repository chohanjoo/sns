package mwohae.generator.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFriendRequest {
    private String user_id;
    private String friend_id;
}
