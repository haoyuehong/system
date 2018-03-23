package system.utils;

import lombok.extern.slf4j.Slf4j;
import system.common.JsonData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 10:39 2018/3/23
 */
@Slf4j
public class LatLngUtils {

    public static JsonData getLatAndLngByAddress(String addr){

        String address = "";
        String lat = "";
        String lng = "";
        try {
            address = java.net.URLEncoder.encode(addr,"UTF-8");
        } catch (Exception e) {
            log.error("【地址转码】地址转码错误,address={}",address);
            return JsonData.createError("【地址转码】地址转码错误");
        }
        String url = "http://api.map.baidu.com/geocoder/v2/?address="+addr+"&output=json&ak=keqEgK2e5fMflZr67mXhDiEuXvRbjAZn&callback=showLocation";
        URL myURL ;
        URLConnection httpsConn;
        try {
            myURL = new URL(url);
        } catch (Exception e) {
            log.error("【请求地址带参数转码】请求地址带参数转码错误");
            return JsonData.createError("【请求地址带参数转码】请求地址带参数转码错误");
        }

        InputStreamReader insr = null;
        try {
            httpsConn = myURL.openConnection();
            if (httpsConn != null) {
                insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                    System.out.println(data);
                    lat = data.substring(data.indexOf("lat") + "lat".length()+2, data.indexOf("},\"precise\""));
                    lng = data.substring(data.indexOf("lng") + "lng".length()+2, data.indexOf(",\"lat\""));
                }

            }
        } catch (IOException e) {
            log.error("【请求失败】请求失败，无法与百度地图取得联系");
            return JsonData.createError("【请求失败】请求失败，无法与百度地图取得联系");
        }finally {
             if(insr != null){
                try {
                    insr.close();
                } catch (IOException e) {
                    log.error("【请求失败】请求失败，无法与百度地图取得联系");
                    return JsonData.createError("【请求失败】请求失败，无法与百度地图取得联系");
                }
            }
        }
        Map<String, BigDecimal> map = new HashMap();
        map.put("lat", new BigDecimal(lat));
        map.put("lng", new BigDecimal(lng));
        return JsonData.createSuccess("获取成功",map);
    }

    public static void main(String[] args){
        String str = "您 好   ";
        System.out.println(str.replace(" ",""));

    }
}
