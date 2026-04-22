package com.caixaba.microtypi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.capgemini.microtypi.api.domain.Comment;
import com.capgemini.microtypi.api.domain.Post;

@Service
public class TypiServiceImpl implements TypiService {
	private static final String URL_BASE = "https://jsonplaceholder.typicode.com";
	private final WebClient webClient;

	public TypiServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(URL_BASE).build();
	}

	@Override
	public List<Post> getPosts() {
		Optional<Post[]> response = Optional.ofNullable(
				this.webClient
						.get()
						.uri("/posts")
						.retrieve()
						.bodyToMono(Post[].class)
						.block());

		return response.map(Arrays::asList).orElseGet(List::of);
	}

	@Override
	public List<Comment> getCommentsByPostId(Integer postId) {
		if (postId == null) {
			return List.of();
		}

		Optional<Comment[]> response = Optional.ofNullable(
				this.webClient
						.get()
						.uri(uriBuilder -> uriBuilder.path("/comments").queryParam("postId", postId).build())
						.retrieve()
						.bodyToMono(Comment[].class)
						.block());

		return response.map(Arrays::asList).orElseGet(List::of);
	}

}
