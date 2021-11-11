package com.pancake.dao;

import com.pancake.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 模糊查询
     * @param value 模糊查询关键字
     * @return List<User>
     */
    List<User> getUserLike(String value);

    /**
     * 查询全部用户
     * @return  List<User>
     */
    List<User> getUserList();

    /**
     * 根据id查询用户
     * @param id id号
     * @return User
     */
    User getUserById(int id);

    /**
     * 根据id查询用户
     * @param map 传入的map对象
     * @return User
     */
    User getUserById2(Map<String, Object> map);

    /**
     * insert一个用户
     * @param user 用户
     * @return int
     */
    int addUser(User user);

    /**
     * 万能的Map的用法！！！
     * @param map 传入的map对象
     * @return User
     */
    int addUser2(Map<String, Object> map);

    /**
     * 修改用户
     * @param user 用户
     * @return int
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param id id号
     * @return int
     */
    int deleteUser(int id);
}
