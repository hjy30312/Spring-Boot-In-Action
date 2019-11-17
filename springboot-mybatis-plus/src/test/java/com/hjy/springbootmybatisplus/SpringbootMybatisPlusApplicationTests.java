package com.hjy.springbootmybatisplus;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjy.springbootmybatisplus.mapper.UserMapper;
import com.hjy.springbootmybatisplus.pojo.User;
import com.hjy.springbootmybatisplus.service.UserService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Log
class SpringbootMybatisPlusApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    UserService userService;

    @Resource
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

        // 需要总记录数
        IPage<User> iPage = userMapper.selectPage(page,queryWrapper);
        System.out.println("总页数：" + iPage.getPages());
        System.out.println("总记录数：" + iPage.getTotal());
        System.out.println(JSON.toJSONString(iPage.getRecords()));

        // 不返回记录数
        Page<User> page2 = new Page<>(1, 2,false);
        IPage<User> iPage2 = userMapper.selectPage(page2,queryWrapper);
        System.out.println("总页数：" + iPage2.getPages());
        System.out.println("总记录数：" + iPage2.getTotal());
        System.out.println(JSON.toJSONString(iPage2.getRecords()));
    }


    /**
     * xml自定义查询
     */
    @Test
    public void selectMyPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name","张三");
        Page<User> page = new Page<>(1, 2);
        IPage<User> iPage2 = userMapper.selectUserPage(page,queryWrapper);
        System.out.println("总页数：" + iPage2.getPages());
        System.out.println("总记录数：" + iPage2.getTotal());
        System.out.println(JSON.toJSONString(iPage2.getRecords()));
    }

    /**
     * 根据id更新
     */
    @Test
    public void update() {
        User user = new User();
        user.setUserId(1L);
        user.setUserName("李四");
        //  使用了baseMapper.updateById(user);
        userService.updateUser(user);

    }
    /**
     * where构造器更新
     */
    @Test
    public void updateByWrapper() {
        User user = new User();
        user.setUserName("王五");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_age",11);
        //全更新  userService.update(user,null);
        userService.update(user,queryWrapper);
        //userService.update(user,null);
    }

    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1L);
        System.out.println("删除条数:" + rows);
    }

}
