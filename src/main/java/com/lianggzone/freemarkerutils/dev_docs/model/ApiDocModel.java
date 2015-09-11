/*
 * Copyright 2013-2015 lianggzone all rights reserved.
 * @license http://www.lianggzone.com/about
 */
package com.lianggzone.freemarkerutils.dev_docs.model;

import java.util.List;

/**
 * ApiDocModel
 * @author 梁桂钊
 * @since 
 * <p>更新时间: 2015年9月11日  v0.1</p><p>版本内容: 创建</p>
 */
public class ApiDocModel {

    private String category;
    private String type;
    private List<ApiTabModel> tabList;
    
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<ApiTabModel> getTab() {
        return tabList;
    }
    public void setTab(List<ApiTabModel> tabList) {
        this.tabList = tabList;
    }
}
