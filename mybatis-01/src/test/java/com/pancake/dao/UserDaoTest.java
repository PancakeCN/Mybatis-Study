package com.pancake.dao;

import com.pancake.pojo.User;
import com.pancake.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    @Test
    public void test(){
        //1、获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //2、执行SQL
        //方式一：（反射） 通过UserDao接口的class对象获取
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();

        for (User user: userList) {
            System.out.println(user);
        }

        //3、关闭SqlSession
        sqlSession.close();

    }
}
