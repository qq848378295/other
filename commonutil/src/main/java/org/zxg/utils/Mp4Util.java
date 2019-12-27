package org.zxg.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class Mp4Util {
    public static void main(String[] args) throws  Exception {
        long l1=System.currentTimeMillis();
//        execute("D:/a.jpg","D:/a.mp4","D:/out.mp4");
//        getImg("D:/share/music-video/a.mp4","24","5");
        cutVideo("D:/share/music-video/a.mp4","70","","D:/share/music-video/b.mp4");
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

    //ffmpeg -i [视频路径] -r 1 -q:v 2 -f image2 image-%d.jpeg
    //视频路径：如 "myvideo.mp4"(这时这个视频也在bin文件目录下才可以直接这么写)，或者完整路径的
    //-r：每秒提取的帧数，如上面为每秒1帧，即一张图像
    //-q:v ：图片质量
    //-f：图片格式，上述为image2
    //image-%d.jpeg：生成图像的文件名，可以加上完整路径，%d会使文件名按整数编号，如上述生成图像为image-1.jpeg, image-2.jpeg, ...
    //还有其他参数：
    //-t：持续时间，如-t 4表示持续4s
    //-ss：起始时间，如-ss 01:30:14，从01:30:14开始
    //-vframes：指定抽取的帧数，如-vframes 120，指定抽取120张
    //-s：格式大小，如-s 640x360
    //-y：覆盖，直接使用
    public static void getImg(String path,String ss,String t){
        String cmd="ffmpeg -i "+path+" -r 1 -q:v 2 -ss "+ss+" -t "+t+" -f image2 image-%d.jpeg";
        System.out.println(cmd);
        String result= MyRunTimeUtil.execCmdWithResult(cmd);
        System.out.println(result);
    }

    /**
     *  截取视频
     * @param path 视频文件路径
     * @param ss 开始时间
     * @param t 裁剪的时间(为空 则截取后面所有)
     * @param outPath 输出地址
     */
    public static void cutVideo(String path,String ss,String t,String outPath){
        String cmd1="ffmpeg -i "+path+" -ss "+ss+(StringUtils.isEmpty(t)?"":" -t "+t)+" "+outPath;//-ss 5指定从输入视频第1:05秒开始截取，-t 10指明最多截取10秒。
        String cmd2="ffmpeg -ss "+ss+" -i "+path +(StringUtils.isEmpty(t)?"":" -t "+t)+"  -c:v copy -c:a copy "+outPath;
        //把-ss 1:05放到-i前面，与原来的区别是，这样会先跳转到第1:05秒在开始解码输入视频，而原来的会从开始解码，只是丢弃掉前1:05秒的结果。
        //-c:v 和 -c:a分别指定视频和音频的编码格式。
        //-c:v copy -c:a copy标示视频与音频的编码不发生改变，而是直接复制，这样会大大提升速度。
        String result= MyRunTimeUtil.execCmdWithResult(cmd2);
        System.out.println(result);
    }


    /**
     *
     * @param path
     */
    private static void saveAndExecCmd(String path)  {
        Map<String, String> map = getSortList(path);
        String name = map.get("name");
        StringBuffer sb = new StringBuffer();
        sb.append("cd ").append(path).append("\n").append(path,0,path.indexOf(":") + 1).append("\n"); // 跳转到目录下
        StringBuffer concat = new StringBuffer();
        for (int i = 1; i <= Integer.valueOf(map.get("count")); i++) {
            String mp4 = String.format(name, i);
            String ts = mp4 + ".ts";
            sb.append(" ffmpeg -i " + mp4 + " -vcodec copy -acodec copy -vbsf h264_mp4toannexb " + ts).append("\n"); //先转成普通ts 文件 然后拼接
            if (i == 1) {
                concat.append(ts);
            } else {
                concat.append("|").append(ts);
            }
        }
        sb.append("ffmpeg -i \"concat:").append(concat.toString()).append("\" -acodec copy -vcodec copy -absf aac_adtstoasc output.mp4").append("\n").append("exit");
        try {
            File file=new File(System.getProperty("java.io.tmpdir") +"/"+ UUID.randomUUID().toString()+".cmd");
            System.out.println(file.getAbsolutePath());
            FileUtils.write(file,sb.toString(), Charset.forName("gbk"));
            MyRunTimeUtil.execCmdWithResult("cmd.exe /c start "+file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到正确排序后的文件
     *
     * @param filePath
     * @return
     */
    public static Map<String, String> getSortList(String filePath) {
        File file = new File(filePath);
        Set<String> set = new TreeSet(Comparator.reverseOrder());
        String name = null;
        for (File f : file.listFiles()) {
            if (f.isFile() && f.getName().endsWith("mp4")) {
                set.add(f.getName());
                if (name == null) {
                    name = f.getName();
                }
            }
        }
        //获取通用的格式
        int lastIndex = name.lastIndexOf("-");
        int lastIndex2 = name.lastIndexOf(".");
        name = name.substring(0, lastIndex + 1) + "%d" + name.substring(lastIndex2);
        Map<String, String> map = new HashMap<>();
        map.put("count", String.valueOf(set.size()));
        map.put("name", name);
        return map;
    }
}
