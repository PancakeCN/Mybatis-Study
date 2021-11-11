package com.pancake.dao;

import com.pancake.pojo.User;
import com.pancake.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {
    @Test
    public void test(){
        //1、获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        //官方推荐使用finally包围，特别是【关闭数据库应当放在finally里面】
        try{
            //2、执行SQL
            //方式一：（反射） 通过UserDao接口的class对象获取
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getUserList();

            //方式二：(见过就好，不推荐使用)
            //List<User> userList = sqlSession.selectList("com.pancake.dao.UserDao.getUserList");

            for (User user: userList) {
                System.out.println(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //3、关闭SqlSession
            sqlSession.close();
        }
    }

}
