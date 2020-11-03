package com.phucle.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.phucle.blog.model.Category;


public class CategoryService implements BaseService<Category> {

	
	private final String FINDALL_SQL="SELECT * FROM categories";
	private final String FINDBYID_SQL="SELECT * FROM categories WHERE id=?";
	
			
	private final String SAVE_SQL="INSERT INTO categories(name,description) VALUES(?,?)";
	private final String UPDATE_SQL="UPDATE categories SET name=?,description=? WHERE id=?";
	private final String DELETE_SQL="DELETE FROM categories WHERE id=?";
	
	
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		List<Category> rt = new ArrayList<>();
		
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(FINDALL_SQL)) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Category temp = new Category(id,name,description);
				rt.add(temp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return rt;
		
	}

	@Override
	public Category findById(int id) {
		// TODO Auto-generated method stub
		Category rt= null;
		
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.FINDBYID_SQL)) {
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String name = rs.getString("name");
				String description = rs.getString("description");
				rt= (new Category(id,name,description));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return rt;
	}

	@Override
	public void save(Category object) {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.SAVE_SQL)) {
			st.executeQuery("SET NAMES 'UTF8'");
			st.executeQuery("SET CHARACTER SET 'UTF8'");
			//THIET LAP THAM SO
			st.setString(1, object.getName());
			st.setString(2, object.getDescription());
			
			st.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Category object) {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.UPDATE_SQL)) {
			//st.executeQuery("SET NAMES 'UTF8'");
			//st.executeQuery("SET CHARACTER SET 'UTF8'");
			//THIET LAP THAM SO
			st.setString(1, object.getName());
			st.setString(2, object.getDescription());
			st.setInt(3, object.getId());
			st.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement st = connection.prepareStatement(this.DELETE_SQL)) {
					//THIET LAP THAM SO
					
					st.setInt(1, id);
					st.executeUpdate();
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
	}

}
