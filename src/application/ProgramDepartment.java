package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class ProgramDepartment {

	public static void main(String[] args) {
		
		DepartmentDao depdao = DaoFactory.creatDepartmentDao();
		
		System.out.println("===== TEST 1: Department findById ======");
		Department dep = depdao.findById(2);
		System.out.println(dep);
		
		System.out.println("\n===== TEST 2: Department findAll ======");
		List<Department> list = depdao.findAll();
		list.forEach(System.out::println);
		
	}
	
}
