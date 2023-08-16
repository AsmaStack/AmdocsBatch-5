package cms.amdocs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperation {
	private Connection conn;

	public UserOperation() throws SQLException {
		this.conn = DatabaseManager.getConnection();
	}

	public void addUser(User user) throws SQLException {
		String query = "INSERT INTO cms_users (user_id, username, user_password, user_role) VALUES (user_id_seq.NEXTVAL, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.setString(3, user.getRole());
		statement.executeUpdate();

	}

	public boolean isUsernameTaken(String username) throws SQLException {
		String query = "SELECT COUNT(*) FROM cms_users WHERE username = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, username);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			int count = result.getInt(1);
			return count > 0;
		}
		return false;
	}

	public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
		String query = "SELECT * FROM cms_users WHERE username = ? AND user_password = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			User user = new User();
			user.setUserID(result.getInt("user_id"));
			user.setUsername(result.getString("username"));
			user.setPassword(result.getString("user_password"));
			user.setRole(result.getString("user_role"));
			return user;
		}

		return null;
	}
}
