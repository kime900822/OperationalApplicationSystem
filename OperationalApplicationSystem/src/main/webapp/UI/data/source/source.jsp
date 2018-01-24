<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-user-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Resource Import',
        showToolbar: true,
        toolbarItem: 'refresh,|,import,export',
        dataUrl: 'getSource.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: false,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'data/source/source-import.html', width:500, height:300, title:'Import Source'}},
        exportOption: {type:'file', options:{url:'exportSourceExcel.action', loadingmask:false}}
    }">
        <thead>
            <tr>
           		<th data-options="{name:'Project_Code',width:150,align:'left',finalWidth:'true'}" >Project_Code</th>
            	<th data-options="{name:'Type',width:150,align:'left',finalWidth:'true'}" >Type</th>
				<th data-options="{name:'Labour_Cost_50',width:150,align:'left',finalWidth:'true'}">Labour_Cost_50</th>
				<th data-options="{name:'Material_50',width:150,align:'left',finalWidth:'true'}">Material_50</th>
				<th data-options="{name:'Consumption_50',width:150,align:'left',finalWidth:'true'}">Consumption_50</th>
				<th data-options="{name:'Goods_Transport_50',width:150,align:'left',finalWidth:'true'}">Goods_Transport_50</th>
				<th data-options="{name:'Other_50',width:150,align:'left',finalWidth:'true'}">Other_50</th>
				<th data-options="{name:'İndirect_Labour_Cost_50',width:150,align:'left',finalWidth:'true'}">İndirect_Labour_Cost_50</th>
				<th data-options="{name:'General_MFG_Expenses_50',width:150,align:'left',finalWidth:'true'}">General_MFG_Expenses_50</th>
				<th data-options="{name:'Research_Development_50',width:150,align:'left',finalWidth:'true'}">Research_Development_50</th>
				<th data-options="{name:'Factory_Amortization_50',width:150,align:'left',finalWidth:'true'}">Factory_Amortization_50</th>
				<th data-options="{name:'Sales_Marketing_50',width:150,align:'left',finalWidth:'true'}">Sales_Marketing_50</th>
				<th data-options="{name:'General_Administration_Cost_50',width:150,align:'left',finalWidth:'true'}">General_Administration_Cost_50</th>
				
				<th data-options="{name:'Labour_Cost_60',width:150,align:'left',finalWidth:'true'}">Labour_Cost_60</th>
				<th data-options="{name:'Material_60',width:150,align:'left',finalWidth:'true'}">Material_60</th>
				<th data-options="{name:'Consumption_60',width:150,align:'left',finalWidth:'true'}">Consumption_60</th>
				<th data-options="{name:'Goods_Transport_60',width:150,align:'left',finalWidth:'true'}">Goods_Transport_60</th>
				<th data-options="{name:'Other_60',width:150,align:'left',finalWidth:'true'}">Other_60</th>
				<th data-options="{name:'İndirect_Labour_Cost_60',width:150,align:'left',finalWidth:'true'}">İndirect_Labour_Cost_60</th>
				<th data-options="{name:'General_MFG_Expenses_60',width:150,align:'left',finalWidth:'true'}">General_MFG_Expenses_60</th>
				<th data-options="{name:'Research_Development_60',width:150,align:'left',finalWidth:'true'}">Research_Development_60</th>
				<th data-options="{name:'Factory_Amortization_60',width:150,align:'left',finalWidth:'true'}">Factory_Amortization_60</th>
				<th data-options="{name:'Sales_Marketing_60',width:150,align:'left',finalWidth:'true'}">Sales_Marketing_60</th>
				<th data-options="{name:'General_Administration_Cost_60',width:150,align:'left',finalWidth:'true'}">General_Administration_Cost_60</th>
				
				<th data-options="{name:'Labour_Cost_70',width:150,align:'left',finalWidth:'true'}">Labour_Cost_70</th>
				<th data-options="{name:'Material_70',width:150,align:'left',finalWidth:'true'}">Material_70</th>
				<th data-options="{name:'Consumption_70',width:150,align:'left',finalWidth:'true'}">Consumption_70</th>
				<th data-options="{name:'Goods_Transport_70',width:150,align:'left',finalWidth:'true'}">Goods_Transport_70</th>
				<th data-options="{name:'Other_70',width:150,align:'left',finalWidth:'true'}">Other_70</th>
				<th data-options="{name:'İndirect_Labour_Cost_70',width:150,align:'left',finalWidth:'true'}">İndirect_Labour_Cost_70</th>
				<th data-options="{name:'General_MFG_Expenses_70',width:150,align:'left',finalWidth:'true'}">General_MFG_Expenses_70</th>
				<th data-options="{name:'Research_Development_70',width:150,align:'left',finalWidth:'true'}">Research_Development_70</th>
				<th data-options="{name:'Factory_Amortization_70',width:150,align:'left',finalWidth:'true'}">Factory_Amortization_70</th>
				<th data-options="{name:'Sales_Marketing_70',width:150,align:'left',finalWidth:'true'}">Sales_Marketing_70</th>
				<th data-options="{name:'General_Administration_Cost_70',width:150,align:'left',finalWidth:'true'}">General_Administration_Cost_70</th>
				
            </tr>
        </thead>
    </table>
</div>