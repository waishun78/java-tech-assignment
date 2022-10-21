/*
 * Copyright (c) 2022. Czech Technical University in Prague, Faculty of Information Technology.
 *
 * Project Social Network. Created for Java Technology course.
 *
 *  Authors:
 *  Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *  Jan Blizničenko (jan.bliznicenko@fit.cvut.cz)
 *  Filip Glazar (glazafil@fit.cvut.cz)
 *
 *  This code is intended for educational purposes only.
 *
 */

import cz.cvut.fit.tjv.social_network.File_BeansConfig;
import cz.cvut.fit.tjv.social_network.InMem_BeansConfig;
import cz.cvut.fit.tjv.social_network.business.MediaPartService;
import cz.cvut.fit.tjv.social_network.business.PostService;
import cz.cvut.fit.tjv.social_network.business.UserService;
import cz.cvut.fit.tjv.social_network.dao.UserFileRepository;
import cz.cvut.fit.tjv.social_network.domain.ImagePart;
import cz.cvut.fit.tjv.social_network.domain.MediaPartKey;
import cz.cvut.fit.tjv.social_network.domain.Post;
import cz.cvut.fit.tjv.social_network.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URI;

/**
 * Main class to test this application.
 */
public class TestSNBackend {


    public static UserService xy() {
        return new UserService(null);
    }
//    /**
//     * The main method of the application. When the application is run, this method is invoked.
//     *
//     * @param args command line arguments of the application
//     */

//    public static void testImplementation(User usr,Post post, UserService usrSvc, PostService postSvc, MediaPartService mediaSvc){
//
//    }
    public static void main(final String[] args) throws BeansException {
        ApplicationContext ctx;

        if (args[0].equals("InMem")){
            // Creating a new context using the InMem_BeansConfig class
            ctx = new AnnotationConfigApplicationContext(InMem_BeansConfig.class);


        } else {
            // Creating a new context using the InMem_BeansConfig class
            ctx = new AnnotationConfigApplicationContext(File_BeansConfig.class);

        }

//        var usr = ctx.getBean(User.class);
//        var post = ctx.getBean(Post.class);
        var usrSvc = ctx.getBean("userService", UserService.class);
        var mediaSvc = ctx.getBean(MediaPartService.class);
        var postSvc = ctx.getBean(PostService.class);

        // Testing Functionality
        User user = new User("1");
        usrSvc.create(user);
        Post post = new Post((long) 1241,null, user,"something");
        postSvc.create(post);
        System.out.println("Initial listing of");
        System.out.println("Users:");
        System.out.println(usrSvc.readAll());
        System.out.println("Posts:");
        System.out.println(postSvc.readAll());
        System.out.println("Media parts:");
        System.out.println(mediaSvc.readAll());
        System.out.println("END");

    }
}
