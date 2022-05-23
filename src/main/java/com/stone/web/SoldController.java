package com.stone.web;

import com.stone.domain.Sell;
import com.stone.domain.SellRepository;
import com.stone.domain.Sold;
import com.stone.domain.SoldRepository;
import com.stone.service.SellServiceImpl;
import com.stone.service.SoldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SoldController {

    @Autowired
    private SoldServiceImpl soldService;

    @Autowired
    private SellServiceImpl sellService;

    /**
     * 查詢訂單資訊
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/finishCart/{id}/sold")
    public String sold(Model model, @PathVariable long id){

        List<Sell> sells = sellService.findSellByUid(id);
        List<Sold> sold = soldService.findAllByUid(id);
        model.addAttribute("sold", sold);
        model.addAttribute("sells", sells);

        return "finishCart";
    }


}
