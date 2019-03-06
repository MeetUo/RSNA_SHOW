package cn.rsna.controller;

import cn.rsna.entity.User;
import cn.rsna.entity.UserInfo;
import cn.rsna.entity.UserKey;
import cn.rsna.service.IUserRegisterService;
import cn.rsna.utils.MD5;
import cn.rsna.utils.RSNAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/user")
public class UserRegisterContoller {
    @Autowired
    private IUserRegisterService userRegisterService;
    @RequestMapping(value = "/register.do", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public RSNAResult handleRequest(HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        String UserName=request.getParameter("username");
        String PassWord =request.getParameter("password");
        String UserType=request.getParameter("usertype");
        boolean res = userRegisterService.select(UserName);
        if (res==false) {
            User user = new User();
            user.setUsername(UserName);
            // MD5 加密密码
            PassWord = MD5.string2MD5(PassWord);
            user.setPassword(PassWord);
            user.setUsertype(UserType);
            user.setScore(0);
            int msg = userRegisterService.add(user);
            Cookie cookie = new Cookie("username",UserName );
            cookie.setMaxAge(86400); // 设置24小时有效
            cookie.setPath("/RSNA_SHOW");
            response.addCookie(cookie); // 服务器返回给浏览器cookie以便下次判断
            return RSNAResult.ok(userRegisterService.selectbyName(UserName));
        }
        else return RSNAResult.build(123, "user has exit");
    }
    }
