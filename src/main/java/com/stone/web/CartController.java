package com.stone.web;

import com.stone.domain.*;
import com.stone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private SellServiceImpl sellService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SoldServiceImpl soldService;

    /**
     * 查詢購物車，沒東西時提醒
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/show/{id}/myCart")
    public String findCart(@PathVariable long id,Model model,final RedirectAttributes attributes){
        List<Cart> carts =cartService.findCartByUid(id);
        User user = userService.findOne(id);

        if(carts.size() == 0){
            attributes.addFlashAttribute("message","購物車沒有東西喔！");
            return "redirect:/show";
        }else {
            model.addAttribute("carts",carts);
            model.addAttribute("user",user);
            return "myCart";

        }
    }


    /**
     * 加進購物車,用userPrice儲存購物車的價格
     * @param sid
     * @param uid
     * @return
     */
    @GetMapping("/show/{sid}/{uid}/cart")
    public String includeCart(@PathVariable long sid, @PathVariable long uid, final RedirectAttributes attributes,
                              Model model) {
        if(cartService.findCartBySidAndUid(sid,uid) != null){
            attributes.addFlashAttribute("message","<"+(sellService.findOne(sid).getName()+"> 已在購物車喽！"));
            return "redirect:/show";
        }else {
            Cart cart = new Cart();
            Sell sell = sellService.findOne(sid);
            cartService.includeCart(cart, sid, uid, sell);
            cartService.saveCart(cart);
            User user = userService.findOne(cart.getUid());
            model.addAttribute("user",user);
            return "redirect:/show";
        }
    }


    /**
     * 移除購物車,將userPrice減掉移除的價格
     * @param id
     * @param uid
     * @return
     */
    @GetMapping("/show/{id}/{uid}/delete")
    public String unIncludeCart(@PathVariable long id, @PathVariable long uid,Model model){
        Cart cart = cartService.findCart(id);
        Sell sell = sellService.findOne(cart.getSid());
        User user = userService.findOne(cart.getUid());
        cartService.deleteCart(sell,id,uid);
        model.addAttribute("user",user);

        return "redirect:/show/{uid}/myCart";
    }

    /**
     * 確認購物車及寄件位置
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/show/{id}/checkCart")
    public String checkCart(@PathVariable long id, Model model){

        User user=userService.findOne(id);
        List<Cart> carts =cartService.findCartByUid(id);
        model.addAttribute("carts",carts);
        model.addAttribute("user",user);


        return "checkCart";

    }

    /**
     * 將購物車裡的物件，逐一存進uid,訂單批數
     * @param uid
     * @param model
     * @return
     */
    @GetMapping("/finishCart/{uid}")
    public String finishCart(@PathVariable long uid,Model model){
            User user = userService.findOne(uid);
            Sold sold1 = soldService.saveSoldPrice(user);
            List<Cart> carts = cartService.findCartByUid(uid);
            carts.forEach(Cart -> {
                Sell sell = sellService.findOne(Cart.getSid());
                sellService.saveUidToSell(sell, sold1.getSellGroup(),user);
                cartService.deleteCartById(Cart.getId());
            });

            List<Sell> sells = sellService.findSellByUid(uid);
            List<Sold> sold = soldService.findAllByUid(uid);
            model.addAttribute("sold", sold);
            model.addAttribute("sells", sells);


        return "finishCart";
    }




}
