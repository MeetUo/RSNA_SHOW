package cn.rsna.utils;

import cn.rsna.entity.User;

import java.util.Date;

public class UserMessage {

    private String phone;

    private String email;

    private String gender;

    private Date birthday;

    private String location;

    private String hosptial;

    private String headpic;

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHosptial(String hosptial) {
        this.hosptial = hosptial;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public User setMeassge(User user)
    {
        user.setPhone(phone);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthday(birthday);
        user.setLocation(location);
        user.setHosptial(hosptial);
        user.setHeadpic(headpic);
        return user;
    }

}
