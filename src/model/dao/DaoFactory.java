package model.dao;

import model.dao.impl.SelleDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SelleDaoJDBC();
	}
	
	
}
