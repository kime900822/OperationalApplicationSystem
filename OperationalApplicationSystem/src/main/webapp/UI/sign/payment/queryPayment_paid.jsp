<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
$(function() {
	
	BJUI.ajax('doajax', {
	    url: 'getPaidWeek.action',
	    loadingmask: false,
	    okCallback: function(json, options) {
            $.each(json, function (i, item) {
                $.CurrentNavtab.find('#q_paid_week').append("<option value='" + item.ids + "'>" + item.week + "</option>")           
            })
            $.CurrentNavtab.find('#q_paid_week').selectpicker('refresh');
	    }
	})	
	
})
</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-paymentWeek-payment-filter')}" id="datagrid-paymentWeek-payment-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>  
        	<tr>
        		<td>
        		<span>Week：</span>
        		</td>
        		<td>
            	<select name="week" data-toggle="selectpicker" id="q_paid_week"  data-width="200px">
	              <option value=""></option>
            	</select>&nbsp;&nbsp;&nbsp;
        		</td>
        		<td align="center">
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
    <table class="table table-bordered" id="datagrid-paymentWeek-payment-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'PaymentWeek management',
        toolbarItem: 'export',
        dataUrl: 'getPaymentWeek.action',
        dataType: 'local',
        paging: false,
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false,
        exportOption: {type:'file', options:{url:'exportPaymentWeekExcel.action', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-paymentWeek-payment-query')}}
        
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