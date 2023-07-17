package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SelleDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SelleDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		
	}

	@Override
	public void update(Seller obj) {
		
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
				ps = conn.prepareStatement("SELECT seller.*, department.Name as DepName\n"
						+ "FROM seller inner join department\n"
						+ "ON seller.DepartmentId = department.Id\n"
						+ "WHERE seller.Id = ?");
				
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if(rs.next()) {
					Department dep = instatiateDepartment(rs);
					Seller seller = instatiateSeller(rs, dep);
					return seller;
				}
				return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}

	private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department dep) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
				ps = conn.prepareStatement("SELECT seller.*, department.Name as DepName\n"
						+ "FROM seller inner join department\n"
						+ "ON seller.DepartmentId = department.Id\n"
						+ "WHERE DepartmentId = ?\n"
						+ "ORDER BY Name");
				
				ps.setInt(1, dep.getId());
				rs = ps.executeQuery();
				
				List<Seller> list = new ArrayList<>();
				Map<Integer, Department> map = new HashMap<>();
				
				while(rs.next()) {
					Department dep1 = map.get(rs.getInt("DepartmentId"));
					
					if(dep1 == null) {
						dep1 = instatiateDepartment(rs);
						map.put(rs.getInt("DepartmentId"), dep1);
					}
					Seller seller = instatiateSeller(rs, dep1);
					list.add(seller);
				}
				return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}

}
