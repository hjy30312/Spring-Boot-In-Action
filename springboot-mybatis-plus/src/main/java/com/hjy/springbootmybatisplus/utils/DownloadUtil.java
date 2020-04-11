package com.hjy.springbootmybatisplus.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

}
