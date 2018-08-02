<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='/struts-tags' prefix='s' %>

<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Operational Application System</title>
<meta name="Keywords" content="COGS Analysis"/>
<meta name="Description" content="COGS Analysis"/> 
<!-- bootstrap - css -->
<link href="B-JUI/themes/css/bootstrap.css" rel="stylesheet">
<!-- core - css -->
<link href="B-JUI/themes/css/style.css" rel="stylesheet">
<link href="B-JUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
<link href="B-JUI/themes/css/fontsize.css" id="bjui-link-theme" rel="stylesheet">
<!-- plug - css -->
<link href="B-JUI/plugins/kindeditor_4.1.11/themes/default/default.css" rel="stylesheet">
<link href="B-JUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="B-JUI/plugins/nice-validator-1.0.7/jquery.validator.css" rel="stylesheet">
<link href="B-JUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
<link href="B-JUI/plugins/webuploader/webuploader.css" rel="stylesheet">
<link href="B-JUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
<!-- Favicons -->
<link rel="apple-touch-icon-precomposed" href="images/logo2.png">
<link rel="shortcut icon" href="images/logo2.png">
<!--[if lte IE 7]>
<link href="B-JUI/themes/css/ie7.css" rel="stylesheet">
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
    <script src="B-JUI/other/html5shiv.min.js"></script>
    <script src="B-JUI/other/respond.min.js"></script>
<![endif]-->
<!-- jquery -->
<script src="B-JUI/js/jquery-1.11.3.min.js"></script>
<script src="B-JUI/js/jquery.cookie.js"></script>
<!--[if lte IE 9]>
<script src="B-JUI/other/jquery.iframe-transport.js"></script>
<![endif]-->
<!-- B-JUI -->
<%-- <script src="B-JUI/js/bjui-all.min.js"></script> --%>
<script src="B-JUI/js/bjui-all.js"></script>
<!-- plugins -->
<!-- swfupload for kindeditor -->
<script src="B-JUI/plugins/swfupload/swfupload.js"></script>
<!-- Webuploader -->
<script src="B-JUI/plugins/webuploader/webuploader.js"></script>
<!-- kindeditor -->
<script src="B-JUI/plugins/kindeditor_4.1.11/kindeditor-all-min.js"></script>
<script src="B-JUI/plugins/kindeditor_4.1.11/lang/zh-CN.js"></script>
<!-- colorpicker -->
<script src="B-JUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- ztree -->
<script src="B-JUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script src="B-JUI/plugins/nice-validator-1.0.7/jquery.validator.js"></script>
<script src="B-JUI/plugins/nice-validator-1.0.7/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script src="B-JUI/plugins/bootstrap.min.js"></script>
<script src="B-JUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<script src="B-JUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
<!-- icheck -->
<script src="B-JUI/plugins/icheck/icheck.min.js"></script>
<!-- HighCharts -->
<script src="B-JUI/plugins/highcharts/highcharts.js"></script>
<script src="B-JUI/plugins/highcharts/highcharts-3d.js"></script>
<script src="B-JUI/plugins/highcharts/themes/gray.js"></script>
<!-- other plugins -->
<script src="B-JUI/plugins/other/jquery.autosize.js"></script>
<link href="B-JUI/plugins/uploadify/uploadify.css" rel="stylesheet">
<script src="B-JUI/plugins/uploadify/jquery.uploadify.min.js"></script>
<script src="B-JUI/plugins/download/jquery.fileDownload.js"></script>
<!-- util -->
<script src="B-JUI/js/util.js"></script>
<script src="B-JUI/js/dict.js"></script>
<script src="B-JUI/js/ajaxfileupload.js"></script>
<!-- zoomify -->
<script src="B-JUI/plugins/zoomify/zoomify.min.js"></script>
<link href="B-JUI/plugins/zoomify/zoomify.min.css" rel="stylesheet">
<!-- bootbox -->
<script src="B-JUI/plugins/bootbox/bootbox.min.js"></script>



<!-- init -->
<style type="text/css">
.bar {
    height: 18px;
    background: green;
}
</style>


<script type="text/javascript">
$(function() {
    BJUI.init({
        JSPATH       : 'B-JUI/',         //[可选]框架路径
        PLUGINPATH   : 'B-JUI/plugins/', //[可选]插件路径
        loginInfo    : {url:'login_timeout.html', title:'登录', width:440, height:240}, // 会话超时后弹出登录对话框
        statusCode   : {ok:200, error:300, timeout:301}, //[可选]
        ajaxTimeout  : 300000, //[可选]全局Ajax请求超时时间(毫秒)
        alertTimeout : 3000,  //[可选]信息提示[info/correct]自动关闭延时(毫秒)
        alertMsg: {displayPosition:'middlecenter', alertTimeout: 6000},
        pageInfo     : {total:'totalRow', pageCurrent:'pageCurrent', pageSize:'pageSize', orderField:'orderField', orderDirection:'orderDirection'}, //[可选]分页参数
        keys         : {statusCode:'statusCode', message:'message'}, //[可选]
        ui           : {
                         sidenavWidth     : 280,
                         showSlidebar     : true, //[可选]左侧导航栏锁定/隐藏
                         overwriteHomeTab : false //[可选]当打开一个未定义id的navtab时，是否可以覆盖主navtab(我的主页)
                       },
        debug        : true,    // [可选]调试模式 [true|false，默认false]
        theme        : 'green' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, red, green]
    })


	 if('${login_message}'!=''){
		 BJUI.alertmsg('error', '${login_message}'); 
	 }
	    
    //时钟
    var today = new Date(), time = today.getTime()
    $('#bjui-date').html(today.formatDate('yyyy/MM/dd'))
    setInterval(function() {
        today = new Date(today.setSeconds(today.getSeconds() + 1))
        $('#bjui-clock').html(today.formatDate('HH:mm:ss'))
    }, 1000)
    
    bjui_index_exchange()
    
    
    $('#stampTemp').zoomify();
})



//菜单-事件-zTree
function MainMenuClick(event, treeId, treeNode) {
    if (treeNode.target && treeNode.target == 'dialog' || treeNode.target == 'navtab')
        event.preventDefault()
    
    if (treeNode.isParent) {
        var zTree = $.fn.zTree.getZTreeObj(treeId)
        
        zTree.expandNode(treeNode)
        return
    }
    
    if (treeNode.target && treeNode.target == 'dialog')
        $(event.target).dialog({id:treeNode.targetid, url:treeNode.url, title:treeNode.name})
    else if (treeNode.target && treeNode.target == 'navtab')
        $(event.target).navtab({id:treeNode.targetid, url:treeNode.url, title:treeNode.name, fresh:treeNode.fresh, external:treeNode.external})
}

// 满屏开关
var bjui_index_container = 'container_fluid'

function bjui_index_exchange() {
    /* bjui_index_container = bjui_index_container == 'container_fluid' ? 'container' : 'container_fluid' */
    bjui_index_container =  'container_fluid'
    $('#bjui-top').find('> div').attr('class', bjui_index_container)
    $('#bjui-navbar').find('> div').attr('class', bjui_index_container)
    $('#bjui-body-box').find('> div').attr('class', bjui_index_container)
}


function showStampImage(id){ $('#'+id).show(400)}
function hideStampImage(id){ $('#'+id).hide(400)}

</script>
<!-- highlight && ZeroClipboard -->
<link href="assets/prettify.css" rel="stylesheet">
<script src="assets/prettify.js"></script>
<link href="assets/ZeroClipboard.css" rel="stylesheet">
<script src="assets/ZeroClipboard.js"></script>
</head>
<body>
<!-- 获取菜单 -->
    <!--[if lte IE 7]>
        <div id="errorie"><div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
    <![endif]-->
    <div id="bjui-top" class="bjui-header">
        <div class="container_fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapsenavbar" data-target="#bjui-top-collapse" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <nav class="collapse navbar-collapse" id="bjui-top-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li class="datetime"><a><span id="bjui-date">0000/00/00</span> <span id="bjui-clock">00:00:00</span></a></li>
                    <li><a href="changepassword.jsp" data-toggle="dialog" data-id="sys_user_changepass" data-mask="true" data-width="500" data-height="300">UserName：${user.name}</a></li>
                    <li><a href="#">Department：${user.department.name}</a></li>
                    <li><a href="logout.action" style="font-weight:bold;">&nbsp;<i class="fa fa-power-off"></i> Logout</a></li>
                    <li class="dropdown active"><a href="#" class="dropdown-toggle theme" data-toggle="dropdown" title="Change Skin"><i class="fa fa-tree"></i></a>
                        <ul class="dropdown-menu" role="menu" id="bjui-themes">
                            <!--
                            <li><a href="javascript:;" class="theme_default" data-toggle="theme" data-theme="default">&nbsp;<i class="fa fa-tree"></i> 黑白分明&nbsp;&nbsp;</a></li>
                            <li><a href="javascript:;" class="theme_orange" data-toggle="theme" data-theme="orange">&nbsp;<i class="fa fa-tree"></i> 橘子红了</a></li>
                            -->
                            <li><a href="javascript:;" class="theme_purple" data-toggle="theme" data-theme="purple">&nbsp;<i class="fa fa-tree"></i> Purple</a></li>
                            <li class="active"><a href="javascript:;" class="theme_blue" data-toggle="theme" data-theme="blue">&nbsp;<i class="fa fa-tree"></i> Blue</a></li>
                            <li><a href="javascript:;" class="theme_green" data-toggle="theme" data-theme="green">&nbsp;<i class="fa fa-tree"></i> Green</a></li>
                        </ul>
                    </li>
                    <!-- <li><a href="javascript:;" onclick="bjui_index_exchange()" title="横向收缩/充满屏幕"><i class="fa fa-exchange"></i></a></li> -->
                </ul>
            </nav>
        </div>
    </div>
    <header class="navbar bjui-header" id="bjui-navbar">
        <div class="container_fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" id="bjui-navbar-collapsebtn" data-toggle="collapsenavbar" data-target="#bjui-navbar-collapse" aria-expanded="false">
                    <i class="fa fa-angle-double-right"></i>
                </button>
                <a class="navbar-brand" href="#"><img src="images/logo1.png" height="30" width="400"></a>
                <a class="navbar-brand" href="#"><img src="images/empty.png" height="30"></a>
                <a class="navbar-brand" href="#"><img src="images/logo2.png" height="30"></a>
            </div>
            <nav class="collapse navbar-collapse" id="bjui-navbar-collapse">
                <ul class="nav navbar-nav navbar-right" id="bjui-hnav-navbar">
                    <s:iterator var="menu" value="#session['parentMenu']">
                    	<li>
                    	 <a href="getChildMenu.action?id=${menu.id}" data-toggle="sidenav" data-id-key="targetid">${menu.name}</a>
                    	</li>
                    </s:iterator>
                </ul>
            </nav>
        </div>
    </header>
    <div id="bjui-body-box">
        <div class="container_fluid" id="bjui-body">
            <div id="bjui-sidenav-col">
                <div id="bjui-sidenav">
                    <div id="bjui-sidenav-arrow" data-toggle="tooltip" data-placement="left" data-title="Hide Left Menu">
                        <i class="fa fa-long-arrow-left"></i>
                    </div>
                    <div id="bjui-sidenav-box">
                        
                    </div>
                </div>
            </div>
            <div id="bjui-navtab" class="tabsPage">
                <div id="bjui-sidenav-btn" data-toggle="tooltip" data-title="Show Left Menu" data-placement="right">
                    <i class="fa fa-bars"></i>
                </div>
                <div class="tabsPageHeader">
                    <div class="tabsPageHeaderContent">
                        <ul class="navtab-tab nav nav-tabs">
                            <li><a href="javascript:;"><span><i class="fa fa-home"></i> #maintab#</span></a></li>
                        </ul>
                    </div>
                    <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                    <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                    <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
                </div>
                <ul class="tabsMoreList">
                    <li><a href="javascript:;">#maintab#</a></li>
                </ul>
                <div class="navtab-panel tabsPageContent">
                    <div class="navtabPage unitBox">
                        <div class="bjui-pageContent" style="position:absolute;width:100%;height:100%;z-index:-1;background-image:url(images/loginbg_05.jpg);background-attachment: fixed;background-repeat: no-repeat;background-size: cover;">
                        	<br><br>
                        	<div style="position:absolute; left: 100px; top:50px">
                        		<a href="getChildMenu.action?id=40289f81605e86cf01605e87be510001" data-toggle="sidenav" data-id-key="targetid" onclick="hideStampImage('showStampExplain')"><img src="images/menu/payment.png" height="60"></a>
                        	</div>
                        	<div style="position:absolute; left: 100px; top:130px">
                        		<a href="getChildMenu.action?id=40284c816243ad5b016243b365bd0005" data-toggle="sidenav" data-id-key="targetid" onclick="showStampImage('showStampExplain')"><img src="images/menu/stamp.png" height="60"></a>
                        	</div>
                        	
                        	<div id="showStampExplain" class="highlight" style="display:none;position:absolute; left:500px;top:50px;width:400px">
								<pre class="prettyprint">
<font size="3" color="red">Instruction:</font>
1.正常情况下的盖章用印申请完成时间为申请审批通过的次日下午4点-5点可以至XX领单子。
2.急件的情况下直接至XX处等待领取
3.填单样式参考 下图(点击放大)
</pre>   
<img alt="点击查看大图" src="images/temp/stampTemp.png" id="stampTemp"  height="150" width="150" style="left:30px"><br>
<pre class="prettyprint">
4.附件要求: 下列附件必需提供附件扫描档
    ① 人事文件 - 个人
    ② 人事文件 - 合同协议
    ③ EPZ 新项目
    ④ 其他业务文件 
5.系统联系: 
    审计室 - 167
</pre>       	


                        	</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="B-JUI/other/ie10-viewport-bug-workaround.js"></script>
</body>
</html>


