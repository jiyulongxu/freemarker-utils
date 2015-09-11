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
    
    public final static String PATH_API_DOCS_JAVA = "E:/百度云同步盘/代码同步/website/dev-docs/src/docs/api_docs_java.html";
    public final static String PATH_API_DOCS_WEB = "E:/百度云同步盘/代码同步/website/dev-docs/src/docs/api_docs_web.html";
    public final static String PATH_API_DOCS_DATABASE = "E:/百度云同步盘/代码同步/website/dev-docs/src/docs/api_docs_database.html";
    
    public static String PATH_JSON_DOCS_JAVA = Thread.currentThread().getContextClassLoader().getResource("dev_docs/api_docs_java.json").getPath();
    public static String PATH_JSON_DOCS_WEB = Thread.currentThread().getContextClassLoader().getResource("dev_docs/api_docs_web.json").getPath();
    public static String PATH_JSON_DOCS_DATABASE = Thread.currentThread().getContextClassLoader().getResource("dev_docs/api_docs_database.json").getPath();
    
    public static String PATH_FTL_DOCS = Thread.currentThread().getContextClassLoader().getResource("dev_docs/api_docs.ftl").getPath();
    
    
    
    public static void main(String[] args) {
        ApiDocsController apiDocsController = new ApiDocsController();
        
        apiDocsController.createHtml(PATH_FTL_DOCS, PATH_API_DOCS_JAVA,
        		apiDocsController.getApiDocsObj(apiDocsController.getApiDocsContent(PATH_JSON_DOCS_JAVA)));
        
        apiDocsController.createHtml(PATH_FTL_DOCS, PATH_API_DOCS_WEB,
        		apiDocsController.getApiDocsObj(apiDocsController.getApiDocsContent(PATH_JSON_DOCS_WEB)));
        
        apiDocsController.createHtml(PATH_FTL_DOCS, PATH_API_DOCS_DATABASE,
        		apiDocsController.getApiDocsObj(apiDocsController.getApiDocsContent(PATH_JSON_DOCS_DATABASE)));
    }
    
    /**
     * 创建HTML
     * @param filePath
     * @param ftlPath
     * @param apiDocList
     */
    private void createHtml(String ftlPath, String filePath, List<ApiDocModel> apiDocList){
        try {
        	Map<String, Object> param = new HashMap<String, Object>();
        	param.put("apidoclist", apiDocList);
            FreeMarkerFactory.createHTML(ftlPath, filePath, param); 
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
    private String getApiDocsContent(String filePath){
        String json = "";
        try {
            json = FileUtils.readFileToString(new File(filePath));
        } catch (IOException e) {
            logger.error("方法:{}, 状态:{}, 错误详情:{}", new Object[] {"getApiDocsContent", "失败", e});
        } 
        return json;
    }
}
