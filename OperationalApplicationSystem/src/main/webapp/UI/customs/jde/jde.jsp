<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">



</script>

<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-customs-jde-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'JDE Import',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh,|,import',
        dataUrl: 'customs/queryCustomsJDE.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'customs/jde/jde-import.html', width:500, height:300, title:'Import JDE'}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
				<th data-options="{name:'businessUnit',width:120,align:'left',finalWidth:'true'}">Business Unit</th>
				<th data-options="{name:'orTy',width:50,align:'left',finalWidth:'true'}">Or Ty</th>
				<th data-options="{name:'orderNumber',width:200,align:'left',finalWidth:'true'}">Order Number</th>
				<th data-options="{name:'orderDate',width:200,align:'left',finalWidth:'true'}">Order Date</th>
				<th data-options="{name:'location',width:200,align:'left',finalWidth:'true'}">Location</th>
				<th data-options="{name:'shortItemNo',width:200,align:'left',finalWidth:'true'}">Short Item No</th>
				<th data-options="{name:'cimtasLongItemNo',width:200,align:'left',finalWidth:'true'}">Cimtas Long Item No</th>
				<th data-options="{name:'doTy',width:200,align:'left',finalWidth:'true'}">Do Ty</th>
				<th data-options="{name:'transactionExplanation',width:200,align:'left',finalWidth:'true'}">Transaction Explanation</th>
				<th data-options="{name:'transQTY',width:200,align:'left',finalWidth:'true'}">Trans QTY</th>
				<th data-options="{name:'extendedCostPrice',width:200,align:'left',finalWidth:'true'}">Extended Cost/Price</th>
				<th data-options="{name:'glDate',width:200,align:'left',finalWidth:'true'}">G/L Date</th>
				<th data-options="{name:'documentNumber',width:200,align:'left',finalWidth:'true'}">Document Number</th>
				<th data-options="{name:'lotStatCode',width:200,align:'left',finalWidth:'true'}">Lot Stat Code (F4111)</th>
				<th data-options="{name:'longProjectNo',width:200,align:'left',finalWidth:'true'}">Long Project No</th>
				<th data-options="{name:'lotNumber',width:200,align:'left',finalWidth:'true'}">Lot Number</th>
				<th data-options="{name:'userID',width:200,align:'left',finalWidth:'true'}">User ID</th>
				<th data-options="{name:'materialLongDescription',width:200,align:'left',finalWidth:'true'}">Material Long Description</th>
				<th data-options="{name:'importNo',width:200,align:'left',finalWidth:'true'}">Import No</th>
				<th data-options="{name:'heatNo',width:200,align:'left',finalWidth:'true'}">Heat No</th>
				<th data-options="{name:'NCRNo',width:200,align:'left',finalWidth:'true'}">NCR No</th>
				<th data-options="{name:'note',width:200,align:'left',finalWidth:'true'}">NOTE</th>
				<th data-options="{name:'weight',width:200,align:'left',finalWidth:'true'}">Weight(kg)</th>
				<th data-options="{name:'purchaseOrderLineNumber',width:200,align:'left',finalWidth:'true'}">Purchase Order Line Number</th>
				<th data-options="{name:'note1',width:200,align:'left',finalWidth:'true'}">Note</th>
				<th data-options="{name:'operationDate',width:200,align:'left',finalWidth:'true'}">操作日期</th>
				
            </tr>
        </thead>
    </table>
</div>