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


function datagrid_paymentSubject() {
    return [{'1':'Fixed Asset 固定资产'},{'2':'Raw Material 原材料'},{'3':'Consumable 消耗品'},{'4':'Subcontractor 外包'},{'5':'Service 服务'},{'6':'Petty Cash备用金'},{'7':'Other 其他'}]
}

function datagrid_paymentState() {
    return [{'0':'Submit Required'},{'1':'Manager Approval'},{'2':'Audit Approval'},{'3':'Audit Rejected'},{'4':'Finance Approval'},{'5':'Invalid'},{'6':'Finance Rejected'},{'7':'GM Approval'},{'8':'Payment Completed'}]
}


function datagrid_urgent() {
    return [{'0':''},{'1':'Y'}]
}

$(function(){
	$.CurrentNavtab.find("#q_payment_urgent").on('ifChecked',function(){
		$.CurrentNavtab.find("#q_payment_urgent").val("1");
	})
	$.CurrentNavtab.find("#q_payment_urgent").on('ifUnchecked',function(){
		$.CurrentNavtab.find("#q_payment_urgent").val("0");
	})	
	
})	







</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-payment-user-filter')}" id="datagrid-payment-user-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="150px">
        		<span>Application Date：</span>
        		</td>
        		<td width="220px">
            	<input type="text" name="applicationDate_f"  data-nobtn="true"  id="q_payment_applicationDate_f" value="" data-toggle="datepicker" size="9" data-rule="date">to:
            	<input type="text" name="applicationDate_t"  data-nobtn="true"  id="q_payment_applicationDate_t" value="" data-toggle="datepicker" size="9" data-rule="date">
        		</td>
        		<td width="150px">
        		<span>Sequential Code：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="code" value="" id="q_payment_code" size="15">
        		</td>
	        	<td>
        		<span>Status</span>
        		</td>
        		<td>
            	<select name="state" data-toggle="selectpicker" id="q_payment_state"  data-width="80px">
	              <option value="" >all</option>
	              <option value="0">Submit Required</option>
	              <option value="1">Manager Approval</option>
	              <option value="2">Audit Approval</option>
	              <option value="3">Audit Rejected</option>
	              <option value="4">Finance Approval</option>
	              <option value="5">Invalid</option>
	              <option value="6">Finance Rejected</option>
	              <option value="7">GM Approval</option>
	              <option value="8">Payment Completed</option>
            	</select>
        		</td>
        		<td>
        			<span>Payment Method</span>
        		</td>
        		<td>
	   				<select name="paymentTerm" data-toggle="selectpicker" id="j_payment_paymentTerm"  data-rule="required" data-width="190px" onchange="changePaymentTerm();" >
	                    <option value=""></option>
	                    <option value="1">Advance 预付款</option>
	                    <option value="2">Payment at sight 见票即付</option>
	                    <option value="3">Upon receiving 收货后</option>
	                    <option value="4">Upon Approval 验收后</option>
	                    <option value="5">Upon invoice 见票后</option>
	                    <option value="6">Other 其他</option>
	               	</select>
        		</td>
        	</tr>    
        	<tr>
        		<td colspan="6" height="10px"></td>
        	</tr>
        	<tr>
        		<td>
        		<span>Payment Subject：</span>
        		</td>
        		<td>
            	<select name="paymentSubject" data-toggle="selectpicker" id="q_payment_paymentSubject"  data-width="200px">
	              <option value=""></option>
	              <option value="1">Fixed Asset 固定资产</option>
	              <option value="2">Raw Material 原材料</option>
	              <option value="3">Consumable 消耗品</option>
	              <option value="4">Subcontractor 外包</option>
	              <option value="5">Service 服务</option>
	              <option value="6">Petty Cash备用金</option>
	              <option value="7">Other 其他</option>
            	</select>
        		</td>
        		<td width="80px">
        		<span>Urgent：</span>
        		</td>
        		<td width="80px">
            		<select name="urgent" data-toggle="selectpicker" id="q_payment_urgent"  data-width="80px">
		              <option value=""></option>
		              <option value="1">Y</option>
		              <option value="0">N</option>
	            	</select>
         		</td>
        		<td colspan="2" align="center">
        		<div class="btn-group">
                <button type="submit" class="btn-green" data-icon="search">Search</button>
                <button type="reset" class="btn-orange" data-icon="times">Reset</button>
            	</div>
        		</td>
        		<td colspan="2"></td>
        	</tr>    
        </table>
            
                       
			
            
            
        </div>

    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-payment-user-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Payment management',
        dataUrl: 'getPayment.action?queryType=user',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'export',
        editMode: {navtab:{width:'830',height:800,title:'Edit Payment',mask:true,fresh:true}},
        delUrl:'deleteUser.action',
        editUrl: 'sign/payment/paymentform.jsp',
        paging: {pageSize:30, pageCurrent:1},
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false,
        exportOption: {type:'file', options:{url:'exportPaymentExcel.action?queryType=user', loadingmask:false,queryForm:$.CurrentNavtab.find('#datagrid-payment-user-query')}}
    }">
        <thead>
            <tr>
            	<th data-options="{render:datagrid_tree_operation,align:'center'}">Operation</th>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
            	<th data-options="{name:'applicationDate',width:150,align:'center',finalWidth:'true'}" >Application Date</th>
				<th data-options="{name:'state',width:150,align:'center',finalWidth:'true',type:'select', items:datagrid_paymentState}">Approval Status</th>
				<th data-options="{name:'code',width:150,align:'center',finalWidth:'true'}">Sequential Code</th>
				<th data-options="{name:'urgent',width:60,align:'center' ,finalWidth:'true',type:'select', items:datagrid_urgent}">Urgent</th>
				<th data-options="{name:'paymentSubject',width:200,align:'center',finalWidth:'true',type:'select', items:datagrid_paymentSubject}">Payment Subject</th>
				<th data-options="{name:'currency_1',width:80,align:'right',finalWidth:'true'}">Currency</th>
				<th data-options="{name:'amountInFigures',width:120,align:'right',finalWidth:'true'}">Amount</th>
				<th data-options="{name:'usageDescription',width:400,align:'left',finalWidth:'true'}">Usage Description</th>            
			</tr>
        </thead>
    </table>
</div>