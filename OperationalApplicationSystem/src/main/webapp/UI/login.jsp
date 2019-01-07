<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='/struts-tags' prefix='s' %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Operational Application System</title>
<meta name="renderer" content="webkit">
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

<style type="text/css">
html, body { height: 100%; overflow: hidden; }
body {
    font-family: "Verdana", "Tahoma", "Lucida Grande", "Microsoft YaHei", "Hiragino Sans GB", sans-serif;
    background: url(images/loginbg_01.jpg) no-repeat center center fixed;
    background-size: cover;
}
.form-control{height:37px;}
.main_box{position:absolute; top:45%; left:50%; margin:-200px 0 0 -180px; padding:15px 20px; width:360px; height:400px; min-width:320px; background:#FAFAFA; background:rgba(255,255,255,0.5); box-shadow: 1px 5px 8px #888888; border-radius:6px;}
.login_msg{height:30px;}
.input-group >.input-group-addon.code{padding:0;}
#captcha_img{cursor:pointer;}
.main_box .logo img{height:35px;}
@media (min-width: 768px) {
    .main_box {margin-left:-240px; padding:15px 55px; width:480px;}
    .main_box .logo img{height:40px;}
}
</style>
<script type="text/javascript">
var COOKIE_NAME = 'sys_em_username';
$(function() {
	if('${login_message}'!=''){
		BJUI.alertmsg('error', '${login_message}');
	} 
    choose_bg();
    changeCode();
    if ($.cookie(COOKIE_NAME)){
        $("#j_uid").val($.cookie(COOKIE_NAME));
        $("#j_password").focus();
        $("#j_remember").attr('checked', true);
    } else {
        $("#j_uid").focus();
    }
    $("#captcha_img").click(function(){
        changeCode();
    });
 
});

function checkform(){
    var issubmit = true;
    var i_index  = 0;
    $(this).find('.form-control').each(function(i){
        if ($.trim($(this).val()).length == 0) {
            $(this).css('border', '1px #ff0000 solid');
            issubmit = false;
            if (i_index == 0)
                i_index  = i;
        }
    });
    if (!issubmit) {
        $(this).find('.form-control').eq(i_index).focus();
        return false;
    }
    
    var $remember = $("#j_remember");
    if ($remember.attr('checked')) {
        $.cookie(COOKIE_NAME, $("#j_jobnumber").val(), { path: '/', expires: 15 });
    } else {
        $.cookie(COOKIE_NAME, null, { path: '/' });  //删除cookie
    }
    
    if( $("#j_uid").val()==''||$("#j_password").val()==''){
    	BJUI.alertmsg('warn', 'Id and password can`t null!');	
    	return false;
    }
    
    $("#login_ok").attr("disabled", true).val('login..');
    /*
    var key = CryptoJS.enc.Base64.parse($("#j_randomKey").val());
    var iv = CryptoJS.enc.Latin1.parse('0102030405060708');
    var password = CryptoJS.AES.encrypt($("#j_password").val(), key, {iv:iv, mode:CryptoJS.mode.CBC, padding:CryptoJS.pad.Pkcs7 });
    
    $("#j_password").val(password)
    */
    
    return true;
    
    //location.href = 'index.jsp'
	
}
/* function changeCode(){
	var img=document.getElementById("captcha_img");
	img.src="../ValidationCode?"+Math.random();	
} */
function choose_bg() {
    var bg = Math.floor(Math.random() * 9 + 1);
    $('body').css('background-image', 'url(images/loginbg_0'+ bg +'.jpg)');
}

function getPassword(){
	if($('#j_uid').val()==''){
		BJUI.alertmsg('warn', 'Your ID is impty!');		
	}else{
		BJUI.ajax('doajax', {
		    url: 'forgetPassword.action',
		    loadingmask: true,
		    data:{uid:$('#j_uid').val()},	    
		    okCallback: function(json, options) {
	            if(json.status='200'){
	            	 BJUI.alertmsg('info', json.message); 
	            }else{
	            	 BJUI.alertmsg('error', json.message); 
	            }
		    }
		});	
	}
	
	
}

</script>
</head>
<body>
<!--[if lte IE 7]>
<style type="text/css">
#errorie {position: fixed; top: 0; z-index: 100000; height: 30px; background: #FCF8E3;}
#errorie div {width: 900px; margin: 0 auto; line-height: 30px; color: orange; font-size: 14px; text-align: center;}
#errorie div a {color: #459f79;font-size: 14px;}
#errorie div a:hover {text-decoration: underline;}
</style>
<div id="errorie"><div>您还在使用老掉牙的IE，请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
<![endif]-->
<div class="container">
    <div class="main_box">
        <form action="login.action" id="login_form" method="post" data-toggle="validate" onsubmit="return checkform();">
            <input type="hidden" value="" id="j_randomKey" />
            <input type="hidden" name="jfinal_token" value="" />
            <br>
            <p class="text-center logo"><img src="images/logo1.png" height="45"></p>
            <div class="login_msg text-center"><font color="red"></font></div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon" id="sizing-addon-user"><span class="glyphicon glyphicon-user"></span></span>
                    <input type="text" class="form-control" id="j_uid" name="uid" value=""  placeholder="ID" aria-describedby="sizing-addon-user">
                </div>
                <div  align="right">
                <span> 域名</span>
                    <select data-toggle="selectpicker"  name="domain" >
		              <option value="@cimtasnbo.com">@cimtasnbo.com</option>
	            	</select>
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon" id="sizing-addon-password"><span class="glyphicon glyphicon-lock"></span></span>
                    <input type="password" class="form-control" id="j_password" name="password"  placeholder="Password" aria-describedby="sizing-addon-password">
                </div>
            </div>
            <div class="form-group">
                <div class="checkbox">
                    <label for="j_remember" class="m"><input id="j_remember" type="checkbox" value="true">&nbsp;Remember my login information!</label>
                </div>
            </div>
            <br>
            <div class="text-center">
                <button type="submit" id="login_ok" class="btn btn-primary btn-lg">&nbsp;Login&nbsp;</button>&nbsp;             
                <button type="reset" class="btn btn-default btn-lg">&nbsp;Reset&nbsp;</button>&nbsp; 
                <br>
                <a href="#" onclick="getPassword();">Forget password</a>
                <!--  <a href="register.html" data-toggle="dialog" data-id="sys_user_changepass" data-mask="true" data-width="300" data-height="380">注册</a>  -->                      	
            </div>
            <br>
            <div class="text-center">
                <hr>
            </div>
        </form>
    </div>
    
    
</div>
</body>
</html>