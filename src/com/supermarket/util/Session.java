package com.supermarket.util;

import com.supermarket.bean.User;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 17:55
 * @Description: 保存用户对象
 */
public class Session {
    private static User user; // User对象属性

    public static User getUser() { // 属性的getXXX()方法
        return user;
    }

    public static void setUser(User user) { // 属性的setXXX()方法
        Session.user = user;
    }
}
