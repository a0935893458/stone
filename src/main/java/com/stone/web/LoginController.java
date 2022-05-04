package com.stone.web;


import com.stone.Form.UserForm;
import com.stone.domain.Cart;
import com.stone.domain.User;
import com.stone.domain.UserRepository;
import com.stone.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("userForm",new UserForm());
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "/login";
    }


    @PostMapping("/login")
    public String loginPost(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session){
        User user = userRepository.findByUsernameAndPassword(username,password);
        if(user != null){
            session.setAttribute("user",user);
            session.setAttribute("id",user.getId());
            return "index";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }



    @PostMapping("/register")
    public String register(@Valid UserForm userForm, BindingResult result, Model model){

        if(!userForm.confirmPassword()){
            result.rejectValue("confirmPassword","confirmError","密碼兩個不同");
        }
        if(result.hasErrors()){
            return  "register";
        }
        User user = userForm.convertToUser();
        userRepository.save(user);
        return "redirect:/login";
    }

    @PostMapping("/changeUserSend")
    public String checkCart(User user, RedirectAttributes redirectAttrs){
        User user1 = userService.findOne(user.getId());
        user1.setName(user.getName());
        user1.setPhone(user.getPhone());
        user1.setSend(user.getSend());
        redirectAttrs.addAttribute("id",user.getId());

        userService.checkCartUser(user1);
        return "redirect:show/{id}/checkCart";
    }
}
