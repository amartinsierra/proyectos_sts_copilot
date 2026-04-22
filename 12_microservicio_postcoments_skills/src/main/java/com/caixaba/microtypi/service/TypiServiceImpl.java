package com.caixaba.microtypi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.capgemini.microtypi.api.domain.Comment;
import com.capgemini.microtypi.api.domain.Post;

@Service
public class TypiServiceImpl implements TypiService {
	private final RestClient restClient;

	public TypiServiceImpl(RestClient typiRestClient) {
		this.restClient = typiRestClient;
	}
	

	@Override
	public List<Post> getPosts() {
		Post[] posts = this.restClient.get()
				.uri("/posts")
				.retrieve()
				.body(Post[].class);

		return Optional.ofNullable(posts).map(Arrays::asList).orElseGet(List::of);
	}

	@Override
	public List<Comment> getCommentsByPostId(Integer postId) {
		Comment[] comments = this.restClient.get()
				.uri(uriBuilder -> uriBuilder.path("/comments").queryParam("postId", postId).build())
				.retrieve()
				.body(Comment[].class);

		return Optional.ofNullable(comments).map(Arrays::asList).orElseGet(List::of);
	}


	@Override
	public Post addPost(Post post) {
		Post createdPost = this.restClient.post()
				.uri("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.body(post)
				.retrieve()
				.body(Post.class);

		return Optional.ofNullable(createdPost)
				.orElseThrow(() -> new IllegalStateException("No se ha podido crear el post (respuesta nula)"));
	}

}
