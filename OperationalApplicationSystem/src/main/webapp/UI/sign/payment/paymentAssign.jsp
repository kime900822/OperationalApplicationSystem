<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script type="text/javascript">
 
 
		$(function(){			
			BJUI.ajax('doajax', {
			    url: 'getALLAcc.action',
			    loadingmask: false,
			    okCallback: function(json, options) {
	                $.each(json, function (i, item) {
	                    $.CurrentDialog.find('#j_assign_edit_uid').append("<option value='" + item.value + "'>" + item.value + "</option>")           
	                })
	                $.CurrentDialog.find('#j_assign_edit_uid').selectpicker('refresh');
			    }
			})	
			
		
		})

		
		
		 
		function getUname(){
			var id=$.CurrentDialog.find('#j_assign_edit_uid').val();
			BJUI.ajax('doajax', {
			    url: 'getUserByID.action',
			    loadingmask: true,
			    data:{uid:id},
			    okCallback: function(json, options) {
			    	if(json.length>0){
			    		$.CurrentDialog.find('#j_assign_edit_uname').val(json[0].name); 
			    	}                         
			    	else{
			    		BJUI.alertmsg('error', 'userid不存在'); 
			    	}
			    }
			})
			
		}
		
		
		
		function assign(){
			var uid=$.CurrentDialog.find('#j_assign_edit_uid').val();
			var uname=$.CurrentDialog.find('#j_assign_edit_uname').val();
			BJUI.ajax('doajax', {
			    url: 'assignPayment.action',
			    loadingmask: true,
			    data:{id:'${param.id}',documentAuditID:uid,documentAudit:uname},
			    okCallback: function(json, options) {
			    	if(json.status='200'){
			    		BJUI.dialog('closeCurrent'); 
			    	}                         
			    	else{
			    		BJUI.alertmsg('error', json.message); 
			    	}
			    }
			})
			
		}

</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
        <form class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-2">
            	<input type="hidden" name="id" id="j_assign_id"value="${param.id}">
                <label class="row-label">User</label>                
                <div class="row-input required">
                    <select name="documentAuditID" data-toggle="selectpicker" id="j_assign_edit_uid" data-rule="required" data-width="100%" onchange="getUname();" >
                         <option value="" selected></option>
                    </select>
                </div>
                <label class="row-label">Name</label>
                <div class="row-input required">
                    <input type="text" name="documentAudit" id="j_assign_edit_uname" value="" data-rule="required" readonly="">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close" >Cancle</button></li>
        <li><button type="submit" class="btn-default" data-icon="save" onclick="assign();">Save</button></li>
    </ul>
</div>