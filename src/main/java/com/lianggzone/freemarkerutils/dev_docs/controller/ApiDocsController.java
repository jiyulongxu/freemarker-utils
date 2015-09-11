/*
 * Copyright 2013-2015 lianggzone all rights reserved.
 * @license http://www.lianggzone.com/about
 */
package com.lianggzone.freemarkerutils.dev_docs.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lianggzone.freemarkerutils.dev_docs.model.ApiDocModel;
import com.lianggzone.freemarkerutils.dev_docs.model.ApiTabModel;
import com.lianggzone.freemarkerutils.utils.FreeMarkerFactory;

/**
 * 
 * @author 梁桂钊
 * @since 
 * <p>更新时间: 2015年9月11日  v0.1</p><p>版本内容: 创建</p>
 */
public class ApiDocsController {

    private final static Logger logger = LoggerFactory.getLogger(ApiDocsController.class);
    
    
    public static void main(String[] args) {
        ApiDocsController apiDocsController = new ApiDocsController();
        
        String content = apiDocsController.getApiDocsContent();

        List<ApiDocModel> apiDocList = apiDocsController.getApiDocsObj(content);
        
        apiDocsController.createHtml(apiDocList);
    }
    
    private void createHtml(List<ApiDocModel> apiDocList){
        String apiDocsFtl = Thread.currentThread().getContextClassLoader().getResource("dev_docs/api_docs.ftl").getPath();
        
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("apidoclist", apiDocList);
        
        try {
            FreeMarkerFactory.createHTML(apiDocsFtl, "d：/api_docs.html", data);
        } catch (IOException e) {
            logger.error("方法:{}, 状态:{}, 错误详情:{}", new Object[] {"createHtml", "失败", e});
        }
    }
    
    /**
     * 获取ApiDocs对象
     * @return
     */
    private List<ApiDocModel> getApiDocsObj(String context){
        List<ApiDocModel> apiDocList = new ArrayList<ApiDocModel>();
         
        JSONObject json = JSONObject.parseObject(context);
        JSONArray items = json.getJSONArray("items");
        for (int i = 0; i < items.size(); i++) {
            JSONObject item = items.getJSONObject(i);
            
            ApiDocModel apiDocModel = new ApiDocModel();
            apiDocModel.setCategory(item.getString("category"));
            apiDocModel.setType(item.getString("type"));
            
            List<ApiTabModel> tabList = new ArrayList<ApiTabModel>();
            JSONArray tabs = item.getJSONArray("tab");
            for (int j = 0; j < tabs.size(); j++) {
                tabList.add(JSONObject.toJavaObject(tabs.getJSONObject(j), ApiTabModel.class));
            }
            apiDocModel.setTab(tabList);
            
            apiDocList.add(apiDocModel);
        }
        return apiDocList;
    }
    
    
    /**
     * 获取ApiDocs内容
     * @return
     */
    private String getApiDocsContent(){
        String apiDocsPath = Thread.currentThread().getContextClassLoader().getResource("dev_docs/api_docs.json").getPath();
        String json = "";
        try {
            json = FileUtils.readFileToString(new File(apiDocsPath));
        } catch (IOException e) {
            logger.error("方法:{}, 状态:{}, 错误详情:{}", new Object[] {"getApiDocsContent", "失败", e});
        } 
        return json;
    }
}
