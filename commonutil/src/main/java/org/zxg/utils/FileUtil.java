package org.zxg.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    public static  void downloadFileHttpHeaderSetting(String agent , HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.reset();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/x-msdownload"); // 不同类型的文件对应不同的MIME类型
        // 对文件名进行编码处理中文问题
        fileName = new String(fileName.getBytes(), StandardCharsets.UTF_8);
        // inline在浏览器中直接显示，不提示用户下载
        // attachment弹出对话框，提示用户进行下载保存本地
        // 默认为inline方式
        if (agent.contains("firefox")) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1); // firefox浏览器
        } else if (agent.contains("msie") || agent.contains("edge")) {
            fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());// IE浏览器
        } else if (agent.contains("chrome")) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);// 谷歌
        } else if (agent.contains("opera")) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);// 谷歌
        } else {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
    }

    public static  void downloadFileHttpHeaderSetting(String agent , HttpServletResponse response, String fileName, long size) throws UnsupportedEncodingException {
        try {
            response.reset();
        } catch (Exception e) {
        }
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/x-msdownload"); // 不同类型的文件对应不同的MIME类型
        // 对文件名进行编码处理中文问题
        fileName = new String(fileName.getBytes(), StandardCharsets.UTF_8);
        // inline在浏览器中直接显示，不提示用户下载
        // attachment弹出对话框，提示用户进行下载保存本地
        // 默认为inline方式
        if (agent.contains("firefox")) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1); // firefox浏览器
        } else if (agent.contains("msie") || agent.contains("edge")) {
            fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());// IE浏览器
        } else if (agent.contains("chrome")) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);// 谷歌
        } else if (agent.contains("opera")) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);// 谷歌
        } else {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        response.setHeader("Content-Length", String.valueOf(size));
    }

    public static void main(String[] args) {
//        new FileUtil().singleDownload(xx,xx,xx,xx);
    }
    private void singleDownload(HttpServletRequest request, HttpServletResponse response,String fileName, File file) throws Exception {
        FileUtil.downloadFileHttpHeaderSetting(request.getHeader("User-Agent").toLowerCase(), response, fileName,file.length());
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.flush();
        out.close();
    }

}
