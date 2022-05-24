package com.stone.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sold {

    @GeneratedValue
    @Id
    private Long id;
    private int price;
    private Long sellGroup;
    private Long uid;
    /**
     * 0 = 付款中
     * 1 = 已付款
     * 2 = 已出貨
     * 3 = 已評論
     */
    private Integer sellStatus;


    public Sold() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getSellGroup() {
        return sellGroup;
    }

    public void setSellGroup(Long sellGroup) {
        this.sellGroup = sellGroup;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }
}
