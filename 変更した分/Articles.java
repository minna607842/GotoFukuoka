package model;

import java.io.Serializable;

/*
 * 記事情報に関するJavaBeans
 */

public class Articles implements Serializable {
	private int arcticle_id;
	private String title;
	private String area;
	private String category;
	private String text;
	private String address;
	private String user_id;
	private String torokubi;
	private int good;
	private String img;

	public Articles(int arcticle_id,
			String title,
			String area,
			String category,
			String text,
			String address,
			String user_id,
			String torokubi,
			int good,
			String img) {
		this.arcticle_id = arcticle_id;
		this.title = title;
		this.area = area;
		this.category = category;
		this.text = text;
		this.address = address;
		this.user_id = user_id;
		this.torokubi = torokubi;
		this.good = good;
		this.img= img;
	}

	public Articles(String title,
			String area,
			String category,
			String text,
			String address,
			String img) {
		this.title = title;
		this.area = area;
		this.category = category;
		this.text = text;
		this.address = address;
		this.img = img;
	}

	public int getArcticle_id() {
		return arcticle_id;
	}

	public String getTitle() {
		return title;
	}

	public String getArea() {
		return area;
	}

	public String getCategory() {
		return category;
	}

	public String getText() {
		return text;
	}

	public String getAddress() {
		return address;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getTorokubi() {
		return torokubi;
	}

	public int getGood() {
		return good;
	}

	public String getImg() {
		return img;
	}

	public void setArcticle_id(int arcticle_id) {
		this.arcticle_id = arcticle_id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setTorokubi(String torokubi) {
		this.torokubi = torokubi;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public void setImg(String img) {
		this.img = img;
	}



}