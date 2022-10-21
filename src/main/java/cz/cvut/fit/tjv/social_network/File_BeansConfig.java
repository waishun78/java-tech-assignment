package cz.cvut.fit.tjv.social_network;

import cz.cvut.fit.tjv.social_network.business.MediaPartService;
import cz.cvut.fit.tjv.social_network.business.PostService;
import cz.cvut.fit.tjv.social_network.business.UserService;
import cz.cvut.fit.tjv.social_network.dao.*;
import cz.cvut.fit.tjv.social_network.domain.Post;
import cz.cvut.fit.tjv.social_network.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
public class File_BeansConfig {
    final Path p = Path.of("storage/t");

//    @Bean
//    public User user() {
//        return new User("john");
//    }
//
//    @Bean
//    public Post post() {
//        return new Post((long) 1241,null, this.user(),"something");
//    }

    @Bean
    public UserFileRepository userFileRepository() {
        return new UserFileRepository(this.p);
    }

    @Bean
    public MediaPartService mediaPartService() {
        return new MediaPartService(new MediaPartFileRepository(this.p));
    }


    @Bean
    public UserService userService() {
        return new UserService(new UserFileRepository(this.p));
    }

    @Bean
    public PostService postService() {
        return new PostService(new PostFileRepository(this.p));
    }
}
