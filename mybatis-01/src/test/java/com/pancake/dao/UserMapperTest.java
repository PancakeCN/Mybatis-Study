package com.pancake.dao;

import com.pancake.pojo.User;
import com.pancake.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void getUserByIdTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById(1);
        System.out.println(userById);

        sqlSession.close();
    }

    @Test
    public void addUserTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int res = mapper.addUser(new User(4, "王五", "123456"));

        if (res>0){
            System.out.println("添加成功！");
        }

        //【增删改需要提交事务！！！】
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUserTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int res = mapper.updateUser(new User(4, "问问", "1246446"));

        if (res>0){
            System.out.println("修改成功！");
        }

        //【增删改需要提交事务！！！】
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUserTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int res = mapper.deleteUser(4);

        if (res>0){
            System.out.println("删除成功！");
        }

        //【增删改需要提交事务！！！】
        sqlSession.commit();
        sqlSession.close();
    }

}
