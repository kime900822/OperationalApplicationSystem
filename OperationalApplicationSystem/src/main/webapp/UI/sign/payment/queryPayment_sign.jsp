<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
//department
//操作列
function datagrid_tree_operation() {
 var html = '<button type="button" class="btn-green" data-toggle="edit.datagrid.tr">View</button>'   
 return html
}
function datagrid_urgent() {
    return [{'0':''},{'1':'Y'}]
}

function datagrid_paymentSubject() {
    return [{'1':'Fixed Asset 固定资产'},{'2':'Raw Material 原材料'},{'3':'Consumable 消耗品'},{'4':'Subcontractor 外包'},{'5':'Service 服务'},{'6':'Petty Cash备用金'},{'7':'Other 其他'}]
}


$(function(){
	
	getAgentEmployee();
	
})

function setAgentEmployee(){
	if($.CurrentNavtab.find('#agentEmployee').html()==''){
		BJUI.dialog({
		    id:'test_dialog3',
		    url:'../UI/sign/payment/agentEmployee-edit.jsp',
		    title:'设置代理',
		    width:'600',
		    height:'250',
		    onClose:function(){
		    	getAgentEmployee();
		    }
		})
	}else{
		BJUI.alertmsg('warn', '请先删除代理信息再重新维护'); 	
	}

	
}

function getAgentEmployee(){
	BJUI.ajax('doajax', {
	    url: 'getAgentEmployee.action',
	    loadingmask: true,
	    data:{key:'${user.uid}'},	
	    okalert:false,
	    okCallback: function(json, options) {
	    	if(json.params.data!=undefined){
            	$.CurrentNavtab.find('#agentEmployee').html(json.params.data.value+"&nbsp;&nbsp;&nbsp;<br>"+json.params.data.valueName+"&nbsp;&nbsp;&nbsp;");
            	$.CurrentNavtab.find('#agentEmployeeDate').html(json.params.data.keyExplain+"&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;"+json.params.data.valueExplain);
	    	}

	    }
	});		
}

function deleteAgentEmployee(){
	BJUI.alertmsg('confirm', '确定删除代理信息?', {
	    okCall: function() {
	    	BJUI.ajax('doajax', {
	    	    url: 'deleteAgentEmployee.action',
	    	    loadingmask: true,
	    	    data:{key:'${user.uid}'},	    
	    	    okCallback: function(json, options) {
	                if(json.status='200'){ 
	                	BJUI.alertmsg('info', json.message); 
	                	 $.CurrentNavtab.find('#agentEmployee').html('');
	                	 $.CurrentNavtab.find('#agentEmployeeDate').html('');
	                }else{
	                	BJUI.alertmsg('error', json.message); 
	                }
	    	    }
	    	})
	    }
	})
	
}

</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<table>
<tr>
	<td>
		<button id="setAgentEmployee" class="btn-default" onclick="setAgentEmployee()">设置代理</button>&nbsp;&nbsp;&nbsp;<br>
		<button id="deleteAgentEmployee" class="btn-default" onclick="deleteAgentEmployee()">删除代理</button>
	</td>
	<td>
		<label>代理人工号:&nbsp;&nbsp;&nbsp;<br>Agent Employee ID:&nbsp;&nbsp;&nbsp;</label>
		<input type="hidden" id="agentEmployeeID" value="">
	</td>
	<td>
	<label id="agentEmployee"></label>
	</td>
	<td>
		<label>代理时间段:&nbsp;&nbsp;&nbsp;<br>Agent Period :&nbsp;&nbsp;&nbsp;</label>
		<input type="hidden" id="agentEmployeeID" value="">
	</td>
	<td>
	<label id="agentEmployeeDate"></label>
	</td>
</tr>
</table>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-user-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Payment management',
        dataUrl: 'getPayment.action?queryType=sign',
        dataType: 'jsonp',
        editMode: {navtab:{width:'830',height:800,title:'Edit Payment',mask:true,fresh:true}},
        delUrl:'deleteUser.action',
        editUrl: 'sign/payment/paymentform_show.jsp',
        paging: {pageSize:30, pageCurrent:1},
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false
    }">
        <thead>
            <tr>
            	<th data-options="{render:datagrid_tree_operation,align:'center'}">Operation</th>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
            	<th data-options="{name:'applicationDate',width:150,align:'center',finalWidth:'true'}" >Application Date</th>
				<th data-options="{name:'code',width:150,align:'center',finalWidth:'true'}">Sequential Code</th>
				<th data-options="{name:'urgent',width:60,align:'center' ,finalWidth:'true',type:'select', items:datagrid_urgent}">Urgent</th>
				<th data-options="{name:'paymentSubject',width:200,align:'center',finalWidth:'true',type:'select', items:datagrid_paymentSubject}">Payment Subject</th>
				<th data-options="{name:'currency_1',width:80,align:'right',finalWidth:'true'}">Currency</th>
				<th data-options="{name:'amountInFigures',width:120,align:'right',finalWidth:'true'}">Amount</th>
				<th data-options="{name:'usageDescription',width:400,align:'left',finalWidth:'true'}">Usage Description</th>            </tr>
        </thead>
    </table>
</div>