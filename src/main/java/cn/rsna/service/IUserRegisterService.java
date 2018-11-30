package cn.rsna.service;

import cn.rsna.entity.User;
import cn.rsna.entity.UserKey;

public interface IUserRegisterService {
    public int add(User user);
    public boolean select(String username);
}
