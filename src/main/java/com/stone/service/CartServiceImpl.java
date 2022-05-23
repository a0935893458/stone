package com.stone.service;

import com.stone.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart findCart(Long id) {
        return cartRepository.findById(id).get();

    }

    @Override
    public List<Cart> findCartByUid(Long uid) {
        return cartRepository.findByUid(uid);
    }

    @Override
    public List<Cart> listAll() {
        return cartRepository.findAll();
    }

    @Override
    public void deleteCart(Sell sell,Long id,Long uid) {

        User user = userRepository.findById(uid).get();
        user.setTotalPrice(user.getTotalPrice() - sell.getPrice());
        userRepository.save(user);
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteCartById(long id){
        cartRepository.deleteById(id);
    }

    @Override
    public Cart findCartBySidAndUid(Long sid,Long uid){
        return cartRepository.findCartBySidAndUid(sid,uid);
    }

    @Override
    public Page<Cart> findAllByPage(Pageable pageable){
        return cartRepository.findAll(pageable );
    }

    @Override
    public Cart saveCart(Cart cart){
        return  cartRepository.save(cart);
    }



    @Override
    public Cart includeCart(Cart cart,Long sid,Long uid,Sell sell){
        User user = userRepository.findById(uid).get();
        user.setTotalPrice(user.getTotalPrice()+ sell.getPrice());
        cart.setPrice(sell.getPrice());
        cart.setOrigin(sell.getOrigin());
        cart.setStoneName(sell.getName());
        cart.setStoneImage(sell.getImage());
        cart.setSid(sid);
        cart.setUid(uid);
        userRepository.save(user);

        return cart;
    }



}
