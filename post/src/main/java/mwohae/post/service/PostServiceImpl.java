package mwohae.post.service;

import mwohae.post.dao.PostDao;
import mwohae.post.dao.UserDao;
import mwohae.post.dto.FriendDto;
import mwohae.post.dto.PostDto;
import mwohae.post.dto.PostLikeDto;
import mwohae.post.request.CreatePostLikeRequest;
import mwohae.post.request.CreatePostRequest;
import mwohae.post.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Autowired
    UserDao userDao;

    @Value("${server.url}")
    String server_url;

    @Override
    public List<PostDto> retrieveAllPost() {
        return this.postDao.retrieveAllPost();
    }

    @Override
    public List<PostDto> retrieveFollowingPost(String token, String user_id) {
        RestTemplate restTemplate = new RestTemplate();
//        List<FriendDto> followingUser = userDao.retrieveUserFriends(user_id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-auth-token", token);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<ListResult<FriendDto>> response = restTemplate.exchange(server_url + "/user/friend?user_id=" +user_id, HttpMethod.GET, entity,  new ParameterizedTypeReference<ListResult<FriendDto>>(){});
        List<FriendDto> followingUser = response.getBody().getList();

        List<PostDto> postList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        Date _yesterday = calendar.getTime();


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String yesterday = format.format(_yesterday);


        for(FriendDto follower : followingUser){
            List<PostDto> follower_posts = postDao.retrieveLimitPostById(follower.getFriend_id(),yesterday);
            postList.addAll(follower_posts);

        }

        List<PostLikeDto> postLikeList = this.postDao.retrievePostLikes(user_id);

        List<Integer> postIdList = new ArrayList<>();

        for(PostLikeDto postLikeDto : postLikeList){
            postIdList.add(postLikeDto.getPost_id());
        }

        for(PostDto post : postList){
            if(postIdList.contains(post.getId())){
                post.setLike(true);
            }
            else{
                post.setLike(false);
            }
        }

        return postList;
    }

    @Override
    public void createPost(CreatePostRequest request) {
        PostDto postDto = PostDto.create(request);

        postDao.createPost(postDto);
    }

    @Override
    public void createPostLike(CreatePostLikeRequest request) {
        PostLikeDto postLikeDto = PostLikeDto.create(request);

        postDao.createPostLike(postLikeDto);
    }

    @Override
    public List<PostLikeDto> retrievePostLikes(String userId) {
        return this.postDao.retrievePostLikes(userId);
    }

    @Override
    public List<PostDto> retrieveLikePostList(String userId) {
        return this.postDao.retrieveLikePostList(userId);
    }

    @Override
    public void deletePostLike(CreatePostLikeRequest request) {
        postDao.deletePostLike(request);
    }
}
