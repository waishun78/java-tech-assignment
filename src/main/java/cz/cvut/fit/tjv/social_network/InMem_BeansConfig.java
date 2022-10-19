package cz.cvut.fit.tjv.social_network;

import cz.cvut.fit.tjv.social_network.business.MediaPartService;
import cz.cvut.fit.tjv.social_network.business.PostService;
import cz.cvut.fit.tjv.social_network.business.UserService;
import cz.cvut.fit.tjv.social_network.dao.*;
import cz.cvut.fit.tjv.social_network.domain.Post;
import cz.cvut.fit.tjv.social_network.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMem_BeansConfig {

    @Bean
    public User user() {
        return new User("john");
    }

    @Bean
    public Post post() {
        return new Post((long) 1241,null, this.user(),"something");
    }
    @Bean
    public UserInMemRepository userInMemRepository() {
        return new UserInMemRepository();
    }

    @Bean
    public MediaPartService mediaPartService() {
        return new MediaPartService(new MediaPartInMemRepository());
    }


    @Bean
    public UserService userService() {
        return new UserService(new UserInMemRepository());
    }

    @Bean
    public PostService postService() {
        return new PostService(new PostInMemRepository());
    }
}
