<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-user-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'IrrecoverableVat Import',
        showToolbar: true,
        toolbarItem: 'refresh,|,import,export',
        dataUrl: 'getIrrecoverableVat.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: false,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'data/irrecoverableVat/irrecoverableVat-import.html', width:500, height:300, title:'Import Instruction'}},
        exportOption: {type:'file', options:{url:'exportIrrecoverableVatExcel.action', loadingmask:false}}
    }">
        <thead>
            <tr>
           		<th data-options="{name:'Short_Item_No',width:150,align:'left',finalWidth:'true'}" >Short_Item_No</th>
            	<th data-options="{name:'Batch_Number',width:150,align:'left',finalWidth:'true'}" >Batch_Number</th>
				<th data-options="{name:'Sub_ledger',width:150,align:'left',finalWidth:'true'}">Sub_ledger</th>
				<th data-options="{name:'Purchase_Order',width:150,align:'left',finalWidth:'true'}">Purchase_Order</th>
				<th data-options="{name:'Managrl_Code_1',width:150,align:'left',finalWidth:'true'}">Managrl_Code_1</th>
				<th data-options="{name:'Account_Number',width:150,align:'left',finalWidth:'true'}">Account_Number</th>
				<th data-options="{name:'Account_Description',width:250,align:'left',finalWidth:'true'}">Account_Description</th>
				<th data-options="{name:'Do_Ty',width:150,align:'left',finalWidth:'true'}">Do_Ty</th>
				<th data-options="{name:'Doc_Number',width:150,align:'left',finalWidth:'true'}">Doc_Number</th>
				<th data-options="{name:'Doc_Co',width:150,align:'left',finalWidth:'true'}">Doc_Co</th>
				<th data-options="{name:'GL_Date',width:150,align:'left',finalWidth:'true'}">G/L_Date</th>
				<th data-options="{name:'Address_Number',width:150,align:'left',finalWidth:'true'}">Address_Number</th>
				<th data-options="{name:'Explanation',width:300,align:'left',finalWidth:'true'}">Explanation</th>
				<th data-options="{name:'LT_1_Amount',width:150,align:'left',finalWidth:'true'}">LT_1_Amount</th>
            </tr>
        </thead>
    </table>
</div>