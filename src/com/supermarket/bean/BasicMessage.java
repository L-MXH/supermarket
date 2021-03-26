package com.supermarket.bean;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 02:11
 * @Description: 人员管理添加
 */
public class BasicMessage {
	private int id;
	// 姓名
	private String name;
	// 年龄
	private int age;
	// 部门
	private int dept;
	// 职务
	private int headship;
	// 性别
	private String sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getDept() {
		return dept;
	}

	public void setDept(int dept) {
		this.dept = dept;
	}

	public int getHeadship() {
		return headship;
	}

	public void setHeadship(int headship) {
		this.headship = headship;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
