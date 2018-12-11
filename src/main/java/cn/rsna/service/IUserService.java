package cn.rsna.service;


import cn.rsna.entity.User;
import cn.rsna.utils.UserMessage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public interface IUserService {
    public boolean isLogin(String username,String password);
    public boolean isVcodeTure(HttpSession session,String vcode);
    public User selectUserByName(String username);
    public String isLogined(Cookie [] cookies) throws Exception;
    public boolean update(UserMessage userMessage,String username);
}
