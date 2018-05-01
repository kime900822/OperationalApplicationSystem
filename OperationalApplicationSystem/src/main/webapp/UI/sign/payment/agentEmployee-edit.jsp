<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script type="text/javascript">


</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
        <form action="setAgentEmployee.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp">
            <div class="bjui-row col-2">
            
                <input type="hidden" name="key" id="j_agentEmployee_edit_agentid" value="${user.uid}"  >
 				<input type="hidden" name="keyName" id="j_agentEmployee_edit_agentid" value="${user.name}"  >
                
                <label class="row-label">User ID</label>
                <div class="row-input">
                    			<input type="text" name="value" id="j_agentEmployee_edit_uid" value=""  data-rule="required" data-toggle="findgrid" size="13" data-options="{
            group: '',
            include: 'value:uid,valueName:name',
            dialogOptions: {title:'选择代理人'},
            empty:false,
            gridOptions: {
                local: 'local',
                dataUrl: 'getUser4Find.action',
                columns: [
                    {name:'uid', label:'SupplierCode', width:100},
                    {name:'name',width:200,label:'Name'}
                ]
            }
        }" placeholder="点放大镜按钮查找" data-rule="required" >
                </div>
                <label class="row-label">User Name</label>
                <div class="row-input">
                    <input type="text" name="valueName" id="j_agentEmployee_edit_name" size="13" value="" readonly="" >
                </div>
                <label class="row-label">Begin Date</label>
                <div class="row-input">
                    <input type="text" name="keyExplain" id="j_agentEmployee_edit_beginDate" size="13" value=""  placeholder="点击选择日期"  data-toggle="datepicker"  data-rule="required">
                </div>
                <label class="row-label">End Date</label>
                <div class="row-input">
                    <input type="text" name="valueExplain" id="j_agentEmployee_edit_endDate" size="13" value=""   placeholder="点击选择日期"  data-toggle="datepicker"   data-rule="required">
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