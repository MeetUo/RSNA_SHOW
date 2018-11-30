package cn.rsna.service.Impl;

import cn.rsna.dao.UserMapper;
import cn.rsna.entity.User;
import cn.rsna.entity.UserExample;
import cn.rsna.entity.UserKey;
import cn.rsna.service.IUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegisterServiceImpl implements IUserRegisterService {
    @Autowired
    private UserMapper userMapper;
    public int add(User user){
      return  userMapper.insert(user);
    }
    public boolean select(String username){
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> UserList = userMapper.selectByExample(example);
        if (UserList.isEmpty()) return false;
        return true;
    }
}
