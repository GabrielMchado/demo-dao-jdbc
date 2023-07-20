package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class ProgramDepartment {

	public static void main(String[] args) {
		
		DepartmentDao depdao = DaoFactory.creatDepartmentDao();
		
		System.out.println("===== TEST 1: Seller findById ======");
		Department dep = depdao.findById(2);
		System.out.println(dep);
		
	}
	
}
