package com.supermarket.bean;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 01:11
 * @Description: 采购订单
 */
public class Stock {
    private int id;
    // 客户
    private String sName;
    // 订单号
    private String orderId;
    // 交易日期
    private String consignmentDate;
    // 商品名
    private String baleName;
    // 数量
    private String count;
    // 金额
    private float money;
    // 是否入库
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getConsignmentDate() {
        return consignmentDate;
    }

    public void setConsignmentDate(String consignmentDate) {
        this.consignmentDate = consignmentDate;
    }

    public String getBaleName() {
        return baleName;
    }

    public void setBaleName(String baleName) {
        this.baleName = baleName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

}
