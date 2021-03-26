package com.supermarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.bean.Sell;
import com.supermarket.bean.Stock;

public class StockDao {
	GetConnection connection = new GetConnection();
	Connection conn = null;

	public void insertStock(Stock stock) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_stock values(?,?,?,?,?,?)");
			statement.setString(1, stock.getsName());
			statement.setString(2, stock.getOrderId());
			statement.setString(3, stock.getConsignmentDate());
			statement.setString(4, stock.getBaleName());
			statement.setString(5, stock.getCount());
			statement.setFloat(6, stock.getMoney());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List selectStock() {
		List list = new ArrayList<Sell>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_stock");
			while (rest.next()) {
				Stock stock = new Stock();
				stock.setId(rest.getInt(1));
				stock.setsName(rest.getString(2));
				stock.setOrderId(rest.getString(3));
				stock.setConsignmentDate(rest.getString(4));
				stock.setBaleName(rest.getString(5));
				stock.setCount(rest.getString(6));
				stock.setMoney(rest.getFloat(7));

				list.add(stock);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public int selectJoinStockByOid(String oid) {
		List list = new ArrayList<Sell>();
		conn = connection.getCon();
		int id = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_joinDepot where Oid = '" + oid + "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {

				// sell.setId(rest.getInt(1));
				// sell.setSellName(rest.getString(2));
				// sell.setAddress(rest.getString(3));
				// sell.setLinkman(rest.getString(4));
				// sell.setLinkPhone(rest.getString(5));
				// sell.setFaxNum(rest.getString(6));
				// sell.setPostNum(rest.getString(7));
				// sell.setBankNum(rest.getString(8));
				// sell.setNetAddress(rest.getString(9));
				// sell.setEmaillAddress(rest.getString(10));
				// sell.setRemark(rest.getString(11));
				id = rest.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

	}

	public List selectStockBySName(String sName) {
		List list = new ArrayList<Stock>();
		conn = connection.getCon();
		int id = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_stock where sName ='" + sName + "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				Stock stock = new Stock();
				stock.setId(rest.getInt(1));
				stock.setsName(rest.getString(2));
				stock.setOrderId(rest.getString(3));
				stock.setConsignmentDate(rest.getString(4));
				stock.setBaleName(rest.getString(5));
				stock.setCount(rest.getString(6));
				stock.setMoney(rest.getFloat(7));
				list.add(stock);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public List selectStockByOid(String oId) {
		List list = new ArrayList<Stock>();
		conn = connection.getCon();
		int id = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_stock where orderId = '" + oId + "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				Stock stock = new Stock();
				stock.setId(rest.getInt(1));
				stock.setsName(rest.getString(2));
				stock.setOrderId(rest.getString(3));
				stock.setConsignmentDate(rest.getString(4));
				stock.setBaleName(rest.getString(5));
				stock.setCount(rest.getString(6));
				stock.setMoney(rest.getFloat(7));
				list.add(stock);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public List selectStockByDate(String cDate) {

		List list = new ArrayList<Stock>();
		conn = connection.getCon();
		int id = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_stock where consignmentDate = '"
					+ cDate + "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {

				Stock stock = new Stock();
				stock.setId(rest.getInt(1));
				stock.setsName(rest.getString(2));
				stock.setOrderId(rest.getString(3));
				stock.setConsignmentDate(rest.getString(4));
				stock.setBaleName(rest.getString(5));
				stock.setCount(rest.getString(6));
				stock.setMoney(rest.getFloat(7));
				list.add(stock);
				// list.add(sell);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public Stock selectStockByid(int id) {
		Stock stock = new Stock();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_stock where id = " + id;
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {

				stock.setId(id);
				stock.setsName(rest.getString(2));
				stock.setOrderId(rest.getString(3));
				stock.setConsignmentDate(rest.getString(4));
				stock.setBaleName(rest.getString(5));
				stock.setCount(rest.getString(6));
				stock.setMoney(rest.getFloat(7));
				// list.add(sell);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;

	}

	public void updateStock(Stock stock) {
		conn = connection.getCon();
		try {
			String sql = "update tb_stock set sName = ?, orderId = ?, consignmentDate = ?,"
					+ "baleName = ?, count = ?, money = ? where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, stock.getsName());
			statement.setString(2, stock.getOrderId());
			statement.setString(3, stock.getConsignmentDate());
			statement.setString(4, stock.getBaleName());
			statement.setString(5, stock.getCount());
			statement.setFloat(6, stock.getMoney());
			statement.setInt(7, stock.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteStock(int id) {
		conn = connection.getCon();
		String sql = "delete from tb_stock where id =" + id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
