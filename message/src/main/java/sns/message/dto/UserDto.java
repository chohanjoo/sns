package sns.message.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sns.message.request.CreateUserRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto implements UserDetails {
    private String id;
    private String pw;
    private String email;
    private String name;

    private List<String> roles = new ArrayList<>();

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
        return  this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.pw;
    }

    @Override
    public String getUsername() {
        return this.id;
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
