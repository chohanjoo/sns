package sns.message.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sns.message.request.CreateUserRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@SuppressWarnings("serial")
public class UserDto implements UserDetails {
    private String id;
    private String pw;
    private String email;
    private String name;
    private List<String> roles;

    public static UserDto create(CreateUserRequest request){
        UserDto userDto = new UserDto();

        userDto.id = request.getId();
        userDto.pw = request.getPw();
        userDto.email = request.getEmail();
        userDto.name = request.getName();

        return userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> grants = new ArrayList<SimpleGrantedAuthority>();

//        for(String role : roles) {
//            grants.add(new SimpleGrantedAuthority(role));
//        }

        return grants;
    }

    public String getNickname() {
        return name;
    }

    @Override
    public String getPassword() {
        return "{noop}" + pw;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
