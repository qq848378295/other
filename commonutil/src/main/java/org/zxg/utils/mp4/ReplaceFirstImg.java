package org.zxg.utils.mp4;

import org.zxg.utils.runtime.MyRunTimeUtil;

import java.io.IOException;
import java.util.List;


public class ReplaceFirstImg {
    public static void main(String[] args) throws  Exception {
        long l1=System.currentTimeMillis();
        execute("D:/a.jpg","D:/a.mp4","D:/out.mp4");
        long l2=System.currentTimeMillis();
        System.out.println(l2-l1);
    }
    /**
     * 修改封面图片
     */
    public static void execute(String imgPath, String videoPath,String videoOutPath) {
       String cmd = "ffmpeg -i "+videoPath+" -i "+imgPath+"  -map 0 -map 1  -c copy -c:v:1 png  -disposition:v:1 attached_pic "+videoOutPath;
       String result= MyRunTimeUtil.execCmdWithResult(cmd);
       System.out.println(result);
    }

}
