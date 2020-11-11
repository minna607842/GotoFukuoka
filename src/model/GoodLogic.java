package model;

import dao.GoodsDAO;

public class GoodLogic {

	public void goodPlus(Goods goods) {

		GoodsDAO dao = new GoodsDAO();
		dao.insert(goods);
		
		
	}
}
