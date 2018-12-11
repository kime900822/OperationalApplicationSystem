<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <script type="text/javascript">
		
 


</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
        <form action="customs/updateMaterialMapping.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-1">
            	<input type="hidden" name="id" value="${param.id}" />
            	<label class="row-label">海关系统旧料号：</label>
                <div class="row-input required">
                    <input type="text" name="oldMaterialNo" value="${param.oldMaterialNo}" data-rule="required"  style="width: 150px;">
                </div>         
                <label class="row-label">JDE新料号</label>
                <div class="row-input required">
                    <input type="text" name="newMaterialNo" value="${param.newMaterialNo}" data-rule="required" style="width: 150px;">
                </div>         
            </div>
        </form>
    </div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">Cancel</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">Save</button></li>
    </ul>
</div>