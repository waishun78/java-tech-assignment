# java-tech-assignment
To enable the use of UserInMemRepository+PostInMemRepository for persistance layer, enter InMem as the first argument. Else, the project would use UserFileRepository+PostFileRepository as the default.

## How it works
Two Beans configuration files are written to create a Context object using either the File implementation or the in memory implementation. 

Configuration file for File Bean Configuration

```
import java.nio.file.Path;

@Configuration
public class File_BeansConfig {
    final Path p = Path.of("storage/txt");

    @Bean
    public User user() {
        return new User("john");
    }

    @Bean
    public Post post() {
        return new Post((long) 1241,null, this.user(),"something");
    }

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
```
A file directory was intialised for writing the objects and saving them. It also have methods that return User and Post methods, similar to the InMem_BeansConfig file except using FileRepository DAO objects.

Based on the argument taken, an if statement will initialise the AnnotationConfigApplicationContext accordingly. This context is then used to getBean for the different java objects. To check the implementation, we used these objects returned to create User and Post objects within UserService and PostService accordingly.


