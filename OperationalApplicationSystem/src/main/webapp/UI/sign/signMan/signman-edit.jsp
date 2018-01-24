<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script type="text/javascript">

		$(function(){
		
			
			BJUI.ajax('doajax', {
			    url: 'getALLSign.action',
			    loadingmask: false,
			    okCallback: function(json, options) {
	                $.each(json, function (i, item) {
	                    $.CurrentDialog.find('#j_signman_edit_type').append("<option value='" + item.value + "'>" + item.key + "</option>")           
	                })
	                $.CurrentDialog.find('#j_signman_edit_type').selectpicker('val','${param.type}');
	                $.CurrentDialog.find('#j_signman_edit_type').selectpicker('refresh');
			    }
			})	
			
			BJUI.ajax('doajax', {
			    url: 'getAllDepartmentOfSign.action',
			    loadingmask: false,
			    okCallback: function(json, options) {
	                $.each(json, function (i, item) {
	                    $.CurrentDialog.find('#j_signman_edit_departmenmt').append("<option value='" + item.did + "'>" + item.did + "</option>")           
	                })
	                $.CurrentDialog.find('#j_signman_edit_departmenmt').selectpicker('val','${param.did}');
	                $.CurrentDialog.find('#j_signman_edit_departmenmt').selectpicker('refresh');
			    }
			})	
			
		})

		function getUname(){
			var id=$.CurrentDialog.find('#j_signman_edit_uid').val();
			BJUI.ajax('doajax', {
			    url: 'getUserByID.action',
			    loadingmask: true,
			    data:{uid:id},
			    okCallback: function(json, options) {
			    	if(json.length>0){
			    		$.CurrentDialog.find('#j_signman_edit_uname').val(json[0].name); 
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
        <form action="editSignMan.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-2">
                

                <input type="hidden" name="sid" id="j_signman_edit_sid" value="${param.sid}"  >

				<label class="row-label">Type</label>
                <div class="row-input required">
                    <select name="type" data-toggle="selectpicker" id="j_signman_edit_type" data-rule="required" data-width="100%"  >
                         <option value="" selected></option>
                    </select>
                </div>
                
                <label class="row-label">UserID</label>
                <div class="row-input required">
                    <input type="text" name="uid" id="j_signman_edit_uid" value="${param.uid}" data-rule="required" onchange="getUname()">
                </div>
                <label class="row-label">Name</label>
                <div class="row-input required">
                    <input type="text" name="uname" id="j_signman_edit_uname" value="${param.uname}" data-rule="required" readonly="">
                </div>
                <label class="row-label">DepartmentID</label>
                <div class="row-input required">
                     <select name="did" data-toggle="selectpicker" id="j_signman_edit_departmenmt" data-rule="required" data-width="100%"  >
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