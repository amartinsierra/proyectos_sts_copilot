package com.caixaba.microtypi.service;

import java.util.List;

import com.capgemini.microtypi.api.domain.Comment;
import com.capgemini.microtypi.api.domain.Post;

public class TypiServiceImpl implements TypiService {
	private static final String url="https://jsonplaceholder.typicode.com/";
	@Override
	public List<Post> getPosts() {
		//
	}

	@Override
	public List<Comment> getCommentsByPostId(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

}
