<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">

</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
	<table>${param.week}</table>
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
            	<th data-options="{name:'code',width:150,align:'center',finalWidth:'true'}" >Sequence No.</th>
            	<th data-options="{name:'applicant',width:150,align:'center',finalWidth:'true'}" >Applicant</th>
            	<th data-options="{name:'did',width:150,align:'center',finalWidth:'true'}" >Business Unit</th>
            	<th data-options="{name:'amountInFigures',width:150,align:'center',finalWidth:'true'}" >Total Amount</th>
            	<th data-options="{name:'usageDescription',width:150,align:'center',finalWidth:'true'}" >Usage Description</th>
            	<th data-options="{name:'amount',width:150,align:'center',finalWidth:'true'}" >PO Amount</th>
            	<th data-options="{name:'supplierCode',width:150,align:'center',finalWidth:'true'}" >Supplier Code</th>
            	<th data-options="{name:'beneficiaryE',width:150,align:'center',finalWidth:'true'}" >Ename</th>    
			</tr>
        </thead>
    </table>
</div>