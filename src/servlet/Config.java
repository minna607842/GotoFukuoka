package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Users;


@WebServlet("/Config")
@MultipartConfig(location = "C:/temp")
public class Config extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/config.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
//		String icon = request.getParameter("icon");
		String text = request.getParameter("text");


		// 画像ファイルの書込＆データベースに保存するパスの作成
		String imgpath = null;
		Part part = request.getPart("icon");

		// 先生のお勧めは、Date型のタイムスタンプ
		String name = this.getFileName(part);
		System.out.println(name);

		if(name.isEmpty()) {
			// 画像の投稿がない場合、
			// 何もしない
		}else {
			// 画像の投稿がある場合、
			// 画像の書き込み
		String img = getServletContext().getRealPath("img") + "/" + name;

		part.write(img);
		imgpath = "/gotoFukuoka/img/" + name;
		}

		// ログインユーザーのインスタンスに保存
				//セッションスコープからログイン情報を取得
			HttpSession session = request.getSession();
			Users loginUser=(Users) session.getAttribute("loginUser");
			Users temporary= new Users(loginUser.getUser_id(),loginUser.getPassword(),text,imgpath);

				//セッションスコープに仮登録情報を保存
			session.setAttribute("temporary", temporary);


		String msg="";
		// 入力値の上限
		if (text.length()>300) {
			msg +="テキストは300文字以内で入力してください。<br>現在の入力値は"+text.length()+"文字です";
		}

		// 入力値チェックの結果により、フォワード先を変更
		String path="";

		if(msg.length()!=0) {
			// 入力値チェックNGの場合、
			// 処理結果メッセージをリクエストスコープに保存し、
			// 投稿画面へフォワード
			request.setAttribute("msg", msg);

			path="/WEB-INF/jsp/config.jsp";
		}else {
			// 入力チェックOKの場合、
			// 確認画面へフォワード
			path="/WEB-INF/jsp/profileConfirmation.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);



	}

	// リクエストヘッダーから「画像ファイル名」を取り出すメソッド
	private String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}
		return name;
	}

}
