package com.stone.web;

import com.stone.domain.Sell;
import com.stone.domain.SellRepository;
import com.stone.domain.Sold;
import com.stone.domain.SoldRepository;
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
    private SellRepository sellRepository;

    @Autowired
    private SoldRepository soldRepository;

    @GetMapping("/finishCart/{id}/sold")
    public String sold(Model model, @PathVariable long id){

        List<Sell> sells = sellRepository.findSellByUid(id);
        List<Sold> sold = soldRepository.findAllByUid(id);
        model.addAttribute("sold", sold);
        model.addAttribute("sells", sells);

        return "finishCart";
    }


}
