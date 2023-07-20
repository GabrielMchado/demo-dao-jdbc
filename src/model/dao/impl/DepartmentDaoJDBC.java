package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO department (Id, Name) \n"
					+ "VALUES (?, ?) ", Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getName());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Unexpected error! No rows affected");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM coursejdbc.department\n"
					+ "WHERE Id = ?;");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
				return dep;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM coursejdbc.department");
			rs = ps.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while(rs.next()) {
				Department dep = instatiateDepartment(rs);
				list.add(dep);
			}
			return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}
	
	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

}
