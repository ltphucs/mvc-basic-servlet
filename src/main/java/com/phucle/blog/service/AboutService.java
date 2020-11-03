package com.phucle.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import com.phucle.blog.model.About;
import com.phucle.blog.model.Blog;

public class AboutService implements BaseService<About> {
	
	private final String GETABOUT_SQL="SELECT * FROM about LIMIT 1";
	
	

	public AboutService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<About> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public About findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(About object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(About object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	
	public About getAbout() {
		About temp = null;

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.GETABOUT_SQL)) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				 String title = rs.getString("title");
				 String shortContent = rs.getString("shortcontent");
				 String fullContent = rs.getString("content");
				 String imageUrl= rs.getString("imageurl");
				 
				 temp = new About(0, title, shortContent,fullContent,imageUrl);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return temp;
	}

}
