<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script type="text/javascript">


		function getUname(a,name){
			var id=$.CurrentDialog.find(a).val();
			if(id=='Dept. Head'){
				$.CurrentDialog.find('#'+name).val('Dept. Head'); 			
			}else{	
				BJUI.ajax('doajax', {
				    url: 'getUserByID.action',
				    loadingmask: true,
				    data:{uid:id},
				    okCallback: function(json, options) {
				    	if(json.length>0){
				    		$.CurrentDialog.find('#'+name).val(json[0].name); 
				    	}                         
				    	else{
				    		BJUI.alertmsg('error','userid不存在'); 
				    	}
				    }
				})			
			}
		}
		
		


</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
        <form action="editApprove.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-2">
                

                <input type="hidden" name="id" id="j_approveList_edit_id" value="${param.id}"  >

				<label class="row-label">TYPE</label>
                <div class="row-input required">
                 	<input type="text" name="type" id="j_approveList_edit_type" value="${param.type}" >
                </div>
                <label class="row-label">NAME</label>
                <div class="row-input required">
                    <input type="text" name="name" id="j_approveList_edit_name" value="${param.name}" >
                </div>
                <label class="row-label">User ID</label>
                <div class="row-input required">
                    <input type="text" name="uid" id="j_approveList_edit_uid" value="${param.uid}" onchange="getUname(this,'j_approveList_edit_uname')">
                </div>
                <label class="row-label">User</label>
                <div class="row-input required">
                    <input type="text" name="uname" id="j_approveList_edit_uname" value="${param.uname}" readonly="">
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