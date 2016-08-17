package com.unicomWeb.controller;

import com.unicomWeb.model.ResponseMeta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springjdbc.pojo.Operator;
import springjdbc.service.LoginService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by heyuhua on 2016/8/9.
 */
@Controller
@RequestMapping("/controller")
public class LoginController {

    @Resource(name = "loginService")
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ResponseMeta Login(HttpServletRequest request, HttpServletResponse response) {
        String optID =  request.getParameter("username");
        String optPW =  request.getParameter("password");
        ResponseMeta responseMeta = new ResponseMeta();
        Operator operator =  loginService.quaryOPT(optID);

        if (operator != null){
            if (operator.getOptPWD().equals(optPW)) {
                if (request.getParameter("remember").equals("true")) {
                    Cookie cookie = new Cookie("optID", optID);
                    cookie.setMaxAge(7 * 24 * 3600);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                responseMeta.setStatusCode(ResponseMeta.StatusCode.OK);
                responseMeta.setMessage("OK");
                Map<String, String> jumpUrl = new HashMap<>();
                jumpUrl.put("url", "/index.html");
                responseMeta.setData(jumpUrl);
            }else {
                responseMeta.setStatusCode(ResponseMeta.StatusCode.ERROR);
                responseMeta.setMessage("密码或用户名错误");
            }
        }
        return responseMeta;
    }

}
