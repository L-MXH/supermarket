package com.supermarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.bean.BasicMessage;
import com.supermarket.bean.Contact;
import com.supermarket.bean.Dept;
import com.supermarket.bean.Headship;
import com.supermarket.bean.Sell;

public class PersonnelDao {
	GetConnection connection = new GetConnection();
	Connection conn = null;

	public void insertBasicMessage(BasicMessage message) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_basicMessage values(?,?,?,?,?)");
			statement.setString(1, message.getName());
			statement.setInt(2, message.getAge());
			statement.setString(3, message.getSex());
			statement.setInt(4, message.getDept());
			statement.setInt(5, message.getHeadship());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertContact(Contact contact) {
		conn = connection.getCon();
		try {
			PreparedStatement statement = conn
					.prepareStatement("insert into tb_contact values(?,?,?,?,?,?)");
			statement.setInt(1, contact.getHid());
			statement.setString(2, contact.getContact());
			statement.setString(3, contact.getOfficePhone());
			statement.setString(4, contact.getFax());
			statement.setString(5, contact.getEmail());
			statement.setString(6, contact.getFaddress());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List selectHeadship() {
		List list = new ArrayList<Dept>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement
					.executeQuery("select * from tb_headship");
			while (rest.next()) {
				Headship headship = new Headship();
				headship.setId(rest.getInt(1));
				headship.setHeadshipName(rest.getString(2));
				list.add(headship);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public int selectIdByHeadship(String hname) {
		int id = 0;
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select id from tb_headship where headshipName = '"
					+ hname + "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				id = rest.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

	}

	public int selectBasicMessageByName(String name) {
		conn = connection.getCon();
		int id = 0;
		try {
			Statement statement = conn.createStatement();
			String sql = "select id from tb_basicMessage where name = '" + name
					+ "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				id = rest.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;

	}

	public List selectBasicMessageByDept(int dept) {
		List list = new ArrayList<String>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select name from tb_basicMessage where dept = "
					+ dept + "";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				list.add(rest.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

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

	public String selectHeadshipById(int id) {
		String hName = "";
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select headshipName from tb_headship where id = '"
					+ id + "'";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				hName = rest.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hName;

	}

	public BasicMessage selectBNameById(String dept, String name) {
		BasicMessage message = new BasicMessage();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_basicMessage where name = '" + name
					+ "' and dept = (select id from tb_dept "
					+ "where dName = '" + dept + "')";
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				message.setId(rest.getInt(1));
				message.setName(rest.getString(2));
				message.setAge(rest.getInt(3));
				message.setSex(rest.getString(4));
				message.setDept(rest.getInt(5));
				message.setHeadship(rest.getInt(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;

	}

	public Contact selectContactById(int hid) {
		Contact contact = new Contact();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_contact where hid = " + hid + "";
			System.out.println(sql);
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				contact.setId(rest.getInt(1));
				contact.setHid(rest.getInt(2));
				contact.setContact(rest.getString(3));
				contact.setOfficePhone(rest.getString(4));
				contact.setFax(rest.getString(5));
				contact.setEmail(rest.getString(6));
				contact.setFaddress(rest.getString(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contact;

	}

	public BasicMessage selectMessagById(int hid) {
		BasicMessage message = new BasicMessage();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			String sql = "select * from tb_basicMessage where id = " + hid + "";
			System.out.println(sql);
			ResultSet rest = statement.executeQuery(sql);
			while (rest.next()) {
				message.setId(rest.getInt(1));
				message.setName(rest.getString(2));
				message.setAge(rest.getInt(3));
				message.setSex(rest.getString(4));
				message.setDept(rest.getInt(5));
				message.setHeadship(rest.getInt(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;

	}

	public void updatertMessage(BasicMessage message) {
		conn = connection.getCon();
		try {
			String sql = "update tb_basicMessage set name = ?, age = ?,"
					+ " sex = ?, dept = ? ,headship = ? where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, message.getName());
			statement.setInt(2, message.getAge());
			statement.setString(3, message.getSex());
			statement.setInt(4, message.getDept());
			statement.setInt(5, message.getHeadship());
			statement.setInt(6, message.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatertContact(Contact contact) {
		conn = connection.getCon();
		try {
			String sql = "update tb_contact set contact = ?, officePhone = ?,"
					+ " fex = ?, email = ? ,faddress = ? where id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, contact.getContact());
			statement.setString(2, contact.getOfficePhone());
			statement.setString(3, contact.getFax());
			statement.setString(4, contact.getEmail());
			statement.setString(5, contact.getFaddress());
			statement.setInt(6, contact.getHid());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBasicMessage(int id) {
		conn = connection.getCon();
		String sql = "delete from tb_basicMessage where id =" + id;
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
