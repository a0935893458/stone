package com.stone.web;

import com.stone.domain.Stone;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class StoneController {

    private final Logger logger = LoggerFactory.getLogger(StoneController.class);

    @Autowired
    private StoneServiceImpl stoneService;

    /**
     * 查詢所有礦礦(分頁)
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
     * 儲存新增礦礦
     * @param stone
     * @param attributes
     * @return
     */

    @PostMapping("/stones")
    public String post(Stone stone,final RedirectAttributes attributes){

        Stone stone1 = stoneService.saveStone(stone);

        if(stone1 != null){
            attributes.addFlashAttribute("message","<"+stone1.getName()+"> 已加進表單喽！");
        }
        return "redirect:/stones";
    }

    /**
     * 儲存編輯礦礦
     * @param stone
     * @param attributes
     * @return
     */
    @PostMapping("/changeStone")
    public String postEdit(Stone stone,final RedirectAttributes attributes){
        Stone stone1 = stoneService.findOne(stone.getId());
        stoneService.postStone(stone,stone1);

        if(stone != null){
            attributes.addFlashAttribute("message","<"+stone1.getName()+"> 已更新表單喽！");
        }
        return "redirect:/stones";
    }


}
