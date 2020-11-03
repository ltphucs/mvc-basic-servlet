package com.phucle.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.phucle.blog.model.User;

public class UserService implements BaseService<User> {

	private final String FINDALL_SQL="SELECT * FROM users";
	private final String FINDBYID_SQL="SELECT * FROM users WHERE id=?";
	private final String FINDBYUP_SQL= "SELECT * FROM users WHERE username=? AND password=?";
			
			
	private final String SAVE_SQL="INSERT INTO users(username,password,fullname) VALUES(?,?,?)";
	private final String UPDATE_SQL="UPDATE users SET username=?,fullname=? WHERE id=?";
	private final String DELETE_SQL="DELETE FROM users WHERE id=?";
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		
		List<User> rt = new ArrayList<>();
		
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(FINDALL_SQL)) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				String username = rs.getString("username");
				rt.add(new User(id,username,fullname));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return rt;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		User user =null;
		
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(FINDBYID_SQL)) {
			//THIET LAP THAM SO
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String fullname = rs.getString("fullname");
				String username = rs.getString("username");
				user = new User(id,username,fullname);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return user;
	}

	
	public User findByUsernamePassword(String username, String password) {
		User user = null;
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.FINDBYUP_SQL)) {
			//THIET LAP THAM SO
			st.setString(1, username);
			st.setString(2,password);
			
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String fullname = rs.getString("fullname");
				int id = rs.getInt("id");
				user = new User(id,username,fullname);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return user;
	}
	
	
	@Override
	public void save(User object) {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.SAVE_SQL)) {
			//THIET LAP THAM SO
			st.setString(1, object.getUsername());
			st.setString(2, object.getPassword());
			st.setString(3, object.getFullname());
			
			st.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(User object) {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.UPDATE_SQL)) {
			//THIET LAP THAM SO
			st.setString(1, object.getUsername());
			st.setString(2, object.getFullname());
			st.setInt(3, object.getId());
			
			st.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.DELETE_SQL)) {
			//THIET LAP THAM SO
			
			st.setInt(1,id );
			
			st.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	

}
