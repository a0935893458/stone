package com.stone.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Stone {

    @Id
    @GeneratedValue
    private Long id;
    private Long stoneId;
    private String name; //礦種
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buyDate; //買的時間
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date comeDate; //來的時間
    private String source;//來源
    private String origin; //來源
    private Integer chPrice; //人民幣
    private Integer ntdPrice; //台幣
    /**
     * 0 = 採購已付
     * 1 = 採購未付
     * 2 = 已到貨
     * 3 = 貼文上架
     * 4 = 已售出
     * 5 = 扭蛋
     * 6 = 抽獎
     * 7 = 售友人
     * 8 = 自留
     * 9 = 付款中
     */
    private Integer status;  //狀態

    @OneToOne(cascade = {CascadeType.ALL},optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "stone_sell_id")
    private Sell sell;



    public Stone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoneId() {
        return stoneId;
    }

    public void setStoneId(Long stoneId) {
        this.stoneId = stoneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getComeDate() {
        return comeDate;
    }

    public void setComeDate(Date comeDate) {
        this.comeDate = comeDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getChPrice() {
        return chPrice;
    }

    public void setChPrice(Integer chPrice) {
        this.chPrice = chPrice;
    }

    public Integer getNtdPrice() {
        return ntdPrice;
    }

    public void setNtdPrice(Integer ntdPrice) {
        this.ntdPrice = ntdPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
