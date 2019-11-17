package com.hjy.springbootmybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tbl_user")
public class User {

    @TableId(value = "user_id")
    private Long userId;

    private String userName;

    private String userAge;

    public User() {
    }

    public User(Long userId, String userName, String userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
    }
}
