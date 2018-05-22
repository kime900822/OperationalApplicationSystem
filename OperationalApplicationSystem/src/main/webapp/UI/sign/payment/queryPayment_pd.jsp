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

function datagrid_urgent() {
    return [{'0':''},{'1':'Y'}]
}

function datagrid_paymentState() {
    return [{'0':'Submit Required'},{'1':'Manager Approval'},{'2':'Audit Approval'},{'3':'Audit Rejected'},{'4':'Finance Approval'},{'5':'Invalid'},{'6':'Finance Rejected'},{'7':'GM Approval'},{'8':'Payment Completed'}]
}
</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-payment-pd-filter')}" id="datagrid-payment-pd-query">
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
        		<span>Status:</span>
        		</td>
        		<td width="150px">
            	<select name="state" data-toggle="selectpicker" id="q_payment_state"  data-width="150px">
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
        	</tr>    
        	<tr>
        		<td colspan="8" height="10px"></td>
        	</tr>
        	<tr>
        		<td>
        		<span>Payment Date：</span>
        		</td>
        		<td>
	           		<input type="text" name="paymentDate_f"  data-nobtn="true"  id="q_payment_paymentDate_f" value="" data-toggle="datepicker" size="9" data-rule="date">to:
	            	<input type="text" name="paymentDate_t"  data-nobtn="true"  id="q_payment_paymentDate_t" value="" data-toggle="datepicker" size="9" data-rule="date">
        		</td>
        		<td>
        		<span>BU NO：</span>
        		</td>
        		<td>
            		<input type="text" name="departmentID" class="form-control" size="15"  >
        		</td>
        		<td>
        		<span>PO Number：</span>
        		</td>
        		<td>
            		<input type="text" name="poNo" value="" id="q_payment_poNo" size="15">
        		</td>
        		<td colspan="2" align="center">
        		<div class="btn-group">
                <button type="submit" class="btn-green" data-icon="search">Search</button>
                <button type="reset" class="btn-orange" data-icon="times">Reset</button>
            	</div>
        		</td>
        	</tr>
        	<tr>	<td colspan="8" height="10px"></td></tr>
        	<tr>
        		<td>
        			<span>Payment Method：</span>
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
        		<td>
        			<span>Supplier Code：</span>
        		</td>
        		<td>
        			 <input type="text" name="supplierCode" class="form-control" size="15"  >
        		</td>
        		<td>
        			<span>SORT：</span>
        		</td>
        		<td>
        		   <select name="downloadType" data-toggle="selectpicker" id="q_payment_download_type"  data-width="150px">
		              <option value="1" selected>PO</option>
		              <option value="2">Supplier Code</option>
	            	</select>
        		</td>
        		<td colspan="2"></td>
        	</tr> 
 
        </table>
            
        </div>

    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-payment-pd-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Payment PD Report',
        dataUrl: 'getPaymentPD.action',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'export',
        paging: {pageSize:30, pageCurrent:1},
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false,
        exportOption: {type:'file', options:{url:'downloadOfPD.action', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-payment-pd-query')}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'PONo',width:100,align:'center',finalWidth:'true'}">PO</th>
            	<th data-options="{name:'amountInFigures',width:100,align:'center',finalWidth:'true'}">Total Amounts</th>
            	<th data-options="{name:'amount',width:100,align:'center',finalWidth:'true'}">Amounts</th>
            	<th data-options="{name:'currency',width:100,align:'center',finalWidth:'true'}">Currency</th>
            	<th data-options="{name:'code',width:100,align:'center',finalWidth:'true'}">Sequence</th>
            	<th data-options="{name:'applicant',width:100,align:'center',finalWidth:'true'}">Applicant</th>
            	<th data-options="{name:'supplierCode',width:100,align:'center',finalWidth:'true'}">Supplier Code</th>
            	<th data-options="{name:'beneficiaryE',width:100,align:'center',finalWidth:'true'}">Company Name-English</th>
            	<th data-options="{name:'applicationDate',width:100,align:'center',finalWidth:'true'}">Application Date</th>
            	<th data-options="{name:'requestPaymentDate',width:100,align:'center',finalWidth:'true'}">Required Payment Date</th>
            	<th data-options="{name:'contacturalPaymentDate',width:100,align:'center',finalWidth:'true'}">Contractual Payment Date</th>
            	<th data-options="{name:'paidDate',width:100,align:'center',finalWidth:'true'}">Actual Paid Date</th>
        
			</tr>
        </thead>
    </table>
</div>