package com.capgemini.microtypi.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caixaba.microtypi.service.TypiService;
import com.capgemini.microtypi.api.domain.Comment;
import com.capgemini.microtypi.api.domain.Post;

@RestController
public class TypiController {
	//Declara una variable de tipo TypiService para acceder a los métodos del servicio e inyectala por constructor
	private final TypiService typiService;
	public TypiController(TypiService typiService) {
		this.typiService = typiService;
	}
	
	//Implementa el método getPosts() que llame al método getPosts() del servicio y retorne la lista de posts
	//asocialo a la ruta "/posts" utilizando la anotación @GetMapping, y envuelve el resultado en un ResponseEntity para retornar un código de estado HTTP adecuado
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getPosts() {
		List<Post> posts = typiService.getPosts();
		return ResponseEntity.ok(posts);
	}
	
	//Implementa el método getCommentsByPostId() que llame al método getCommentsByPostId() del servicio y retorne la lista de comentarios para un post específico recibido
	//como variable en url, utiliza la ruta /comments{postId} y la anotación @GetMapping para asociar el método a esa ruta, y envuelve el resultado en un ResponseEntity<List<Comment>> para retornar un código de estado HTTP adecuado
	@GetMapping("/comments/{postId}")
	public ResponseEntity<List<Comment>> getCommentsByPostId(Integer postId) {
		List<Comment> comments = typiService.getCommentsByPostId(postId);
		return ResponseEntity.ok(comments);
	}
	
}
