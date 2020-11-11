package model;

import java.io.Serializable;
import java.util.ArrayList;


/*
 * ユーザー情報に関するJavaBeans
 */

public class Users implements Serializable {

	private String user_id;
	private String password;
	private ArrayList<Articles> userInsertList=null;
	private String img;
	private String text;


	public Users(String user_id, String password) {
		this.user_id = user_id;
		this.password =password;

	}

	public Users(String user_id, String password,String text,String img) {
		this.user_id = user_id;
		this.password =password;
		this.text= text;
		this.img= img;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Articles> getUserInsertList() {
		return userInsertList;
	}

	public void setUserInsertList(ArrayList<Articles> userInsertList) {
		this.userInsertList = userInsertList;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}



}