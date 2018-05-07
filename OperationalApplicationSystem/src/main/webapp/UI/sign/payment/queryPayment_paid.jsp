<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">

function datagrid_tree_operation() {
	 var html = '<button type="button" class="btn-green" data-toggle="edit.datagrid.tr">View</button>'   
	 return html
}

</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">

</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-paymentWeek-payment-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'PaymentWeek management',
        dataUrl: 'getPaidWeek.action',
        dataType: 'jsonp',
        editMode: {navtab:{width:'830',height:800,title:'Payment Paid Week',mask:true,fresh:true}},
        editUrl: 'sign/payment/queryPayment_paid_view.jsp',
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
            	<th data-options="{name:'ids',width:150,align:'center',finalWidth:'true',hide:'true'}">ids</th>
            	<th data-options="{name:'week',width:150,align:'center',finalWidth:'true'}">Week</th>            	  
			</tr>
        </thead>
    </table>
</div>