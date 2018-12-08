<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <script type="text/javascript">
		
 


</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
        <form action="customs/importsAndExportsEditNo.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-1">
            	<input type="hidden" name="id" value="${param.id}" />
            	<label class="row-label">Order Number：</label>
                <div class="row-input required">
                    <input type="text" name="orderNjmber" value="${param.orderNumber}" data-rule="required" disabled="disabled" style="width: 100px;">
                </div>         
                <label class="row-label">料件序号</label>
                <div class="row-input required">
                    <input type="text" name="no" value="${param.no}" data-rule="required" style="width: 100px;">
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