package com.hjy.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.springbootmybatisplus.mapper.UserMapper;
import com.hjy.springbootmybatisplus.pojo.User;
import com.hjy.springbootmybatisplus.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public int insertUser(User user) {
        return baseMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return baseMapper.updateById(user);
    }

    @Override
    public int deleteUser(User user) {
        return baseMapper.deleteById(user.getUserId());
    }

    @Override
    public User findUserByName(String userName) {
        return baseMapper.getUserByName(userName);
    }

    @Override
    public IPage getUserPage(Page page, User user) {
        return null;
    }
}
