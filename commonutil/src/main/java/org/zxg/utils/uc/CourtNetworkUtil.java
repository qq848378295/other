package org.zxg.utils.uc;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class CourtNetworkUtil {
    public static void main(String[] args) throws IOException {
        Document doc= Jsoup.parse(new File("D:/uc.txt"), Charset.defaultCharset().name());
//        http://a.mp.uc.cn/media.html?mid=84229754edf544798a3868966dcb6980&client=ucweb&uc_param_str=frdnsnpfvecpntnwprdsssnikt
        System.out.println(doc.html());
    }
}
