package com.baidu.ueditor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public enum UeditorConfig {
    CONFIG();
 
    private JSONObject config;
 
    UeditorConfig() {
        StringBuilder config = new StringBuilder();
 
        try {
//此处即为获取配置文件，因为springboot的原因，目前我只能用流来获取文件内容
            InputStream stream = getClass().getClassLoader().getResourceAsStream("ueditorConfig/config.json");
            BufferedReader buff = new BufferedReader(new InputStreamReader(stream));
            String temp = null;
            while ((temp = buff.readLine()) != null) {
                config.append(temp);
            }
            //把配置文件中的注释去掉
            String configStr = config.toString().replaceAll("/\\*[\\s\\S]*?\\*/", "");
            this.config = JSON.parseObject(configStr);
        } catch (Exception e) {
            this.config = null;
        }
    }
 
    public JSONObject getConfig() {
        return config;
    }
 
    public String getConfigStr(String callback) {
        if (this.config == null)
            return (callback + "(读取配置文件失败);");
        return (callback + "(" + this.config.toJSONString() + ");");
    }
}

