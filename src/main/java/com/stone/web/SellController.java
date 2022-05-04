package com.stone.web;

import com.stone.domain.Sell;
import com.stone.domain.SellRepository;
import com.stone.domain.Stone;
import com.stone.service.SellServiceImpl;
import com.stone.service.StoneService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SellController {

    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private StoneService stoneService;

    @Autowired
    private SellServiceImpl sellservice;

    /**
     * 後臺編輯區
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/listSell")
    public String listSell(@PageableDefault(sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable,
                           Model model){
        Page<Sell> page1 = sellservice.findAllByPage(pageable);
        model.addAttribute("page",page1);

        return "listSell";
    }

    @GetMapping("/listSold")
    public String listSold(@PageableDefault(sort = {"id"},direction = Sort.Direction.ASC) Pageable pageable,
                           Model model){
        Page<Sell> page1 = sellservice.findAllByPage(pageable);
        model.addAttribute("page",page1);

        return "listSold";
    }

    /**
     * 礦友挑選區
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/show")
    public String listShow(@PageableDefault(sort = {"id"},
            direction = Sort.Direction.ASC) Pageable pageable,
                           Model model, HttpSession session){

        Page<Sell> page1 = sellRepository.findSellByStatus(3,pageable);
        model.addAttribute("page",page1);

        return "show";
    }

    /**
     * 新增sell上架的礦礦
     */
    @GetMapping("/listSell/{id}/editSellStone")
    public String editSellStone(@PathVariable long id, Model model){
        Sell sell = sellservice.findOne(id);

        model.addAttribute("sell",sell);

        return "editSellStone";
    }

    @GetMapping("/listSell/inputSell")
    public String inputPage(Model model){
        model.addAttribute("sell",new Sell());
        return "listSell";
    }

    /**
     * 存取上架的照片,介紹,大小
     * @param sell
     * @param file
     * @return
     */
    @PostMapping("/changeSell")
    public String post(@RequestParam("file") MultipartFile file, Sell sell){
        sellservice.saveSell(sell,file);

        return "redirect:/listSell";
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

        return "redirect:/listSold";
    }

}
