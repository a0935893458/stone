package com.stone.web;

import com.stone.domain.Evaluation;
import com.stone.domain.EvaluationRepository;
import com.stone.domain.Stone;
import com.stone.service.EvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Controller
public class EvaluationController {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EvaluationServiceImpl evaluationService;

    @GetMapping("/listEvaluation")
    public String listEvaluation(@PageableDefault(sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        Page<Evaluation> page1 = evaluationService.findAllByPage(pageable);
        model.addAttribute("page",page1);

        return "listEvaluation";
    }


    @GetMapping("/listEvaluation/inputEva")
    public String showAddProduct(){
        return "/inputEvaluation";
    }

    @PostMapping("/inputEvaluation")
    public String saveEvaluation(@RequestParam("comment") String comment,
                                 @RequestParam("stars") int stars,
                                 @RequestParam("file") MultipartFile file,
                                 @ModelAttribute  Date evaluationDate
                                 ){
        evaluationService.saveEvaluationToDB(file,comment,stars,evaluationDate);
        return "redirect:/listEvaluation";

    }

    @GetMapping("/deleteEvaluation/{id}")
    public String deleteEvaluation(@PathVariable("id") Long id){
    evaluationRepository.deleteById(id);
    return "redirect:/listEvaluation";
    }
}
