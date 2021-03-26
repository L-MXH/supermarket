package com.supermarket.dao;

import java.sql.*;

import com.supermarket.bean.User;

public class UserDao {

	GetConnection connection = new GetConnection();
	Connection conn = null;

	public User getUser(String userName, String passWord) {
		User user = new User();
		conn = connection.getCon();

		try {
			String sql = "select * from tb_users where userName = ? and passWord = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			statement.setString(2, passWord);
			ResultSet rest = statement.executeQuery();
			while (rest.next()) {
				user.setId(rest.getInt(1));
				user.setUserName(rest.getString(2));
				user.setUserName(rest.getString(3));
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return user;

	}
}
