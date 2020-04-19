package com.hjy.springbootmybatisplus.utils.execlUtil;

import lombok.Data;

import java.util.Date;

/**
 * @author hjy
 * @date 2020/4/19 22:59
 */
@Data
public class DemoData {

    /**
     * 学号
     */
    private String no;
    /**
     * 成绩
     */
    private Double score;
    /**
     * 创建时间
     */
    private Date createTime;

}
