package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticlesDAO;
import model.Articles;


@WebServlet("/ArticlesSearch")
public class ArticlesSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//検索ボックスのフォームからのデータを受け取り
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("search");
		String category = request.getParameter("category");
		String area=request.getParameter("area");
		String orderBy = request.getParameter("orderBy");


		//検索条件に該当する情報を記事テーブルから情報を取得
		ArticlesDAO articlesDAO = new ArticlesDAO();
		List<Articles> articlesList = articlesDAO.select(title,category,orderBy);

		//結果をリクエストへ保存
		request.setAttribute("articlesList",articlesList);

		//フォワード
		RequestDispatcher dispatcher =	request.getRequestDispatcher("/contents.jsp");
		dispatcher.forward(request, response);



	}

}
