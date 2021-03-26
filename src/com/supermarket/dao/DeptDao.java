package com.supermarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.bean.Dept;
import com.supermarket.bean.Sell;

public class DeptDao {
	GetConnection connection = new GetConnection();
	Connection conn = null;

	public void insertDept(Dept dept) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_dept values(?,?,?)");
			statement.setString(1, dept.getdName());
			statement.setString(2, dept.getPrincipal());
			statement.setString(3, dept.getBewrite());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List selectDept() {
		List list = new ArrayList<Dept>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_dept");
			while (rest.next()) {
				Dept dept = new Dept();
				dept.setId(rest.getInt(1));
				dept.setdName(rest.getString(2));
				dept.setPrincipal(rest.getString(3));
				dept.setBewrite(rest.getString(4));
				list.add(dept);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public Dept selectDeptByid(int id) {
		Dept dept = new Dept();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_dept where id =" + id;
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				dept.setId(rest.getInt(1));
				dept.setdName(rest.getString(2));
				dept.setPrincipal(rest.getString(3));
				dept.setBewrite(rest.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;

	}

	public int selectDeptIdByName(String name) {
		int id = 0;
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select id from tb_dept where dName = '" + name + "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {

				id = rest.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

	}

	public Dept selectDeptByName(String name) {
		conn = connection.getCon();
		Dept dept = null;
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_dept where dName = '" + name + "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				dept = new Dept();
				dept.setId(rest.getInt(1));
				dept.setdName(rest.getString(2));
				dept.setPrincipal(rest.getString(3));
				dept.setBewrite(rest.getString(4));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;

	}

	public Dept selectDeptByPrincipal(String manage) {

		conn = connection.getCon();
		Dept dept = null;
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_dept where principal = '" + manage
					+ "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				dept = new Dept();
				dept.setId(rest.getInt(1));
				dept.setdName(rest.getString(2));
				dept.setPrincipal(rest.getString(3));
				dept.setBewrite(rest.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;

	}

	public Dept selectDeptById(int id) {

		conn = connection.getCon();
		Dept dept = new Dept();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_dept where id = '" + id + "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				dept.setId(rest.getInt(1));
				dept.setdName(rest.getString(2));
				dept.setPrincipal(rest.getString(3));
				dept.setBewrite(rest.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;

	}

	public void updateDept(Dept dept) {
		conn = connection.getCon();
		try {
			String sql = "update tb_dept set dName = ?, principal = ?, bewrite = ? where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, dept.getdName());
			statement.setString(2, dept.getPrincipal());
			statement.setString(3, dept.getBewrite());
			statement.setInt(4, dept.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteDept(int id) {
		conn = connection.getCon();
		String sql = "delete from tb_dept where id =" + id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
