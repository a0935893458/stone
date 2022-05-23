package com.stone.service;

import com.stone.domain.Sold;
import com.stone.domain.User;

import java.util.List;

public interface SoldService {
    Sold saveSoldPrice(User user);
    List<Sold> findAllByUid(long uid);
}
