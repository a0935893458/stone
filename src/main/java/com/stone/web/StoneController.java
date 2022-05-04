package com.stone.web;

import com.stone.domain.Sell;
import com.stone.domain.SellRepository;
import com.stone.domain.Stone;
import com.stone.service.SellServiceImpl;
import com.stone.service.StoneServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class StoneController {

    private final Logger logger = LoggerFactory.getLogger(StoneController.class);

    @Autowired
    private StoneServiceImpl stoneService;

    @Autowired
    private SellServiceImpl sellService;

    /**
     * 查詢所有礦礦
     * @param model
     * @return
     */
    @GetMapping("/stones")
    public String list(@PageableDefault(sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable,
                               Model model){
        Page<Stone> page1 = stoneService.findAllByPage(pageable);
        model.addAttribute("page",page1);

        return "stones";
    }


    /**
     * 查詢單一礦礦
     * @param id
     * @param model
     * @return
     */

    @GetMapping("/stones/{id}")
    public String detail(@PathVariable long id,Model model){
        Stone stone = stoneService.findOne(id);
        if(stone == null){
            stone = new Stone();
        }
        model.addAttribute("stone",stone);
        return "stone";
    }

    /**
     * 新增礦礦
     * @param model
     * @return
     */
    @GetMapping("/stones/input")
    public String inputPage(Model model){
        model.addAttribute("stone",new Stone());
        return "input";
    }



    /**
     * 讀取礦礦訊息在編輯頁
     * @param id
     * @param model
     * @return
     */
//    @GetMapping("/stones/{id}/stoneEdit")
//    public String editPage(@PathVariable long id, Model model){
//        Stone stone = stoneService.findOne(id);
//        model.addAttribute("stone",stone);
//        return "stoneEdit";
//    }

//    @GetMapping("/stones/{id}/edit")
//    public String stoneeditPage(@PathVariable long id, Model model){
//        Stone stone = stoneService.findOne(id);
//        model.addAttribute("stone",stone);
//        return "redirect:/stones";
//    }


    /**
     * 編輯礦礦
     * @param stone
     * @param attributes
     * @return
     */


    @PostMapping("/stones")
    public String post(Stone stone,final RedirectAttributes attributes){
        stone.setNtdPrice(stone.getChPrice()*6);
        stone.setSell(new Sell());
        Sell sell = stone.getSell();
        sell.setStoneId(stone.getStoneId());
        sell.setOrigin(stone.getOrigin());
        sell.setName(stone.getName());
        sell.setPrice(stone.getChPrice()*6);
        sell.setStatus(stone.getStatus());
        Stone stone1 = stoneService.saveStone(stone);

        if(stone1 != null){
            attributes.addFlashAttribute("message","<"+stone1.getName()+"> 已進表單喽！");
        }
        return "redirect:/stones";
    }

    @PostMapping("/changeStone")
    public String postEdit(Stone stone,final RedirectAttributes attributes){
        stone.setNtdPrice(stone.getChPrice()*6);
        Stone stone1 = stoneService.findOne(stone.getId());
        Sell sell = stone1.getSell();
        sell.setStoneId(stone.getStoneId());
        sell.setOrigin(stone.getOrigin());
        sell.setName(stone.getName());
        sell.setPrice(stone.getChPrice()*6);
        sell.setStatus(stone.getStatus());
        stone.setSell(stone1.getSell());
        stoneService.saveStone(stone);

        if(stone != null){
            attributes.addFlashAttribute("message","<"+stone1.getName()+"> 已更新表單喽！");
        }
        return "redirect:/stones";
    }


}
