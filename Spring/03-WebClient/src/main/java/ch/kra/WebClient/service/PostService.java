package ch.kra.WebClient.service;

import ch.kra.WebClient.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PostService {
    private final WebClient webClient;

    public PostService(@Value("${postService.baseUrl}") String baseUrl,  WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public Mono<Post[]> getAllPosts() {
        return this.webClient.get().uri("/posts")
                .retrieve().bodyToMono(Post[].class);
    }

    public Mono<Post> getPost(final int index) {
        return this.webClient.get().uri("/posts/{index}", index)
                .retrieve().bodyToMono(Post.class);
    }
}
