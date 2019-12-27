package org.zxg.utils.other;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zxg.utils.response.GenericResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class TestController extends  BaseController{
    @GetMapping("/ip")
    @ResponseBody
    public GenericResponse ip(HttpServletRequest request) {
        Map<String,String> set = new HashMap();
        set.put("1", IpUtil.getClientIpAddr(request));
        set.put("2",IpUtil.getClientIpAddress(request));
        set.put("3",IpUtil.getIpAddr(request));
        set.put("4",IpUtil.getIpAddress(request));
        set.put("5",IpUtil.getRemoteAddr(request));
        set.put("6",getRealIpAddress(request));
        set.put("7",IpUtil.getIpAddress111(request));
        set.put("8",IpUtil.getPublicIp());
        return success(set);
    }
    @GetMapping("/ip2")
    @ResponseBody
    public GenericResponse ip2(HttpServletRequest request) {
        Map<String,String> map = new HashMap();
        for (String key:IpUtil.HEADERS_TO_TRY ) {
            map.put(key,request.getHeader(key));
        }
        map.put("remoteAddr",request.getRemoteAddr());
        return success(map);
    }
}
