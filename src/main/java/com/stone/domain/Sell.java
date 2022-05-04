package com.stone.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sell {

    @GeneratedValue
    @Id
    private Long id;
    private Long stoneId;
    private String name;
    private String origin;
    private String introduce;
    private String size;
    private Integer price;
    private Long uid;
    private Integer sellStatus;
    private Long sellGroup; //第幾批
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sellTime;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private Integer status;
    private String username;
    private String phone;
    private String send;

    @OneToOne(mappedBy = "sell",fetch = FetchType.EAGER)
    private Stone stone;



    public Sell() {
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Stone getStone() {
        return stone;
    }

    public void setStone(Stone stone) {
        this.stone = stone;
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

    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    public Long getSellGroup() {
        return sellGroup;
    }

    public void setSellGroup(Long sellGroup) {
        this.sellGroup = sellGroup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }
}