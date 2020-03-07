package mwohae.post.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserSecurityService extends UserDetailsService {
    Collection<GrantedAuthority> getAuthorities(String user_id);
}
