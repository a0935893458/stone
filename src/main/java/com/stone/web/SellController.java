package com.stone.web;

import com.stone.domain.Sell;
import com.stone.service.SellServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class SellController {

    @Autowired
    private SellServiceImpl sellservice;

    /**
     * 後臺上架區(分頁)
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/stones/listSell")
    public String listSell(@PageableDefault(sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable,
                           Model model){
        Page<Sell> page1 = sellservice.findAllByPage(pageable);
        model.addAttribute("page",page1);

        return "listSell";
    }

    /**
     * 賣家管理出貨區(分頁)
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/stones/listSold")
    public String listSold(@PageableDefault(sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable,
                           Model model){
        Page<Sell> page1 = sellservice.findSellBySellStatusNotNull(pageable);
        model.addAttribute("page",page1);
        model.addAttribute("pagestatus",0);

        return "listSold";
    }

    /**
     * 賣家寄件區(分頁)
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/stones/listSold/Send")
    public String listSoldSend(@PageableDefault(sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable,
                           Model model){

        Page<Sell> page1 = sellservice.findSellBySellStatusEquals(1,pageable);
        model.addAttribute("page",page1);
        model.addAttribute("pagestatus",1);

        return "listSold";
    }

    /**
     * 礦友挑選區,只挑選狀態為上架中的礦石(3)
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/show")
    public String listShow(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable,
                           Model model, HttpSession session){

        Page<Sell> page1 = sellservice.findSellByStatus(3,pageable);
        model.addAttribute("page",page1);

        return "show";
    }

    /**
     * 商品詳情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/listSell/{id}")
    public String sellStoneDetail(@PathVariable long id,Model model){
        Sell sell = sellservice.findOne(id);

        model.addAttribute("sell",sell);

        return "stone";
    }

    /**
     * 編輯上架的礦礦
     */
    @GetMapping("/stones/listSell/{id}/editSellStone")
    public String editSellStone(@PathVariable long id, Model model){
        Sell sell = sellservice.findOne(id);

        model.addAttribute("sell",sell);

        return "editSellStone";
    }


    /**
     * 儲存上架的照片,介紹,大小
     * @param sell
     * @param file
     * @return
     */
    @PostMapping("/changeSell")
    public String post(@RequestParam("file") MultipartFile file, Sell sell){
        sellservice.saveSell(sell,file);

        return "redirect:/stones/listSell";
    }

    /**
     * 更改買家觀看狀態及進銷存的狀態
     * @param sell
     * @return
     */
    @PostMapping("/changeSellStatus")
    public String postSold( Sell sell){
        Sell sell1 = sellservice.findOne(sell.getId());
        sellservice.saveSellStatus(sell,sell1);

        return "redirect:/stones/listSold";
    }

}
