package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postRepository.findAll().stream()
                .map(this::mapPostToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id){
        return postRepository.findById(id)
                .map(this::mapPostToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    private PostDTO mapPostToDTO(Post post){
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());

        List<Comment> relevantComments = commentRepository.findByPostId(dto.getId());
        List<CommentDTO> commentDTOS = relevantComments.stream().map(this::mapCommentToDTO).toList();
        dto.setComments(commentDTOS);

        return dto;
    }

    private CommentDTO mapCommentToDTO(Comment comment){
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;
    }
}
// END
