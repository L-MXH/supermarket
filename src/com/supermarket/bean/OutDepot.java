package com.supermarket.bean;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 01:21
 * @Description: 仓库出库
 */
public class OutDepot {
	private int id;
	// 仓库编码
	private int did;
	// 货品名称
	private String wName;
	// 出库时间
	private String outDate;
	// 重量
	private float wight;
	// 备注
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getwName() {
		return wName;
	}

	public void setwName(String wName) {
		this.wName = wName;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public float getWight() {
		return wight;
	}

	public void setWight(float wight) {
		this.wight = wight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
