package com.meiqi.edu.controller;

import com.meiqi.utils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Monica
 * @Date 2022/7/29 10:56
 **/
@Api("登录")
@CrossOrigin
@RestController
@RequestMapping("admin/user")
public class EduLoginController {

    @PostMapping("/login")
    public R loginUser(){
        return R.ok().data("token","admin");
    }
    @GetMapping("/info")
    public R infoUser(){
        return R.ok().data("roles","admin").data("name","admin").data("avatar","");
    }
}
