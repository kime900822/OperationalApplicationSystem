<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script type="text/javascript">

		$(function(){
			
			if('${param.uid}'!=""){
				$.CurrentDialog.find('#j_user_edit_uid').attr("readonly","");				
			}
			
			BJUI.ajax('doajax', {
			    url: 'getAllRole.action',
			    loadingmask: false,
			    okCallback: function(json, options) {
	                $.each(json, function (i, item) {
	                    $.CurrentDialog.find('#j_user_edit_rid').append("<option value='" + item.rid + "'>" + item.name + "</option>")           
	                })
	                $.CurrentDialog.find('#j_user_edit_rid').selectpicker('val','${param.rid}');
	                $.CurrentDialog.find('#j_user_edit_rid').selectpicker('refresh');
			    }
			})	
			
			BJUI.ajax('doajax', {
			    url: 'getAllDepartment.action',
			    loadingmask: false,
			    okCallback: function(json, options) {
	                $.each(json, function (i, item) {
	                    $.CurrentDialog.find('#j_user_edit_did').append("<option value='" + item.did + "'>" + item.name + "</option>")           
	                })
	                $.CurrentDialog.find('#j_user_edit_did').selectpicker('val','${param.did}');
	                $.CurrentDialog.find('#j_user_edit_did').selectpicker('refresh');
			    }
			})	
		})

		


</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
        <form action="modUser.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-2">
                
                <label class="row-label">ID</label>
                <div class="row-input required">
                    <input type="text" name="uid" id="j_user_edit_uid" value="${param.uid}" data-rule="required"  >
                </div>
                <label class="row-label">Name</label>
                <div class="row-input required">
                    <input type="text" name="name" value="${param.name}" data-rule="required">
                </div>
                <label class="row-label">Position</label>
                <div class="row-input required">
					<input type="text" name="position"  id="j_user_edit_Position" data-rule="required" value="${param.position}">
                </div>
                <label class="row-label">Role</label>
                <div class="row-input required">
                    <select name="rid" data-toggle="selectpicker" id="j_user_edit_rid" data-rule="required" data-width="100%"  >
                         <option value="" selected></option>
                    </select>
                </div>
                <label class="row-label">Deprtment</label>
                <div class="row-input required">
                    <select name="did" data-toggle="selectpicker" id="j_user_edit_did" data-rule="required" data-width="100%"  >
                         <option value="" selected></option>
                    </select>
                </div>
                <label class="row-label">Email</label>
                <div class="row-input required">
                    <input type="text" name="email"  id="j_user_edit_email" data-rule="email" value="${param.email}">
                </div>
                <label class="row-label">Password</label>
                <div class="row-input required">
                    <input type="text" name="password"  id="j_user_edit_password" data-rule="Password:required;length(6~)" value="${param.password}">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">Cancle</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">Save</button></li>
    </ul>
</div>