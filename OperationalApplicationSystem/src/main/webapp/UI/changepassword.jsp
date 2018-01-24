<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<script type="text/javascript">


function check(){
	if('${user.password}'!=$("#j_userinfo_changepass_oldpass").val()){
		BJUI.alertmsg('error', 'Wrong password');
		return false;
	}
	return true;
	
}

</script>


<div class="bjui-pageContent">
    <form action="change.action" data-toggle="validate" method="post" data-close-current="true" onsubmit="return check();">
        <div class="bjui-row col-1">
      		<label class="row-label">ID:</label>
            <div class="row-input">${user.uid}</div>
            <label class="row-label">Username:</label>
            <div class="row-input">${user.name}</div>
            <label class="row-label">Email:</label>
            <div class="row-input">${user.email}</div>
            <label class="row-label">Old Password:</label>
            <div class="row-input required">
                <input type="password" id="j_userinfo_changepass_oldpass" name="oldpassword" value="" data-rule="required;">
            </div>
            <label class="row-label">New Password:</label>
            <div class="row-input required">
                <input type="password" id="j_userinfo_changepass_newpass" name="password" value="" data-rule="New Password:required;length(6~)">
            </div>
            <label class="row-label">Confirm password:</label>
            <div class="row-input required">
                <input type="password" id="j_userinfo_changepass_confirmpass" name="" value="" data-rule="required;match(#j_userinfo_changepass_newpass)">
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">Close</button></li>
        <li><button type="submit" class="btn-default" data-icon="check">Confirm</button></li>
    </ul>
</div>