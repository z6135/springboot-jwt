package com.zmz.springbootjjwt.api;

import com.alibaba.fastjson.JSONObject;
import com.zmz.springbootjjwt.annotation.UserLoginToken;
import com.zmz.springbootjjwt.entity.User;
import com.zmz.springbootjjwt.service.TokenService;
import com.zmz.springbootjjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangshengrui
 * @date 2022-01-06 20:45
 */
@Controller
@RequestMapping("api")
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @GetMapping("/loginto")
    public String loginto(String username,String password ,ModelMap map){
        map.addAttribute("name","ceshi");
        System.out.println(username+" "+password);
        return "login";
    }
    //登录
    @ResponseBody
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        System.out.println(user);
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findByUsername(user);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @ResponseBody
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
