package com.supermarket.util;

import com.supermarket.bean.User;

/**
 * @author: MaXinHong
 * @Email: 2372632949@qq.com
 * @Date: 2021/03/26 17:55
 * @Description: �����û�����
 */
public class Session {
    private static User user; // User��������

    public static User getUser() { // ���Ե�getXXX()����
        return user;
    }

    public static void setUser(User user) { // ���Ե�setXXX()����
        Session.user = user;
    }
}
