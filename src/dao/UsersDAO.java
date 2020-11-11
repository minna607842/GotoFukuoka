package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Users;


/*
 * ユーザーに関するＤＡＯクラス
 */

public class UsersDAO {

    private final String url = "jdbc:postgresql://localhost:5432/gotofukuoka";
    private final String user = "postgres";
    private final String passWord = "test";

	/********************************************************************************
	 * 「ユーザー」テーブルからユーザー名を検索し、検索結果を返します。
	 ********************************************************************************/
    public List<Users> select(String user_id) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Users> aRecords = null;

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(url, user, passWord);

	         /* SELECT文の準備 */
	         String sql = "SELECT * ";
	         sql += "FROM Users ";
	         sql += "WHERE user_id like ? ";
	         sql += "ORDER BY user_id DESC; ";
	         st = con.prepareStatement(sql);
	         st.setString(1, "%" + user_id + "%");

	         /* SELECT文の実行 */
	         rs = st.executeQuery();

	         /* 結果をリストに移し替える */
	         aRecords = makeResultData(rs);

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}

			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}

        return aRecords;
    }

	/********************************************************************************
	 * 「ユーザー」テーブルにプロフィール文、アイコンを更新
	 ********************************************************************************/

    public int profileUpdate(	String user_id,
    							String text,
    							String img) {
        Connection con = null;
        PreparedStatement st = null;
        int pUpdateCnt = 0;		// 更新件数

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(url, user, passWord);

	         /* INSERT文の準備 */
	         String sql = "";
	         sql = "UPDATE users ";
	         if(img=="") {
		         sql += "SET text= ? ";
	         }else {
		         sql += "SET text= ? , img = ?";
	         }
	    	 sql += "WHERE user_id = ? ; ";

	    	 st = con.prepareStatement(sql);
	         st.setString(1, text);

	         if(img=="") {
		         st.setString(2, user_id);

	         }else {
		         st.setString(2, img);
		         st.setString(3, user_id);
	         }

	         System.out.println("sql準備完了");


	         /* INSERT文の実行 */
	         pUpdateCnt = st.executeUpdate();
	         System.out.println("実行");

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	        /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}

        return pUpdateCnt;
    }

	/********************************************************************************
	 * 「ユーザー」テーブルに行を追加
	 ********************************************************************************/

    public int insert(String user_id,
    					String password) {
        Connection con = null;
        PreparedStatement st = null;
        int insCnt = 0;		// 更新件数

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(url, user, passWord);

	         /* INSERT文の準備 */
	         String sql = "";
	         sql = "INSERT INTO Users VALUES( ? , ? ,'プロフィール文','/gotoFukuoka/img/defaultIcon.jpg');";

	         st = con.prepareStatement(sql);
	         st.setString(1, user_id);
	         st.setString(2, password);
	         System.out.println("sql準備完了");


	         /* INSERT文の実行 */
	         insCnt = st.executeUpdate();
	         System.out.println("実行");

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	        /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}

        return insCnt;
    }

	/********************************************************************************
	 * 「ユーザー」テーブルにからユーザーidを元にを削除します
	 ********************************************************************************/

    public int delete(String id) {
        /* PostgreSQLへの接続情報 */
        Connection con = null;
        PreparedStatement st = null;
        int delCnt = 0;		// 削除件数

		try {
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(url, user, passWord);

	         /* SELECT文の準備 */
	         String sql = "";
	         sql = "DELETE FROM Users ";
	         sql += "Where user_id = ?;";

	         st = con.prepareStatement(sql);
	         st.setString(1, id);

	         /* DELETE文の実行 */
	         delCnt = st.executeUpdate();

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {}
			}

			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}

		return delCnt;
    }

    /**
     * 検索結果をリストで返します。
     */
    public ArrayList<Users> makeResultData(ResultSet rs) throws Exception {

    	// 全検索結果を格納するリストを準備
    	ArrayList<Users> aRecords = new ArrayList<Users>();

    	while(rs.next()) {
    		// 1行分のデータを読込む
    		String user_id = rs.getString("user_id");
    		String password = rs.getString("password");
    		String text = rs.getString("text");
    		String img = rs.getString("img");

    		// 1行分のデータを格納するインスタンス
    		Users aRecord = new Users(user_id,password,text,img);

            // リストに1行分のデータを追加する
            aRecords.add(aRecord);
    	}
    	return aRecords;
    }

}