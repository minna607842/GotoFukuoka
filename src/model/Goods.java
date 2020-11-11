package model;

import java.io.Serializable;

public class Goods implements Serializable{

	private String article_id;
	private String user_id;
	private int goodCnt;

	public int getGoodCnt() {
		return goodCnt;
	}
	public void setGoodCnt(int goodCnt) {
		this.goodCnt = goodCnt;
	}
	
	public Goods() {}
	public Goods(String article_id, String user_id) {
		this.article_id =article_id;
		this.user_id = user_id;
	}

	public String getArticle_id() {
		return article_id;
	}

	public void setArcticle_id(String article_id) {
		this.article_id = article_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


}
