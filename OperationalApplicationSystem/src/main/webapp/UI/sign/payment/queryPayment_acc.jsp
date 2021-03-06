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
    return [{'1':'Fixed Asset 固定资产'},{'2':'Raw Material 原材料'},{'3':'Consumable 消耗品'},{'4':'Subcontractor 外包'},{'5':'Service 服务'},{'6':'Petty Cash备用金'},{'7':'Other 其他'},{'8':'Travel 差旅费'}]
}

function datagrid_urgent() {
    return [{'0':''},{'1':'Y'}]
}


</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-payment-acc-filter')}" id="datagrid-payment-acc-query">
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
        		<span>Cimtas ID：</span>
        		</td>
        		<td width="180px">
        		<input type="text" name="UID" value="" id="q_payment_UID" size="15">
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
        	</tr>    
        	<tr>
        		<td colspan="8" height="10px"></td>
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
	              <option value="8">Travel 差旅费</option>
	              <option value="7">Other 其他</option>
            	</select>
        		</td>
        		<td>
        		<span>BU NO：</span>
        		</td>
        		<td>
            	<input type="text" name="departmentID" class="form-control" size="15" data-rule="number" >
        		</td>
        		<td>
        		<span>Status</span>
        		</td>
        		<td>
            	<select name="state" data-toggle="selectpicker" id="q_payment_state"  data-width="80px">
	              <option value="" >all</option>
	              <option value="0">Submit Required</option>
	              <option value="1">Manager Approval</option>
	              <option value="9">Manager2 Approval</option>
	              <option value="2">Audit Approval</option>
	              <option value="3">Audit Rejected</option>
	              <option value="4">Finance Approval</option>
	              <option value="5">Invalid</option>
	              <option value="6">Finance Rejected</option>
	              <option value="7">GM Approval</option>
	              <option value="8">Payment Completed</option>
            	</select>
        		</td>
        		<td colspan="2" align="center">
        		<div class="btn-group">
                <button type="submit" class="btn-green" data-icon="search">Search</button>
                <button type="reset" class="btn-orange" data-icon="times">Reset</button>
            	</div>
        		</td>

        	</tr>    
        </table>
            
                       
			
            
            
        </div>

    </fieldset>
</form>

</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-payment-acc-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Payment management',
        dataUrl: 'getPayment.action?queryType=acc',
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
            	<th data-options="{name:'state',width:150,align:'center',finalWidth:'true',type:'select', items:datagrid_paymentState}">Approval Status</th>
            	<th data-options="{name:'UID',width:150,align:'center',finalWidth:'true'}">Cimtas ID</th>
				<th data-options="{name:'UName',width:150,align:'center',finalWidth:'true'}">User Name</th>
				<th data-options="{name:'code',width:150,align:'center',finalWidth:'true'}">Sequential Code</th>
				<th data-options="{name:'urgent',width:60,align:'center' ,finalWidth:'true',type:'select', items:datagrid_urgent}">Urgent</th>
				<th data-options="{name:'paymentSubject',width:200,align:'center',finalWidth:'true',type:'select', items:datagrid_paymentSubject}">Payment Subject</th>
				<th data-options="{name:'currency_1',width:80,align:'right',finalWidth:'true'}">Currency</th>
				<th data-options="{name:'amountInFigures',width:120,align:'right',finalWidth:'true'}">Amount</th>
				<th data-options="{name:'usageDescription',width:400,align:'left',finalWidth:'true'}">Usage Description</th>            </tr>
        </thead>
    </table>
</div>