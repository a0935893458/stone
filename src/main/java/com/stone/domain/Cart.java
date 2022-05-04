package com.stone.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @GeneratedValue
    @Id
    private Long id; //購物車id
    private Long uid; //使用者id
    private Long sid; //石頭id
    private int price;
    private String origin;
    private String stoneName; //商品標題
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String stoneImage; //商品圖片









    public Cart() {
    }

    public Cart(Long uid, Long sid) {
        this.uid = uid;
        this.sid = sid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }


    public String getStoneImage() {
        return stoneImage;
    }

    public void setStoneImage(String stoneImage) {
        this.stoneImage = stoneImage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStoneName() {
        return stoneName;
    }

    public void setStoneName(String stoneName) {
        this.stoneName = stoneName;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", uid=" + uid +
                ", sid=" + sid +
                ", price=" + price +
                ", origin='" + origin + '\'' +
                ", stoneName='" + stoneName + '\'' +
                ", stoneImage='" + stoneImage + '\'' +
                '}';
    }
}
