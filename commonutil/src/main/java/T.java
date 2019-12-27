
import org.apache.commons.io.FileUtils;
import org.zxg.utils.MyRunTimeUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class T {


    public static void main(String[] args) throws Exception {
        //D:\CNTV\Download\CNTV\《恋上北海道》第66集日本文化-角色扮演
        String path = "D:\\CNTV\\Download\\CNTV\\《恋上北海道》第66集日本文化-角色扮演\\";
        long l1 = System.currentTimeMillis();
        saveAndExecCmd(path);
        long l2 = System.currentTimeMillis();
        System.out.println("耗时:" + (l2 - l1));
    }

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
