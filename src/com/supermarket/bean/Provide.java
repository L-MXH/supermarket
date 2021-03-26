package com.supermarket.bean;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 00:16
 * @Description: 供货商
 */
public class Provide {
    // 主键
    private int id;
    // 客户
    private String cName;
    // 地址
    private String address;
    //
    private String provide;
    // 联系人
    private String linkman;
    // 联系人电话
    private String linkPhone;
    // 传真
    private String faxes;
    // 邮编
    private String postNum;
    // 银行号码
    private String bankNum;
    // 网址
    private String netAddress;
    // Email
    private String emaillAddress;
    // 备注
    private String remark;

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvide() {
        return provide;
    }

    public void setProvide(String provide) {
        this.provide = provide;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getFaxes() {
        return faxes;
    }

    public void setFaxes(String faxes) {
        this.faxes = faxes;
    }

    public String getPostNum() {
        return postNum;
    }

    public void setPostNum(String postNum) {
        this.postNum = postNum;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getNetAddress() {
        return netAddress;
    }

    public void setNetAddress(String netAddress) {
        this.netAddress = netAddress;
    }

    public String getEmaillAddress() {
        return emaillAddress;
    }

    public void setEmaillAddress(String emaillAddress) {
        this.emaillAddress = emaillAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
