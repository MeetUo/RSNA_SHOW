package cn.rsna.service.Impl;

import cn.rsna.dao.UserMapper;
import cn.rsna.entity.User;
import cn.rsna.entity.UserExample;
import cn.rsna.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean isLogin(String username,String password){
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> UserList = userMapper.selectByExample(example);
        if (UserList.isEmpty()) return false;
        if (UserList.get(0).getPassword().equals(password)) return true;
        else return false;
    }
    @Override
    public boolean isVcodeTure(HttpSession session, String vcode){
        if (session==null) return false;
        String code =(String) session.getAttribute("Vcode");
        if (code.equals(vcode)) return true;
        else return  false;
    }
    @Override
    public User selectUserByName(String username){
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> UserList = userMapper.selectByExample(example);
        if (UserList==null) return null;
        else return UserList.get(0);
    }
    public String isLogined(Cookie [] cookies)  throws Exception{
        String username = "";
        boolean flag = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(URLDecoder.decode(cookie.getName(), "utf-8"));
                if (URLDecoder.decode(cookie.getName(), "utf-8").equals("username")) { // 表明已经登陆过了，就直接跳转了
                    flag = true;
                    username = URLDecoder.decode(cookie.getValue(), "utf-8");
                }
            }
        }
        if (!flag) return null;
        else return username;
    }

}
