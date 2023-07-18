package application;
 
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
			
		System.out.println("===== TEST 1: Seller findById ======");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n===== TEST 2: Seller findById ======");
		Department dep = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		list.forEach(System.out::println);
		
		System.out.println("\n===== TEST 3: Seller findAll ======");
		list = sellerDao.findAll();
		list.forEach(System.out::println);
		
		System.out.println("\n===== TEST 3: Seller insert ======");
		Seller seller1 = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep);
		sellerDao.insert(seller1);
		System.out.println("Insrted! New id = " + seller1.getId());
		
	}
	
	
}
