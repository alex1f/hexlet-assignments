package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer limit){
        int startIndex = (page - 1) * limit;
        int endIndex = startIndex + limit;
        return posts.subList(startIndex, endIndex);
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> getPost(@PathVariable String id){
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post data){
       Optional<Post> target = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
       if (target.isPresent()){
           Post current = target.get();
           current.setBody(data.getBody());
           current.setId(data.getId());
           current.setTitle(data.getTitle());
       }

       return data;
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable String id){
      posts.removeIf(p -> p.getId().equals(id));
    }
    // END
}
