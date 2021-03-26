package com.supermarket.bean;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 01:00
 * @Description: 货品档案
 */
public class Ware {
    // 主键
    private int id;
    // 货品名称
    private String wareName;
    // 货品描述
    private String warBewrite;
    // 单位
    private String spec;
    // 进货价
    private float stockPrice;
    // 零售价
    private float retailPrice;
    // 会员价
    private float associatorPrice;

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public String getWarBewrite() {
        return warBewrite;
    }

    public void setWarBewrite(String warBewrite) {
        this.warBewrite = warBewrite;
    }

    public float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public float getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(float retailPrice) {
        this.retailPrice = retailPrice;
    }

    public float getAssociatorPrice() {
        return associatorPrice;
    }

    public void setAssociatorPrice(float associatorPrice) {
        this.associatorPrice = associatorPrice;
    }

}