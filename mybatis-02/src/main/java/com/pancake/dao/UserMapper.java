package com.pancake.dao;

import com.pancake.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

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
     * insert一个用户
     * @param user 用户
     * @return int
     */
    int addUser(User user);

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
