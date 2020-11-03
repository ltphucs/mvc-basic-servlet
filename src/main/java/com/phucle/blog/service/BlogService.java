package com.phucle.blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.phucle.blog.model.Blog;
import com.phucle.blog.model.Category;

public class BlogService implements BaseService<Blog> {
	
	private final String FINDALL_SQL="SELECT blogs.*, categories.name as categoryName FROM blogs INNER JOIN categories ON blogs.`id-category` = categories.id";
	private final String FINDBYID_SQL="SELECT blogs.*, categories.name as categoryName FROM blogs INNER JOIN categories ON blogs.`id-category` = categories.id WHERE blogs.id=?";
	
	private final String FINDBYCATEGORYID_SQL="SELECT blogs.*, categories.name as categoryName FROM blogs INNER JOIN categories "
			+ "ON blogs.`id-category` = categories.id WHERE categories.id=?";
	
	private final String SELECT_TOP3_SQL="SELECT blogs.*, categories.name as categoryName "
			+ "FROM blogs INNER JOIN categories ON blogs.`id-category` = categories.id "
			+ "ORDER BY blogs.`publish-date` DESC LIMIT 3";
	
	private final String SELECT_TOP12_SQL="SELECT blogs.*, categories.name as categoryName "
			+ "FROM blogs INNER JOIN categories ON blogs.`id-category` = categories.id "
			+ "ORDER BY blogs.`publish-date` DESC LIMIT 8";
	
	private final String SELECT_TOP5_LASTEST_SQL="SELECT blogs.*, categories.name as "
			+ "categoryName FROM blogs INNER JOIN categories ON "
			+ "blogs.`id-category` = categories.id ORDER BY blogs.views "
			+ "DESC LIMIT 3";
	
	private final String SELECT_TOP3_RELATED_SQL="SELECT blogs.*, categories.name as "
			+ "categoryName FROM blogs INNER JOIN categories ON blogs.`id-category` = categories.id WHERE categories.id=? "
			+ "ORDER BY blogs.`publish-date` DESC LIMIT 3";
	
	
	private final String SELECT_TOP5_POPULAR_SQL="SELECT blogs.*, categories.name as categoryName FROM blogs INNER JOIN categories ON blogs.`id-category` = categories.id ORDER BY blogs.views DESC LIMIT 3";
			
	//private final String SAVE_SQL="INSERT INTO categories(name,description) VALUES(?,?)";
	//private final String UPDATE_SQL="UPDATE categories SET name=?,description=? WHERE id=?";
	//private final String DELETE_SQL="DELETE FROM categories WHERE id=?";
	
	private final String INCVIEW_SQL=" UPDATE blogs SET views=views+1 WHERE id=?";

	private final String INCLIKE_SQL=" UPDATE blogs SET likes=likes+1 WHERE id=?";
	
	public BlogService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Blog> findAll() {
		List<Blog> rt = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(FINDALL_SQL)) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				
				
				 String title = rs.getString("title");
				 String shortContent = rs.getString("short-content");
				 String fullContent = rs.getString("full-content");
				 Date publishDate = rs.getDate("publish-date");
				 String imageUrl= rs.getString("image-url");
				 int views = rs.getInt("views");
				 int likes = rs.getInt("likes");
				 int idCategory = rs.getInt("id-category");
				 
				 String categoryName= rs.getString("categoryName");
				
				Blog temp = new Blog(id, title, shortContent,fullContent,publishDate,imageUrl,views,likes,idCategory,categoryName);
				rt.add(temp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return rt;
	}

	@Override
	public Blog findById(int id) {
		// TODO Auto-generated method stub
		Blog temp = null;

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(FINDALL_SQL)) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				 String title = rs.getString("title");
				 String shortContent = rs.getString("short-content");
				 String fullContent = rs.getString("full-content");
				 Date publishDate = rs.getDate("publish-date");
				 String imageUrl= rs.getString("image-url");
				 int views = rs.getInt("views");
				 int likes = rs.getInt("likes");
				 int idCategory = rs.getInt("id-category");
				
				 
				 String categoryName= rs.getString("categoryName");
				
				 temp = new Blog(id, title, shortContent,fullContent,publishDate,imageUrl,views,likes,idCategory,categoryName);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return temp;
	}

	public List<Blog> findByCate(int idCategory){
		List<Blog> rt = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.FINDBYCATEGORYID_SQL)) {
			st.setInt(1, idCategory);
			ResultSet rs=st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				
				
				 String title = rs.getString("title");
				 String shortContent = rs.getString("short-content");
				 String fullContent = rs.getString("full-content");
				 Date publishDate = rs.getDate("publish-date");
				 String imageUrl= rs.getString("image-url");
				 int views = rs.getInt("views");
				 int likes = rs.getInt("likes");
				// int idCategory = rs.getInt("id-category");
				 String categoryName= rs.getString("categoryName");
				
				Blog temp = new Blog(id, title, shortContent,fullContent,publishDate,
						imageUrl,views,likes,idCategory,categoryName);
				
				rt.add(temp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return rt;
	}
	
	public List<Blog> findTop3(){
		List<Blog> rt = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.SELECT_TOP3_SQL)) {
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				
				
				 String title = rs.getString("title");
				 String shortContent = rs.getString("short-content");
				 String fullContent = rs.getString("full-content");
				 Date publishDate = rs.getDate("publish-date");
				 String imageUrl= rs.getString("image-url");
				 int views = rs.getInt("views");
				 int likes = rs.getInt("likes");
				 int idCategory = rs.getInt("id-category");
				 String categoryName= rs.getString("categoryName");
				
				Blog temp = new Blog(id, title, shortContent,fullContent,publishDate,
						imageUrl,views,likes,idCategory,categoryName);
				
				rt.add(temp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return rt;
	}
	
	
	public List<Blog> findTop5(){
		List<Blog> rt = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.SELECT_TOP5_LASTEST_SQL)) {
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				
				
				 String title = rs.getString("title");
				 String shortContent = rs.getString("short-content");
				 String fullContent = rs.getString("full-content");
				 Date publishDate = rs.getDate("publish-date");
				 String imageUrl= rs.getString("image-url");
				 int views = rs.getInt("views");
				 int likes = rs.getInt("likes");
				 int idCategory = rs.getInt("id-category");
				 String categoryName= rs.getString("categoryName");
				
				Blog temp = new Blog(id, title, shortContent,fullContent,
						publishDate,imageUrl,views,likes,idCategory,categoryName);
				rt.add(temp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return rt;
	}
	
	public List<Blog> findTop3Related(int categoryId){
		List<Blog> rt = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.SELECT_TOP3_RELATED_SQL)) {
			st.setInt(1,categoryId);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				
				
				 String title = rs.getString("title");
				 String shortContent = rs.getString("short-content");
				 String fullContent = rs.getString("full-content");
				 Date publishDate = rs.getDate("publish-date");
				 String imageUrl= rs.getString("image-url");
				 int views = rs.getInt("views");
				 int likes = rs.getInt("likes");
				 int idCategory = rs.getInt("id-category");
				 String categoryName= rs.getString("categoryName");
				
				Blog temp = new Blog(id, title, shortContent,fullContent,publishDate,imageUrl,views,likes,idCategory,categoryName);
				rt.add(temp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return rt;
	}
	
	
	public List<Blog> findTop12(){
		List<Blog> rt = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.SELECT_TOP12_SQL)) {
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				
				
				 String title = rs.getString("title");
				 String shortContent = rs.getString("short-content");
				 String fullContent = rs.getString("full-content");
				 Date publishDate = rs.getDate("publish-date");
				 String imageUrl= rs.getString("image-url");
				 int views = rs.getInt("views");
				 int likes = rs.getInt("likes");
				 int idCategory = rs.getInt("id-category");
				 String categoryName= rs.getString("categoryName");
				
				Blog temp = new Blog(id, title, shortContent,fullContent,publishDate,imageUrl,views,likes,idCategory,categoryName);
				rt.add(temp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return rt;
	}
	
	
	public List<Blog> findTop5Popular(){
		List<Blog> rt = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.SELECT_TOP5_POPULAR_SQL)) {
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				
				
				 String title = rs.getString("title");
				 String shortContent = rs.getString("short-content");
				 String fullContent = rs.getString("full-content");
				 Date publishDate = rs.getDate("publish-date");
				 String imageUrl= rs.getString("image-url");
				 int views = rs.getInt("views");
				 int likes = rs.getInt("likes");
				 int idCategory = rs.getInt("id-category");
				 String categoryName= rs.getString("categoryName");
				
				Blog temp = new Blog(id, title, shortContent,fullContent,publishDate,imageUrl,views,likes,idCategory,categoryName);
				rt.add(temp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return rt;
	}
	
	@Override
	public void save(Blog object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Blog object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public void incViews(int id) {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.INCVIEW_SQL)) {
			
			st.setInt(1, id);
			 st.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}

	public void incLikes(int id) {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement st = connection.prepareStatement(this.INCLIKE_SQL)) {
			
			st.setInt(1, id);
			 st.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
}
