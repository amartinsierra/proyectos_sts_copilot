package com.caixaba.microtypi.service;

import java.util.List;

import com.capgemini.microtypi.api.domain.Comment;
import com.capgemini.microtypi.api.domain.Post;

public interface TypiService {
	List<Post> getPosts();
	List<Comment> getCommentsByPostId(Integer postId);
	Post addPost(Post post);
}
