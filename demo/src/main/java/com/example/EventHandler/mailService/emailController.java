package com.example.EventHandler.mailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class emailController {
    @Autowired
    private emailService emailService;
    static Map<String,Integer> otpStorage=new HashMap<>();
    int otp=0;
    @GetMapping("/sendOtp")
    @Async
    public String sendOtp(@RequestParam String user){
        try {
            otp=(int)(Math.random()*900000)+100000;
            String sotp=otp+"";
            otpStorage.put(user,otp);
            emailService.sendMail(user, "From Event Handlers", sotp);
        }
        catch(Exception e){
            return e.getMessage();
        }
        return "true";
    }
    @GetMapping("/validOtp")
    public boolean validotp(@RequestParam String user,@RequestParam int otpNumber){
        try {
            if(otpStorage.get(user)==otpNumber){
                otpStorage.remove(user);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}