package model;

import java.util.List;

import dao.UsersDAO;

public class LoginLogic {
	boolean result=false;

	public boolean execute(Users user) {

		UsersDAO usersdao=new UsersDAO();
		List<Users> list=usersdao.select(user.getUser_id());

		for(Users users:list) {
			if(user.getPassword().equals(users.getPassword())) {
				result=true;
			} else {
				//userMapがnull（ユーザーリストが未登録）の場合はログイン失敗として処理
				result=false;
			}
		}

//		if(user.getPassword().equals(password)) {
//			result=true;
//		} else {
//			//userMapがnull（ユーザーリストが未登録）の場合はログイン失敗として処理
//			result=false;
//		}
		return result;
	}
}

