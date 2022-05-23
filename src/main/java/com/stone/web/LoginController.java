package com.stone.web;


import com.stone.Form.UserForm;
import com.stone.domain.User;
import com.stone.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

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

    /**
     * 登入成功，將user存進session
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String loginPost(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session){
        User user = userService.findByUsernameAndPassword(username,password);
        if(user != null){
            session.setAttribute("user",user);
            return "index";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }


    /**
     * 註冊會員，確認密碼兩者是否相同，驗證有誤導向註冊頁，通過後儲存user及導向登入頁面
     * @param userForm
     * @param result
     * @return
     */
    @PostMapping("/register")
    public String register(@Valid UserForm userForm, BindingResult result){

        if(!userForm.confirmPassword()){
            result.rejectValue("confirmPassword","confirmError","密碼兩個不同");
        }
        if(result.hasErrors()){
            return  "register";
        }
        User user = userForm.convertToUser();
        userService.saveUser(user);
        return "redirect:/login";
    }

    /**
     * 更改user寄件資料及將userId重新導向檢查購物車
     * @param user
     * @param redirectAttrs
     * @return
     */
    @PostMapping("/changeUserSend")
    public String checkCart(User user, RedirectAttributes redirectAttrs){
        User user1 = userService.findOne(user.getId());
        userService.checkCartUser(user,user1);
        redirectAttrs.addAttribute("id",user.getId());

        return "redirect:show/{id}/checkCart";
    }
}
