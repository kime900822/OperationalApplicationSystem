<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <script type="text/javascript">
if('${param.id}'!=null&&'${param.id}'!=''){
	BJUI.ajax('doajax', {
	    url: 'getEditorByID.action',
	    loadingmask: false,
	    data:{'id':'${param.id}'},
	    okCallback: function(json, options) {
	    	$.CurrentDialog.find('#j_form_id').val(json.id);
	    	$.CurrentDialog.find('#j_form_title').val(json.title);
	    	KindEditor.html("#j_form_content",json.content)
	    	//$.CurrentDialog.find('#j_form_content').val(json.content);
	    }
	})		
}

		 
		


</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
        <form action="saveEditor.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-3">
            	<input type="hidden" name="id" id="j_form_id" value="" />
                <label class="row-label">title</label>
                <div class="row-input required">
                    <input type="text" name="title" id="j_form_title" value="" data-rule="required" style="width: 500px;">
                </div><br>         
                <div class="row-input">
                    <textarea name="content" id="j_form_content" class="j-content" style="width: 700px;" data-toggle="kindeditor" data-minheight="200" data-uploadjson="../uploadJson" data-filemanagerjson="../fileManagerJson" >

                    </textarea>
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