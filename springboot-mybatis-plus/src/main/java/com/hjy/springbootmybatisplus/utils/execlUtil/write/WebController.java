package com.hjy.springbootmybatisplus.utils.execlUtil.write;

import com.hjy.springbootmybatisplus.utils.DownloadUtil;
import com.hjy.springbootmybatisplus.utils.execlUtil.DemoData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hjy
 * @date 2020/4/20 0:16
 */
@RestController
@RequestMapping("/downloadexecl")
public class WebController {

    @RequestMapping("/execl")
    public String downloadJSON(HttpServletRequest request, HttpServletResponse response) {
        List contentList = data();
        try {
            DownloadUtil.downloadEXECL("大洋", contentList, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setNo("字符串" + i);
            data.setCreateTime(new Date());
            data.setScore(0.56);
            list.add(data);
        }
        return list;
    }
}
