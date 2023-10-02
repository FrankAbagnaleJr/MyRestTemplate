package com.kyrie.test;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


/**
 * @auther: jijin
 * @date: 2023/10/2 16:20 周一
 * @project_name: MyRestTemplate
 * @version: 1.0
 * @description 根据url远程调用
 */

@Service
public class RemoteUrl {

    @Value("${weixin.appid}")
    String appid;

    @Value("${weixin.secret}")
    String secret;

    @Autowired
    RestTemplate restTemplate;


    public Map<String, String> remoteUrl(String code) {

        //调用的路径
        String url_template = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        //给路径添加请求参数
        String url = String.format(url_template, appid, secret, code);

        //远程调用此url
        /**
         * url：是地址
         * HttpMethod.POST：是请求方式
         * requestEntity：是请求参数
         * String.class：是返回的类型
         */
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, null, String.class);

        //获取响应的结果
        String result = exchange.getBody();

        //将result转成map，这里用的阿里巴巴的FastJson
        Map<String,String> map = JSON.parseObject(result, Map.class);

        return map;
    }
}
