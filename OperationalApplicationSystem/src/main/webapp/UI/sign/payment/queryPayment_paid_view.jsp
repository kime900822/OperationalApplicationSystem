<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">

function downPaymentPaidExcel(){
	  BJUI.ajax('ajaxdownload', {
		    url:'exportPaymentWeekExcel.action',
		    data: {week:'${param.week}'},
		    loadingmask: true
		})	
}

</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
	<table>
		<tr>
			<td width="80">${param.week}</td>
			<td><button type="button" id="payment-paid-view-print" class="btn-default" data-icon="print" onclick="downPaymentPaidExcel()" >Download</button></td>
		</tr>
	</table>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-paymentWeek-payment-paid-view-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        postData:{week:'${param.week}'},
        gridTitle : 'PaymentWeek Report',
        dataUrl: 'getPaymentWeek.action',
        local: 'local',
        paging: false,
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false
        
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
            	<th data-options="{name:'paidDate',width:150,align:'center',finalWidth:'true'}" >Actual paid Date</th>
            	<th data-options="{name:'amount',width:150,align:'center',finalWidth:'true'}" >PO Amount</th>
            	<th data-options="{name:'beneficiaryE',width:300,align:'center',finalWidth:'true'}" >Ename</th>   
            	<th data-options="{name:'usageDescription',width:400,align:'center',finalWidth:'true'}" >Usage Description</th>
				<th data-options="{name:'applicant',width:200,align:'center',finalWidth:'true'}" >Applicant</th>
				<th data-options="{name:'PONo',width:150,align:'center',finalWidth:'true'}" >PO</th>
            	<th data-options="{name:'supplierCode',width:150,align:'center',finalWidth:'true'}" >Supplier Code</th>
            	<th data-options="{name:'code',width:150,align:'center',finalWidth:'true'}" >Sequence</th>
            	<th data-options="{name:'currency',width:150,align:'center',finalWidth:'true'}" >Currency</th>
			</tr>
        </thead>
    </table>
</div>