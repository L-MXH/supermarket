package com.supermarket.bean;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 01:00
 * @Description: 仓库管理
 */
public class Depot {
	// 主键
	private int id;
	// 库管（部门名称）
	private String manage;
	// 负责人
	private String functional;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getManage() {
		return manage;
	}

	public void setManage(String manage) {
		this.manage = manage;
	}

	public String getFunctional() {
		return functional;
	}

	public void setFunctional(String functional) {
		this.functional = functional;
	}

}
