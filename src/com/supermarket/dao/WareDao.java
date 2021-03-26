package com.supermarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.bean.Ware;

public class WareDao {
	GetConnection connection = new GetConnection();
	Connection conn = null;

	public void insertWare(Ware ware) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_ware values(?,?,?,?,?,?)");
			statement.setString(1, ware.getWareName());
			statement.setString(2, ware.getWarBewrite());
			statement.setString(3, ware.getSpec());
			statement.setFloat(4, ware.getStockPrice());
			statement.setFloat(5, ware.getRetailPrice());
			statement.setFloat(6, ware.getAssociatorPrice());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List selectWare() {
		List list = new ArrayList<Ware>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_ware");
			while (rest.next()) {
				Ware ware = new Ware();
				ware.setId(rest.getInt(1));
				ware.setWareName(rest.getString(2));
				ware.setWarBewrite(rest.getString(3));
				ware.setSpec(rest.getString(4));
				ware.setStockPrice(rest.getFloat(5));
				ware.setRetailPrice(rest.getFloat(6));
				ware.setAssociatorPrice(rest.getFloat(7));
				list.add(ware);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public Ware selectWareByid(int id) {
		Ware ware = new Ware();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_ware where id =" + id;
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {

				ware.setId(rest.getInt(1));
				ware.setWareName(rest.getString(2));
				ware.setWarBewrite(rest.getString(3));
				ware.setSpec(rest.getString(4));
				ware.setStockPrice(rest.getFloat(5));
				ware.setRetailPrice(rest.getFloat(6));
				ware.setAssociatorPrice(rest.getFloat(7));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ware;

	}

	public List selectWareByName(String name) {

		conn = connection.getCon();
		List list = new ArrayList<Ware>();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_ware where wareName = '" + name
					+ "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				Ware ware = new Ware();
				ware.setId(rest.getInt(1));
				ware.setWareName(rest.getString(2));
				ware.setWarBewrite(rest.getString(3));
				ware.setSpec(rest.getString(4));
				ware.setStockPrice(rest.getFloat(5));
				ware.setRetailPrice(rest.getFloat(6));
				ware.setAssociatorPrice(rest.getFloat(7));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public void updateWare(Ware ware) {
		conn = connection.getCon();
		try {
			String sql = "update tb_ware set wareName = ?, warBewrite = ?,"
					+ "spec = ?, stockPrice = ?, retailPrice = ?, associatorPrice = ?"
					+ " where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, ware.getWareName());
			statement.setString(2, ware.getWarBewrite());
			statement.setString(3, ware.getSpec());
			statement.setFloat(4, ware.getStockPrice());
			statement.setFloat(5, ware.getRetailPrice());
			statement.setFloat(6, ware.getAssociatorPrice());
			statement.setInt(7, ware.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteWare(int id) {
		conn = connection.getCon();
		String sql = "delete from tb_ware where id =" + id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
