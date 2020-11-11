package model;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.Part;


public class ImgLogic extends HttpServlet{
	String imgName="";

	public String execute(Part part,String preImg) {

		// 一意のファイル名とするために、
		// 元ファイルの名前と、現在時刻を取得
		String name = this.getFileName(part);
		Date now = new Date();

		//画像添付の有無で書き込みの有無を分岐
		if(name.isEmpty()) {
			// 画像の投稿がない場合、
			// 何もしない
		}else {
			// 画像の投稿がある場合、
			// 画像の書き込み

			//書き込みの準備
		String img = getServletContext().getRealPath("img") + "/" + now.getTime()+ name ;

		try {
			part.write(img);
		}catch(IOException e){
			e.fillInStackTrace();
		}

		//保存先のパスと保存名の決定
		//画像名が被った場合に上書きをされないための処理
		imgName += "/gotoFukuoka/img/" + now.getTime()+ name ;
		}

		return imgName;
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
