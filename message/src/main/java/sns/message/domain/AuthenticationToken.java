package sns.message.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class AuthenticationToken {
    private String username;
    private Collection authorities;
    private String token;

    public AuthenticationToken(String username, Collection authorities, String token){
        this.username = username;
        this.authorities = authorities;
        this.token = token;
    }
}
