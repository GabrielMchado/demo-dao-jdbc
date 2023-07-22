package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class ProgramDepartment {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao depdao = DaoFactory.creatDepartmentDao();
		
		System.out.println("===== TEST 1: Department findById ======");
		Department dep = depdao.findById(2);
		System.out.println(dep);
		
		System.out.println("\n===== TEST 2: Department findAll ======");
		List<Department> list = depdao.findAll();
		list.forEach(System.out::println);
		
		System.out.println("\n===== TEST 3: Department insert ======");
		Department newdep = new Department(null, "Shoes");
		depdao.insert(newdep);
		System.out.println("Inserted! New id = " + newdep.getId());

		System.out.println("\n===== TEST 4: Department update ======");
		Department dep2 = depdao.findById(6);
		dep2.setName("Food");
		depdao.update(dep2);
		System.out.println("Update completed");
		
		System.out.println("\n===== TEST 5: Department delete ======");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		depdao.deleteById(id);
		System.out.println("Delete completed");
		
		sc.close();
		
	}
	
}
