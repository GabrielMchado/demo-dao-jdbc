package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SelleDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SelleDaoJDBC(DB.getConnection());
	}
	
	public static DepartmentDao creatDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
	
}
