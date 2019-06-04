package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionFactory;

public class UserDAO implements DAO<User> {
	
//	private static Logger log = Logger.getLogger(UserDAO.class);
	
	public User getByUsername(String username) {
		
	User user = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ers_users JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id WHERE ers_username = ?");
			pstmt.setString(1, username);
			System.out.println("SQL STATEMENT");
			
			List<User> users = this.mapResultSet(pstmt.executeQuery());
			if (!users.isEmpty()) user = users.get(0);
			System.out.println(user);
			
		} catch (SQLException e) {
			System.out.println("CAUGHT THE EXECPTION");
//			log.error(e.getMessage());
		}
		
		return user;
	}
	
	public User getByCredentials(String username, String password) {
		
		User user = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ers_users JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id WHERE ers_username = ? AND ers_password = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			List<User> users = this.mapResultSet(pstmt.executeQuery());
			if (!users.isEmpty()) user = users.get(0);
			
		} catch (SQLException e) {
//			log.error(e.getMessage());
		}
				
		return user;
	}
	
	@Override
	public List<User> getAll() {
		
		List<User> users = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM ers_users JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id");
			users = this.mapResultSet(rs);
			
			users.forEach(u -> u.setPassword("*********"));
			
		} catch (SQLException e) {
//			log.error(e.getMessage());
		}
		
		return users;
	}
	
	@Override
	public User getById(int userId) {
		
		User user = null;;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ers_users JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id WHERE ers_users_id = ?");
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			List<User> users = this.mapResultSet(rs);
			
			if (!users.isEmpty()) {
				user = users.get(0);
				user.setPassword("*********");
			}
			
		} catch (SQLException e) {
//			log.error(e.getMessage());
		}
		
		return user;
	}
	
	@Override
	public User add(User newUser) {
		
		Role newRole = new Role();
		newRole.setRoleId(2);
		newRole.setRoleName("employee");
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO ers_users VALUES (0, ?, ?, ?, ?, ?, 2)";
			
			String[] keys = new String[1];
			keys[0] = "ers_users_id";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, newUser.getUsername());
			pstmt.setString(2, newUser.getPassword());
			pstmt.setString(3, newUser.getFirstName());
			pstmt.setString(4, newUser.getLastName());
			pstmt.setString(5, newUser.getEmail());
			
			int rowsInserted = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rowsInserted != 0) {
				
				while(rs.next()) {
					newUser.setId(rs.getInt(1));
					newUser.setRole(newRole);
				}
				
				conn.commit();
				
			}
					
		} catch (SQLIntegrityConstraintViolationException sicve) {
//			log.error(sicve.getMessage());
	//		log.warn("Username already taken.");
		} catch (SQLException e) {
//			log.error(e.getMessage());
		}
		
		if(newUser.getId() == 0) return null;
		
		return newUser;
	}
	private List<User> mapResultSet(ResultSet rs) throws SQLException {
		
		List<User> users = new ArrayList<>();
		
		while(rs.next()) {
			User user = new User();
			user.setId(rs.getInt("ers_users_id"));
			user.setUsername(rs.getString("ers_username"));
			user.setPassword(rs.getString("ers_password"));
			user.setFirstName(rs.getString("user_first_name"));
			user.setLastName(rs.getString("user_last_name"));
			user.setEmail(rs.getString("user_email"));
			user.setRole(new Role(rs.getInt("user_role_id")));
			users.add(user);
		}
		
		return users;
	}

	@Override
	public User update(User updatedObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}



