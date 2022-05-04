package com.stone.web;

import com.stone.domain.*;
import com.stone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private SellServiceImpl sellService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private SoldServiceImpl soldService;

    @Autowired
    private SoldRepository soldRepository;

    @GetMapping("/show/{id}/myCart")
    public String findCart(@PathVariable long id,Model model){
        List<Cart> carts =cartRepository.findByUid(id);
        User user = userService.findOne(id);

        model.addAttribute("carts",carts);
        model.addAttribute("user",user);

        return "myCart";
    }

//    @GetMapping("/show/myCart")
//    public String viewCart(Model model){
//        List<Cart> listCarts = cartService.listAll();
//        model.addAttribute("listCarts",listCarts);
//
//        return "myCart";
//    }

    /**
     * 加進購物車,用userPrice儲存購物車的價格
     * @param sid
     * @param uid
     * @return
     */
    @GetMapping("/show/{sid}/{uid}/cart")
    public String includeCart(@PathVariable long sid, @PathVariable long uid, final RedirectAttributes attributes,
                              Model model) {
        if(cartRepository.findCartBySidAndUid(sid,uid) != null){
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

    @GetMapping("/show/{id}/checkCart")
    public String checkCart(@PathVariable long id, Model model){
        User user=userService.findOne(id);
        List<Cart> carts =cartRepository.findByUid(id);
        model.addAttribute("carts",carts);
        model.addAttribute("user",user);


        return "checkCart";

    }

    @GetMapping("/finishCart/{uid}")
    public String finishCart(@PathVariable long uid,Model model){
        if(cartService.findCartByUid(uid) != null) {
            User user = userService.findOne(uid);
            Sold sold = soldService.saveSoldPrice(user);
            List<Cart> carts = cartRepository.findByUid(uid);
            carts.forEach(Cart -> {
                Sell sell = sellService.findOne(Cart.getSid());
                sellService.saveUidToSell(sell, uid, sold.getSellGroup(),user);
                cartRepository.deleteById(Cart.getId());
            });

        }
//        if(sellRepository.findSellByUid(uid) != null) {

            List<Sell> sells = sellRepository.findSellByUid(uid);
            List<Sold> sold = soldRepository.findAllByUid(uid);
            model.addAttribute("solds", sold);
            model.addAttribute("sells", sells);

//        }else{
//            return "/show";
//        }


        return "finishCart";
    }




}
