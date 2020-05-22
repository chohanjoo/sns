package mwohae.user.service;

import mwohae.user.dao.UserDao;
import mwohae.user.dto.FriendDto;
import mwohae.user.dto.ProfileDto;
import mwohae.user.dto.UserDto;
import mwohae.user.request.CreateFriendRequest;
import mwohae.user.request.DeleteFriendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> retrieveAllUser() {
        return this.userDao.retrieveAllUser();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(String user_id) {
        List<GrantedAuthority> authorities = userDao.retrieveAuthority(user_id);

        return authorities;
    }

    @Override
    public ProfileDto retrieveUserProfile(String user_id) {
        return this.userDao.retrieveUserProfile(user_id);
    }

    @Override
    public List<FriendDto> retrieveUserFriends(String user_id) {
        return this.userDao.retrieveUserFriends(user_id);
    }

    @Override
    public List<FriendDto> retrieveRecommendFriends(String user_id) {
        List<FriendDto> user_friend = userDao.retrieveUserFriends(user_id);
        Map<String,Object> map = new HashMap<String, Object>();
        List<FriendDto> recommendFriendList = new ArrayList<>();

        map.put("user_id",user_id);
        map.put("list",user_friend);

        for(int i=0; i<user_friend.size();++i){
            map.put("friend_id",user_friend.get(i).getFriend_id());
            List<FriendDto> friendList = userDao.retrieveRecommendFriends(map);

            recommendFriendList.addAll(friendList);
            map.remove("friend_id");
        }
        return recommendFriendList;
    }

    @Override
    public void createUserFriend(CreateFriendRequest request) {
        FriendDto friendDto = userDao.retrieveFriend(request.getUser_id(),request.getFriend_id());

        if(friendDto == null){
            friendDto = FriendDto.create(request);
            userDao.createUserFriend(friendDto);
        }
    }

    @Override
    public void deleteUserFriend(DeleteFriendRequest request) {
        userDao.deleteUserFriend(request);
    }

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        UserDto user = userDao.retrieveUserById(user_id);
        user.setAuthorities(getAuthorities(user_id));

        return user;
    }
}
