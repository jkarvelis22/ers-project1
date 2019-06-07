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
import com.revature.utils.Josh4J;

public class UserDAO implements DAO<User> {
	static Josh4J j = Josh4J.getInstance();
	
//	private static Logger log = Logger.getLogger(UserDAO.class);
	
	public User getByUsername(String username) {
		j.info("UserDAO.getByUsername(" + username + ")");
		
	User user = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			j.info("UserDAO(" + username + ") : Variable == False : try catch block entered.");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ers_users JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id WHERE ers_username = ?");
			pstmt.setString(1, username);
			System.out.println("SQL STATEMENT");
			
			List<User> users = this.mapResultSet(pstmt.executeQuery());
			if (!users.isEmpty()) user = users.get(0);
			j.info("UserDAO.getByUsername(" + username + ") : Variable == False : if block entered.");
			System.out.println(user);
			
		} catch (SQLException e) {
			System.out.println("CAUGHT THE EXCEPTION");
//			log.error(e.getMessage());
		}
		j.info("UserDAO.update(" + username + ") : VALUE was returned");
		return user;
	}
	
	public User getByCredentials(String username, String password) {
		j.info("UserDAO.getByCredentials(" + username + "," + password + ")");
		
		User user = null;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			j.info("UserDAO.getByCredentials(" + username + "," + password + ") : Variable == False : Try catch block entered.");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ers_users JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id WHERE ers_username = ? AND ers_password = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			List<User> users = this.mapResultSet(pstmt.executeQuery());
			if (!users.isEmpty()) user = users.get(0);
			j.info("UserDAO.getByCredentials(" + username + "," + password + ") : Variable == False : if block entered.");
			
		} catch (SQLException e) {
//			log.error(e.getMessage());
		}
		j.info("UserDAO.getByCredentials(" + username + password + ") : VALUE was returned");	
		return user;
	}
	
	@Override
	public List<User> getAll() {
		
		j.info("UserDAO.getAll()");
		
		List<User> users = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			j.info("UserDAO.getAll() : Variable == False : try catch block entered.");
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM ers_users JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id");
			users = this.mapResultSet(rs);
			
			users.forEach(u -> u.setPassword("*********"));
			
		} catch (SQLException e) {
//			log.error(e.getMessage());
		}
		j.info("UserDAO.getAll() : VALUE was returned");
		return users;
	}
	
	@Override
	public User getById(int userId) {
		j.info("UserDAO.getById(" + userId + ")");
		
		User user = null;;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			j.info("UserDAO.getById(" + userId + ") : Variable == False : try catch block entered.");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ers_users JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id WHERE ers_users_id = ?");
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			List<User> users = this.mapResultSet(rs);
			
			if (!users.isEmpty()) {
				j.info("UserDAO.getById(" + userId + ") : Variable == False : if block entered.");
				user = users.get(0);
				user.setPassword("*********");
			}
			
		} catch (SQLException e) {
//			log.error(e.getMessage());
		}
		j.info("UserDAO.getById(" + userId + ") : VALUE was returned");
		return user;
	}
	
	@Override
	public User add(User newUser) {
		j.info("UserDAO.add(" + newUser + ")");
		
		Role newRole = new Role();
		newRole.setRoleId(2);
		newRole.setRoleName("employee");
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			j.info("UserDAO.add(" + newUser + ") : Variable == False : try catch block entered.");
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
				j.info("UserDAO.add(" + newUser + ") : Variable == False : Else block entered.");
				
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
		j.info("UserDAO.add(" + newUser + ") : Variable == False : if block entered.");
		
		j.info("UserDAO.add(" + newUser + ") : VALUE was returned");
		return newUser;
	}
	private List<User> mapResultSet(ResultSet rs) throws SQLException {
		j.info("UserDAO.mapResultSet(" + rs + ")");
		
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
		j.info("UserDAO.mapResultSet(" + rs + ") : VALUE was returned");
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



