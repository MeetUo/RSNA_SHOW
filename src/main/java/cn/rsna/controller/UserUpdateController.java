package cn.rsna.controller;


import cn.rsna.service.IUserService;
import cn.rsna.utils.LocalRequest;
import cn.rsna.utils.RSNAResult;
import cn.rsna.utils.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/userinfo")
public class UserUpdateController {
    @Autowired
    private IUserService userService;
    @RequestMapping(value = "/update.do", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public RSNAResult userUpdate(@RequestBody UserMessage userMessage, HttpServletRequest request) throws Exception {
       Cookie[] cookies = request.getCookies();
       String username = userService.isLogined(cookies);
       if (username == null) return RSNAResult.build(123,"please login first");
       if (userService.update(userMessage,username)) return RSNAResult.ok();
       else return RSNAResult.build(123,"user update fail");
    }
}
