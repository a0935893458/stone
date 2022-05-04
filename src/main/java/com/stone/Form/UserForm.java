package com.stone.Form;

import com.stone.domain.Cart;
import com.stone.domain.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Pattern;

public class UserForm {

    public static final String PHONE_REG = "^09[0-9]{8}$";

    @NotBlank
    private String username;
    @Length(min = 6, message = "密碼至少6位")
    private String password;
    @NotBlank
    private String name;
    @Pattern(regexp = PHONE_REG, message = "請輸入正確的手機號碼")
    private String phone;
    @Email
    private String email;
    @NotBlank
    private String send;
    @NotBlank
    private String confirmPassword;



    public UserForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public boolean confirmPassword(){
        if(this.password.equals(this.confirmPassword)){
            return true;
        }
        return false;
    }

    public User convertToUser(){
        User user = new UserFormConvert().convert(this);
        return user;
    }

    public class UserFormConvert implements FormConvert<UserForm,User>{

        @Override
        public User convert(UserForm userForm){
            User user = new User();
            BeanUtils.copyProperties(userForm,user);
            return user;
        }
    }
}
