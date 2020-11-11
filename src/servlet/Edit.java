package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ArticlesDAO;
import model.Articles;
import model.Users;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
@MultipartConfig(location = "C:/temp")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String edit = request.getParameter("edit");

		String[] editType=edit.split(",");
		//編集モードをリクエストスコープに保存
		request.setAttribute("editmode",editType[0]);

		//編集する記事idをリクエストスコープに保存
		request.setAttribute("articleId",editType[1]);
		System.out.println("リクエストスコープにぶちこむまで");

		//フォワード
		RequestDispatcher dispatcher =	request.getRequestDispatcher("/editConfirmation.jsp");
		dispatcher.forward(request, response);
		System.out.println("フォワードまで");


	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		//編集モード
		String editMode=request.getParameter("editMode");
		//記事のパラメータ
		String articleId=request.getParameter("articleId");
		String title = request.getParameter("title");
		String area = request.getParameter("area");
		String category = request.getParameter("category");
		String text = request.getParameter("text");
		String address = request.getParameter("address");

		// 画像ファイルの書込＆データベースに保存するパスの作成
		String img1 = null;
		Part part = request.getPart("img");

		//フォワード先の設定
		String path="";

		//記事ＤＡＯクラスを利用する準備
		ArticlesDAO articlesdao= new ArticlesDAO();


		if(editMode.equals("edit")) {
			/********************
			 * 記事更新モード
			 *********************/

			// 先生のお勧めは、Date型のタイムスタンプ
			String name = this.getFileName(part);

			if(name.isEmpty()) {
				// 画像の投稿がない場合、
				// 何もしない
			}else {
				// 画像の投稿がある場合、
				// 画像の書き込み
				String img = getServletContext().getRealPath("img") + "/" + name;

				part.write(img);
				img1 = "/gotoFukuoka/img/" + name;
			}
			if(address == null) {
				address = "";
			}

			String msg="";
			// 未入力チェック
			if (title == null || title.equals("")) {
				msg +="タイトルは必須項目です<br>";
			}

			if (category == "--選択してください--") {
				msg +="カテゴリを選択してください";
			}

			if (area == "--選択してください--") {
				msg +="エリアを選択してください";
			}

			if (text == null || text.equals("")) {
				msg +="内容は必須項目です";
			}

			if(msg.length()!=0) {
				// 入力値チェックNGの場合、
				// 処理結果メッセージをリクエストスコープに保存し、
				// 投稿画面へフォワード
				request.setAttribute("msg", msg);

				path="/editConfirmation.jsp";

			}else {
				// 入力チェックOKの場合、編集データを登録して
				// 確認画面へフォワード

				//登録データ更新
				int UpdateResult=articlesdao.articleUpdate(articleId,title,area,category,address,text,img1);
				System.out.println("記事の更新結果："+UpdateResult);

				//javaBeans Usersに登録してあるユーザーの記事一覧を再取得（マイページ表示のため）
				HttpSession session=request.getSession();
				Users loginUser = (Users) session.getAttribute("loginUser");
				String user_id = loginUser.getUser_id();

				ArrayList<Articles> userInsertList=(ArrayList<Articles>)articlesdao.se(user_id);
				loginUser.setUserInsertList(userInsertList);

				path="/myPage.jsp";
			}




		}else if(editMode.equals("delete")) {
			/********************
			 * 記事更新モード
			 *********************/

			int deleteResult = articlesdao.delete(articleId);
			System.out.println("記事の削除結果："+deleteResult);

			if(deleteResult !=0) {
			//javaBeans Usersに登録してあるユーザーの記事一覧を再取得（マイページ表示のため）
			HttpSession session=request.getSession();
			Users loginUser = (Users) session.getAttribute("loginUser");
			String user_id = loginUser.getUser_id();

			ArrayList<Articles> userInsertList=(ArrayList<Articles>)articlesdao.se(user_id);
			loginUser.setUserInsertList(userInsertList);

			}
			path="/myPage.jsp";
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
