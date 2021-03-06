package com.pzoom.ads.platform.mail.model;

import java.util.Calendar;

import com.pzoom.ads.platform.backend.model.ModelObject;
import com.pzoom.ads.platform.mail.type.NewsType;

public class News extends ModelObject<Long>{

	private String title;
	private String content;
	private Calendar createTime;
	private String newsFrom;
	private NewsType newsType;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getNewsFrom() {
		return newsFrom;
	}
	public void setNewsFrom(String newsFrom) {
		this.newsFrom = newsFrom;
	}
	public NewsType getNewsType() {
		return newsType;
	}
	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
	
	
	
}
