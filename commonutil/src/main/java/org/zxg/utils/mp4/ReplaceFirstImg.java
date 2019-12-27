package org.zxg.utils.mp4;

import org.apache.commons.lang3.StringUtils;
import org.zxg.utils.MyRunTimeUtil;


public class ReplaceFirstImg {
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

    public static void cutVideo(String path,String ss,String t,String outPath){
        String cmd1="ffmpeg -i "+path+" -ss "+ss+(StringUtils.isEmpty(t)?"":" -t "+t)+" "+outPath;//-ss 5指定从输入视频第1:05秒开始截取，-t 10指明最多截取10秒。
        String cmd2="ffmpeg -ss "+ss+" -i "+path +(StringUtils.isEmpty(t)?"":" -t "+t)+"  -c:v copy -c:a copy "+outPath;
        //把-ss 1:05放到-i前面，与原来的区别是，这样会先跳转到第1:05秒在开始解码输入视频，而原来的会从开始解码，只是丢弃掉前1:05秒的结果。
        //-c:v 和 -c:a分别指定视频和音频的编码格式。
        //-c:v copy -c:a copy标示视频与音频的编码不发生改变，而是直接复制，这样会大大提升速度。
        String result= MyRunTimeUtil.execCmdWithResult(cmd2);
        System.out.println(result);
    }
}
