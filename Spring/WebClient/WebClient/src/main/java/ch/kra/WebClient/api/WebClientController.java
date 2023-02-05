package ch.kra.WebClient.api;

import ch.kra.WebClient.model.Post;
import ch.kra.WebClient.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class WebClientController {

    private final PostService postService;

    @GetMapping(path = "/posts")
    public Mono<Post[]> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(path = "/posts/{id}")
    public Mono<Post> getPost(@PathVariable int id) {
        Post post = postService.getPost(id).block();
        post.setUserId(5);
        return Mono.just(post);
    }
}
