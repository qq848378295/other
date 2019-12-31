package org.zxg.utils;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    /**
     * 解析出url请求的路径，包括页面
     * @param strURL url地址
     * @return url路径
     */
    public static String getURLPage(String strURL) {
        String strPage = null;
        String[] arrSplit;
        strURL = strURL.trim().toLowerCase();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 0) {
            if (arrSplit.length > 1) {
                if (arrSplit[0] != null) {
                    strPage = arrSplit[0];
                }
            }
        }
        return strPage;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String truncateUrlPage(String strURL) {
        String strAllParam = null;
        strURL = strURL.trim();//.toLowerCase();
        String[] arrSplit = strURL.split("[?]");
        if (strURL.length() > 1 && arrSplit.length > 1 && arrSplit[1] != null) {
            strAllParam = arrSplit[1];
        }
        return strAllParam;
    }

    public static String getURLParam(String url,String attribute) {
        return getURLParam(url).get(attribute);
    }
    public static String getURLParam(URL url,String attribute) {
        return getURLParam(url.toString()).get(attribute);
    }
    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * @param url url地址
     * @return url请求参数部分
     */
    public static Map<String, String> getURLParam(String url) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String strUrlParam = truncateUrlPage(url);
        if (strUrlParam == null) {
            return mapRequest;
        }
        //每个键值为一组 www.2cto.com
        String[] arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = strSplit.split("[=]");
            if (arrSplitEqual.length > 1) { //解析出键值
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]); //正确解析
            } else {
                if (arrSplitEqual[0] != "") {
                    mapRequest.put(arrSplitEqual[0], ""); //只有参数没有值，不加入
                }
            }
        }
        return mapRequest;
    }

    public static void main(String[] args) {
        String str = "index.jsp?Action=del&id=123&sort="; // 请求url
//        str="http://a.mp.uc.cn/media.html?mid=247aa32b2caa40a0a2e7bcd166819d9f&client=ucweb&uc_param_str=frdnsnpfvecpntnwprdsssnikt\n";
        System.out.println(getURLPage(str)); //url页面路径
        String strRequestKeyAndValues="";
        Map<String, String> mapRequest = getURLParam(str); //url参数键值对
        for(String strRequestKey: mapRequest.keySet()) {
            String strRequestValue=mapRequest.get(strRequestKey);
            strRequestKeyAndValues+="key:"+strRequestKey+",Value:"+strRequestValue+";";
        }
        System.out.println(strRequestKeyAndValues);
        //获取无效键时，输出null
        System.out.println(mapRequest.get("mid"));
    }

}
