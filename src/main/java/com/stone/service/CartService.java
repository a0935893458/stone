package com.stone.service;

import com.stone.domain.Cart;
import com.stone.domain.Sell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {


    Cart findCart(Long id);

    List<Cart> findCartByUid(Long uid);
    List<Cart> listAll();


    void deleteCart(Sell sell,Long id,Long uid);


    Page<Cart> findAllByPage(Pageable pageable);

    Cart saveCart(Cart cart);

    Cart includeCart(Cart cart, Long sid, Long uid, Sell sell);




}
