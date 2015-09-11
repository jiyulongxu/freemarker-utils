<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>在线文档</title>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/circle_img.css">
    <link rel="stylesheet" href="../../css/main_ext.css">
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
	<#if apidoclist??>
		<#list apidoclist as apidoc>
		<#if apidoc.type == "1">
		<div class="widget"><div class="row"><div class="col-sm-3"><h3 class="title">${apidoc.category}</h3></div></div></div>
		<div class="row">
        	<#list apidoc.tab as tab>
	        <div class="col-sm-3">
	            <div class="case-item">
	                <div class="ih-item circle effect1">
	                    <a href="${tab.url}" target="_blank">
	                        <div class="spinner"></div>
	                        <div class="img"><img src="../../images/${tab.name?lower_case}.png" alt="${tab.name}"></div>
	                        <div class="info"><div class="info-back"><h3>${tab.name}</h3><p>${tab.remark}</p></div></div>
	                    </a>
	                </div>
	            </div>
	        </div>     
        	</#list>
        </div>
        </#if>
        <#if apidoc.type == "2">
        <div class="widget"><div class="row"><div class="col-sm-3"><h3 class="title">${apidoc.category}</h3></div></div>         
	    <div class="row">
	    	<div class="col-sm-12">
		    	<div class="content tag-cloud">
		    	<#list apidoc.tab as tab>
		    		<a href="${tab.url}" target="_blank">${tab.name}</a>
		    	</#list>
		    	</div>
	    	</div>
	    </div>
	    </div>
	    </#if>
	    </#list>
	</#if>
    </div>
</body>
</html>