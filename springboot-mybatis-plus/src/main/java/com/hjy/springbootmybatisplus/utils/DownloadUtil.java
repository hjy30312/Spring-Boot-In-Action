package com.hjy.springbootmybatisplus.utils;

import com.alibaba.excel.EasyExcel;
import com.hjy.springbootmybatisplus.utils.execlUtil.DemoData;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 用于各种下载
 * @author hjy
 * @date 2020/4/11 12:47
 */
@Slf4j
public class DownloadUtil {

    /**
     *
     * @param fileName 下载文件名
     * @param content 文件内容
     * @param response
     */
    public static void downloadJSON(String fileName, String content, HttpServletResponse response) {
        download(fileName, ".json", content, response);
    }

    /**
     * 下载txt文件
     * @param fileName 下载文件名
     * @param content 文件内容
     * @param response
     */
    public static void downloadTxt(String fileName, String content, HttpServletResponse response) {
        download(fileName,".txt", content, response);
    }

    private static void download(String fileName, String fileType, String str,  HttpServletResponse response) {
        OutputStream out = null;
        try {
            // 处理文件名显示中文乱码问题
            fileName = URLEncoder.encode(fileName + fileType,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            out = response.getOutputStream();
            // 序列化成字节数组
            byte[] buffer = str.getBytes();
            // 写入输出流
            out.write(buffer);
        } catch (IOException e) {
            log.error("下载文件异常：" + e.getMessage(), e);
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载execl文件
     * @param response
     * @param fileName 文件名
     * @param contentList 内容
     * @throws IOException
     */
    public static void downloadEXECL(String fileName, List contentList, HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), DemoData.class).sheet("模板").doWrite(contentList);
    }
}
