package com.phucle.blog.model;

import java.util.Date;

public class About {

	public About() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortContent() {
		return shortContent;
	}

	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	private int id;
	private String title;
	private String shortContent;
	private String content;
	private String imageUrl;
	
	public About(int id,String title,String shortContent,String content,String imageUrl) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.content=content;
		this.imageUrl= imageUrl;
		this.shortContent= shortContent;
		this.content=content;
	}
}
