package com.stone;

import com.alibaba.fastjson.JSON;
import com.stone.domain.Cart;
import com.stone.service.CartService;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by limi on 2017/9/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTests {

    @Autowired
    private CartService cartService;


//    @Test
//    public void viewCart(){
//        List<Cart> listCarts = cartService.listAll();
//        System.out.println(JSON.toJSONString(listCarts,true));
//    }




    @Test
    public void findTopic() {
        Cart cart = cartService.findCart(2L);
        cart.getSells();
        System.out.println(JSON.toJSONString(cart,true));
    }



}
