package com.supermarket.dao;

import java.sql.*;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 00:16
 * @Description: 数据库连接池
 */
public class GetConnection {

	private Connection con;
	private PreparedStatement pstm;
	/* sqlServer */
	/*private String user = "sa";
	private String password = "123456";
	private String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=db_supermarket";*/

	/* mysql */
	private String user = "root";
	private String password = "root";
	private String className = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/db_supermarket?serverTimezone=Asia/Shanghai";

	public GetConnection() {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库驱动失败！");
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		try {
			con = (Connection) DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			System.out.println("创建数据库驱动失败！");
			con = null;
			e.printStackTrace();
		}
		return con;
	}

	public void doPstm(String sql, Object[] params) {
		if (sql != null && !sql.equals("")) {
			if (params == null)
				params = new Object[0];
			getCon();
			if (con != null) {
				try {
					System.out.println(sql);
					pstm = con.prepareStatement(sql,
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					for (int i = 0; i < params.length; i++) {
						pstm.setObject(i + 1, params[i]);
					}
					pstm.execute();

				} catch (SQLException e) {
					System.out.println("doPstm()方法出错！");
					e.printStackTrace();
				}

			}
		}
	}

	public ResultSet getRs() throws SQLException {
		return pstm.getResultSet();
	}

	public int getCount() throws SQLException {
		return pstm.getUpdateCount();
	}

	public void closed() {
		try {
			if (pstm != null)
				pstm.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("关闭pstm对象失败！");
			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("关闭con对象失败！");
			e.printStackTrace();
		}
	}
}
