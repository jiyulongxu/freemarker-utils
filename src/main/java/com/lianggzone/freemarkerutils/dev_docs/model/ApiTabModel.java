/*
 * Copyright 2013-2015 lianggzone all rights reserved.
 * @license http://www.lianggzone.com/about
 */
package com.lianggzone.freemarkerutils.dev_docs.model;

/**
 * ApiTabModel
 * @author 梁桂钊
 * @since 
 * <p>更新时间: 2015年9月11日  v0.1</p><p>版本内容: 创建</p>
 */
public class ApiTabModel {
    
    private String name;
    private String url;
    private String remark;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
