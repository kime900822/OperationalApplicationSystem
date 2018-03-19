<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script type="text/javascript">

		$(function(){
			
			BJUI.ajax('doajax', {
			    url: 'getAllCheck.action',
			    loadingmask: false,
			    okCallback: function(json, options) {
	                $.each(json, function (i, item) {
	                    $.CurrentDialog.find('#j_checkTyp_edit_Approve').append("<option value='" + item.id + "'>" + item.type + "</option>")           
	                })
	                $.CurrentDialog.find('#j_checkTyp_edit_Approve').selectpicker('val','${param.valueExplain}');
	                $.CurrentDialog.find('#j_checkTyp_edit_Approve').selectpicker('refresh');
			    }
			})	
			
		})

		function getUname(){
			var id=$.CurrentDialog.find('#j_checkTyp_edit_UserID').val();
			BJUI.ajax('doajax', {
			    url: 'getUserByID.action',
			    loadingmask: true,
			    data:{uid:id},
			    okCallback: function(json, options) {
			    	if(json.length>0){
			    		$.CurrentDialog.find('#j_checkTyp_edit_username').val(json[0].name); 
			    	}                         
			    	else{
			    		BJUI.alertmsg('error','userid不存在'); 
			    	}
			    }
			})
			
		}
		
		


</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
        <form action="modeCheckType.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-2">
                

                <input type="hidden" name="id" id="j_checkType_edit_id" value="${param.Id}"  >
 				<input type="hidden" name="type" id="j_checkType_edit_type" value="CHECKTYPE" >
 				
 				<label class="row-label">CheckType</label>
                <div class="row-input required">
                    <input type="text" name="key" id="j_checkTyp_edit_checkType" value="${param.key}" data-rule="required">
                </div>
 				<label class="row-label">Name</label>
                <div class="row-input required">
                    <input type="text" name="value" id="j_checkTyp_edit_name" value="${param.value}" data-rule="required">
                </div>
                
                <label class="row-label">User ID</label>
                <div class="row-input">
                    <input type="text" name="keyExplain" id="j_checkTyp_edit_UserID" value="${param.keyExplain}" onchange="getUname()">
                </div>
                <label class="row-label">User Name</label>
                <div class="row-input">
                    <input type="text" name="username" id="j_checkTyp_edit_username" value="" readonly="">
                </div>
                
				<label class="row-label">Approve</label>
                <div class="row-input required">
                    <select name="valueExplain" data-toggle="selectpicker" id="j_checkTyp_edit_Approve" data-rule="required" data-width="100%"  >
                         <option value="" selected></option>
                    </select>
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