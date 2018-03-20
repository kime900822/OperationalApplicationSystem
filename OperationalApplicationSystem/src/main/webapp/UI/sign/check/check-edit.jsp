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
        <form action="modeCheck.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-2">
                

                <input type="hidden" name="id" id="j_check_edit_sid" value="${param.id}"  >

				<label class="row-label">CheckType</label>
                <div class="row-input required">
                 	<input type="text" name="type" id="j_check_edit_type" value="${param.type}" >
                </div>
                <label class="row-label">Attachment</label>
                <div class="row-input required">
               		 <input type="checkbox" name="attachment" id="j_check_edit_attachment" data-toggle="icheck" value="1" data-label="Attachment">
                </div>
                <label class="row-label">First ID</label>
                <div class="row-input required">
                    <input type="text" name="fistUID" id="j_check_edit_fistUID" value="${param.fistUID}" onchange="getUname(this,'j_check_edit_fistUname')">
                </div>
                <label class="row-label">First name</label>
                <div class="row-input required">
                    <input type="text" name="fistUname" id="j_check_edit_fistUname" value="${param.fistUname}" readonly="">
                </div>
 				<label class="row-label">Second ID</label>
                <div class="row-input required">
                    <input type="text" name="secondUID" id="j_check_edit_secondUID" value="${param.secondUID}" onchange="getUname(this,'j_check_edit_secondUname')">
                </div>
                <label class="row-label">Second name</label>
                <div class="row-input required">
                    <input type="text" name="secondUname" id="j_check_edit_secondUname" value="${param.secondUname}" readonly="">
                </div>
                 <label class="row-label">Third=ID</label>
                <div class="row-input required">
                    <input type="text" name="thirdUID" id="j_check_edit_thirdUID" value="${param.thirdUID}" onchange="getUname(this,'j_check_edit_thirdUname')">
                </div>
                <label class="row-label">Third name</label>
                <div class="row-input required">
                    <input type="text" name="thirdUname" id="j_check_edit_thirdUname" value="${param.thirdUname}" readonly="">
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