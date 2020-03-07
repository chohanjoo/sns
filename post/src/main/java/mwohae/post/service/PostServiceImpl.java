package mwohae.post.service;

import mwohae.post.dao.PostDao;
import mwohae.post.dao.UserDao;
import mwohae.post.dto.FriendDto;
import mwohae.post.dto.PostDto;
import mwohae.post.request.CreatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<PostDto> retrieveAllPost() {
        return this.postDao.retrieveAllPost();
    }

    @Override
    public List<PostDto> retrieveFollowingPost(String user_id) {
        List<FriendDto> followingUser = userDao.retrieveUserFriends(user_id);

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

        return postList;
    }

    @Override
    public void createPost(CreatePostRequest request) {
        PostDto postDto = PostDto.create(request);

        postDao.createPost(postDto);
    }
}
