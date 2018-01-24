<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script type="text/javascript">
 $(function(){
	 
	 getUnameOfManager();
	 getUnameOfSign();
 })
 
 
		function getUnameOfManager(){
			var id=$.CurrentDialog.find('#j_signman4manager_edit_uid').val();
			BJUI.ajax('doajax', {
			    url: 'getUserByID.action',
			    loadingmask: true,
			    data:{uid:id},
			    okCallback: function(json, options) {
			    	if(json.length>0){
			    		$.CurrentDialog.find('#j_signman4manager_edit_uname').val(json[0].name); 
			    	}                         
			    	else{
			    		BJUI.alertmsg('error','userid不存在'); 
			    	}
			    }
			})
			
		}
		
		function getUnameOfSign(){
			var id=$.CurrentDialog.find('#j_signman4manager_edit_signid').val();
			BJUI.ajax('doajax', {
			    url: 'getUserByID.action',
			    loadingmask: true,
			    data:{uid:id},
			    okCallback: function(json, options) {
			    	if(json.length>0){
			    		$.CurrentDialog.find('#j_signman4manager_edit_signname').val(json[0].name); 
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
        <form action="modeSignMan4Management.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-2">
                

                <input type="hidden" name="id" id="j_signman4manager_edit_id" value="${param.Id}"  >
				<input type="hidden" name="type" id="j_signman4manager_edit_type" value="SignMan4Manager"  >
                <label class="row-label">UserID</label>
                <div class="row-input required">
                    <input type="text" name="key" id="j_signman4manager_edit_uid" value="${param.key}" data-rule="required" onchange="getUnameOfManager()">
                </div>
                <label class="row-label">Name</label>
                <div class="row-input required">
                    <input type="text" name="keyExplain" id="j_signman4manager_edit_uname" value="" data-rule="required" readonly="">
                </div>
                <label class="row-label">SignID</label>
                <div class="row-input required">
                    <input type="text" name="value" id="j_signman4manager_edit_signid" value="${param.value}" data-rule="required" onchange="getUnameOfSign()">
                </div>
                <label class="row-label">SignName</label>
                <div class="row-input required">
                    <input type="text" name="valueExplain"  id="j_signman4manager_edit_signname" value="" data-rule="required" readonly="">
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