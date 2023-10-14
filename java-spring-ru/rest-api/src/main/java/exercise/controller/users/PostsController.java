package exercise.controller.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> showUserPosts(@PathVariable String id){
        List<Post> userPosts = posts.stream()
                .filter(p -> p.getUserId() == Integer.parseInt(id))
                .toList();
        if (!userPosts.isEmpty()){
            return ResponseEntity.ok(userPosts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> newUserPost(@PathVariable String id, @RequestBody Post post){
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());
        newPost.setSlug(post.getSlug());
        newPost.setUserId(Integer.parseInt(id));
        posts.add(newPost);
        return ResponseEntity.status(201).body(newPost);
    }
}
// END
