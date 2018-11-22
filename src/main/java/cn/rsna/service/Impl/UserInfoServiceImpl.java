package cn.rsna.service.Impl;
import cn.rsna.dao.IUserInfoDAO;
import cn.rsna.entity.UserInfo;
import cn.rsna.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private IUserInfoDAO dao;
    @Override
    public void add(UserInfo info) {
        dao.add(info);
    }

    public IUserInfoDAO getDao() {
        return dao;
    }

    public void setDao(IUserInfoDAO dao) {
        this.dao = dao;
    }
}
