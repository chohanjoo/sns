package mwohae.user.dto;

import lombok.Getter;
import lombok.Setter;
import mwohae.user.request.CreateUserRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
public class UserDto implements UserDetails {
    private String id;
    private String pw;
    private String email;
    private String name;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDto create(CreateUserRequest request){
        UserDto userDto = new UserDto();

        userDto.id = request.getId();
        userDto.pw = request.getPw();
        userDto.email = request.getEmail();
        userDto.name = request.getName();

        userDto.isAccountNonExpired = true;
        userDto.isAccountNonLocked = true;
        userDto.isCredentialsNonExpired = true;
        userDto.isEnabled = true;

        return userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
