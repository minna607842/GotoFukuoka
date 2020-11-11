package model;

import dao.GoodsDAO;

public class GetGoodLogic {
	public int execute(Goods goods) {

		GoodsDAO dao = new GoodsDAO();
		int good = dao.select(goods);

		return good;
	}
}
