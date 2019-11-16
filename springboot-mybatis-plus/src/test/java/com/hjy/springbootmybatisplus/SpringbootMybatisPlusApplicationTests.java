package com.hjy.springbootmybatisplus;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjy.springbootmybatisplus.mapper.UserMapper;
import com.hjy.springbootmybatisplus.pojo.User;
import com.hjy.springbootmybatisplus.service.UserService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log
class SpringbootMybatisPlusApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    /**
     * 添加
     */
    @Test
    public void insert() {
        User user = new User(1L,"张三","11");
        userService.insertUser(user);
    }

    /**
     * 无查询条件
     */
    @Test
    public void selectList() {
        List<User> userList = userService.list();
        log.info(JSON.toJSONString(userList));
    }


    /**
     * 根据条件构造器查询
     * 例：名字有张(模糊查询)且年龄大于10
     */
    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name","张").gt("user_age",10);
        List<User> userList = userService.list(queryWrapper);
        System.out.println(JSON.toJSONString(userList));
    }

    /**
     * 根据条件构造器查询      设置查询select字段(需要的)
     * 例：名字有张(模糊查询)且年龄大于10
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","user_name").like("user_name","张").gt("user_age",10);
        List<User> userList = userService.list(queryWrapper);
        System.out.println(JSON.toJSONString(userList));
    }

    /**
     * 根据条件构造器查询      设置查询select字段(不需要的)
     * 例：名字有张(模糊查询)且年龄大于10
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class,info->!info.getColumn().equals("user_age"))
                .like("user_name","张").gt("user_age",10);
        List<User> userList = userService.list(queryWrapper);
        System.out.println(JSON.toJSONString(userList));
    }

    /**
     * 分页查询  物理分页
     */
    @Test
    public void selectPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> page = new Page<>(1, 2);

        IPage<User> iPage = userMapper.selectPage(page,queryWrapper);
        System.out.println("总页数：" + iPage.getPages());
        System.out.println("总记录数：" + iPage.getTotal());
        System.out.println(JSON.toJSONString(iPage.getRecords()));
    }

}
