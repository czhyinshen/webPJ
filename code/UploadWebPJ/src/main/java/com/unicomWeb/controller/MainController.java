package com.unicomWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by York on 2016/7/29.
 */
//采用注解的方式，可以明确地定义该类为处理请求的Controller类
@Controller
public class MainController {
   @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){

        return "index";//请求后返回页面
    }
}


