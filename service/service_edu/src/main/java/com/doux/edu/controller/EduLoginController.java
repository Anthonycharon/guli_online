package com.doux.edu.controller;

import com.doux.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangTao
 * @create 2023-02-16-21:16
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/user")
public class EduLoginController {
    @PostMapping("/login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("/info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://www.baidu.com/img/bdlogo.png");
    }

}
