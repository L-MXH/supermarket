package com.supermarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.bean.Depot;
import com.supermarket.bean.Ware;

public class DepotDao {
	GetConnection connection = new GetConnection();
	Connection conn = null;

	public void insertDepot(Depot depot) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_depot values(?,?,?)");
			statement.setInt(1, depot.getId());
			statement.setString(2, depot.getManage());
			statement.setString(3, depot.getFunctional());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List selectDepot() {
		List list = new ArrayList<Depot>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_depot");
			while (rest.next()) {
				Depot depot = new Depot();
				depot.setId(rest.getInt(1));
				depot.setManage(rest.getString(2));
				depot.setFunctional(rest.getString(3));
				list.add(depot);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public Depot selectDepotByid(int id) {
		Depot depot = new Depot();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_depot where id =" + id;
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {

				depot.setId(id);
				depot.setManage(rest.getString(2));
				depot.setFunctional(rest.getString(3));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depot;

	}

	public List selectDepotByManage(String manage) {

		conn = connection.getCon();
		List list = new ArrayList<Depot>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_depot where manage = '" + manage
					+ "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				Depot depot = new Depot();
				depot.setId(rest.getInt(1));
				depot.setManage(rest.getString(2));
				depot.setFunctional(rest.getString(3));
				list.add(depot);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public List selectDepotByManageAndId(int id, String manage) {
		conn = connection.getCon();
		List list = new ArrayList<Depot>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_depot where manage + '" + manage
					+ "' and id = " + id;
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				Depot depot = new Depot();
				depot.setId(rest.getInt(1));
				depot.setManage(rest.getString(2));
				depot.setFunctional(rest.getString(3));
				list.add(depot);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;

	}

	public Depot selectDepotById(int id) {
		conn = connection.getCon();
		Depot depot = new Depot();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_depot where id = '" + id + "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {

				depot.setId(rest.getInt(1));
				depot.setManage(rest.getString(2));
				depot.setFunctional(rest.getString(3));

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return depot;
	}

	public void updateDepot(Depot depot) {
		conn = connection.getCon();
		try {
			String sql = "update tb_depot set manage = ?, functional = ? where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, depot.getManage());
			statement.setString(2, depot.getFunctional());
			statement.setInt(3, depot.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteDepot(int id) {
		conn = connection.getCon();
		String sql = "delete from tb_depot where id =" + id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
