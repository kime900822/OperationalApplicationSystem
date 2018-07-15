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
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-payment-admin-filter')}" id="datagrid-payment-admin-query">
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
        	<tr>
        	<td colspan="8" height="10px"></td>
        	</tr>   
        	<tr>
        	    <td>
        			<span>Payment Method:</span>
        		</td>
        		<td>
	   				<select name="paymentTerm" data-toggle="selectpicker" id="j_payment_paymentTerm"   data-width="200px" onchange="changePaymentTerm();" >
	                    <option value="" >all</option>
	                    <option value="1">Advance 预付款</option>
	                    <option value="2">Payment at sight 见票即付</option>
	                    <option value="3">Upon receiving 收货后</option>
	                    <option value="4">Upon Approval 验收后</option>
	                    <option value="5">Upon invoice 见票后</option>
	                    <option value="6">Other 其他</option>
	               	</select>
        		</td>
        		<td colspan="6"></td>
        	</tr> 
        </table>
            
                       
			
            
            
        </div>

    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-payment-admin-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Payment_Visit management',
        dataUrl: 'getPaymentVisit.action?queryType=admin',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'export',
        editMode: {navtab:{width:'830',height:800,title:'Edit Payment',mask:true,fresh:true}},
        editUrl: 'sign/payment/visit/payment_visit_form.jsp?viewtype=admin',
        paging: {pageSize:30, pageCurrent:1},
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false,
        exportOption: {type:'file', options:{url:'exportPaymentExcel.action?queryType=admin', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-payment-admin-query')}}
    }">
        <thead>
            <tr>
            	<th data-options="{render:datagrid_tree_operation,align:'center'}">Operation</th>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
            	<th data-options="{name:'applicantDate',width:150,align:'center',finalWidth:'true'}" >Applicant Date</th>
				<th data-options="{name:'referenceNo',width:150,align:'center',finalWidth:'true'}">ReferenceNo</th>
				<th data-options="{name:'uId',width:150,align:'center',finalWidth:'true'}">Cimtas ID</th>
				<th data-options="{name:'uName',width:150,align:'center',finalWidth:'true'}">User Name</th>
			</tr>
        </thead>
    </table>
</div>