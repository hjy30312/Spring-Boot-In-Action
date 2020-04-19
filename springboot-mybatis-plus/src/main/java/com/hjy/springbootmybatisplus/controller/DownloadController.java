package com.hjy.springbootmybatisplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.hjy.springbootmybatisplus.utils.DownloadUtil;
import com.hjy.springbootmybatisplus.utils.execlUtil.DemoData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hjy
 * @date 2020/4/11 13:15
 */
@RestController
@RequestMapping("/download")
public class DownloadController {

    @RequestMapping("/json")
    public String downloadJSON(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test", "大洋");
        String content = jsonObject.toJSONString();
//        Download.downloadJSON("test",content,request,response);
        DownloadUtil.downloadTxt("大洋", content, response);
        return "success";
    }


}
