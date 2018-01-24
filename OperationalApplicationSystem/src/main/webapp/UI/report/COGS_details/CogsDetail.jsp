<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-COGS_Details_RMB-filter')}">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
            <span>Month：</span>
            <input type="text" name="Month" value="" data-toggle="datepicker" data-pattern="yyyyMM" size="10">
            &nbsp;&nbsp;    
            <span>Rate：</span>
            <input type="text" name="Rate" class="form-control" size="10"> 
            &nbsp;&nbsp;    
            <span>ProjectNO：</span>
	        <input type="text" name="projectNO" class="form-control" size="15">  
	        &nbsp;&nbsp;   
	        <span>More：</span>      
            <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom"><i class="fa fa-angle-double-down"></i></button>
            &nbsp;&nbsp;  
            <div class="btn-group">
                <button type="submit" class="btn-green" data-icon="search">Search</button>
                &nbsp;&nbsp;
                <button type="reset" class="btn-orange" data-icon="times">Reset</button>
                &nbsp;&nbsp;
                <button type="button" class="btn-green" data-icon="sign-out" data-toggle="ajaxdownload"  data-options="{url:'exportCogsDetailExcel.action', loadingmask:false}" >Export</button>
            </div>
            <div class="bjui-moreSearch">
            <div class="bjui-row col-2">
            <label class="row-label">Customer:</label>
            <input type="text" name="Customer" class="form-control" size="15">
            <label class="row-label">Product Mix：</label>
            <input type="text" name="ProductMix" class="form-control" size="15"> 
            <label class="row-label">Frame(PO Peport)：</label>
	        <input type="text" name="FramePoReport" class="form-control" size="15">
	        </br>
	        <label class="row-label">Notes：</label>
	        <input type="text" name="Notes" class="form-control" size="15"> 
	        <label class="row-label">Frame(Projection)：</label>
	        <input type="text" name="FrameProjection" class="form-control" size="15"> 
			</div>
			</div>
        </div>
    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-COGS_Details_RMB-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'COGS_Details',
        showToolbar: false,
        dataUrl: 'getCogsDetail.action',
        paging: false,
        linenumberAll: true,
        filterThead: false,
        contextMenuB: true,
        hScrollbar: true,
        exportOption: {type:'file', options:{url:'exportCogsDetailExcel.action', loadingmask:false}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'Month',width:150,align:'left',finalWidth:'true'}">Month</th>
            	<th data-options="{name:'Customer',width:150,align:'left',finalWidth:'true'}">Customer</th>
            	<th data-options="{name:'ProductMix',width:150,align:'left',finalWidth:'true'}">ProductMix</th>
            	<th data-options="{name:'FramePoReport',width:150,align:'left',finalWidth:'true'}">FramePoReport</th>
            	<th data-options="{name:'projectNO',width:150,align:'left',finalWidth:'true'}">projectNO</th>
            	<th data-options="{name:'Sales',width:150,align:'left',finalWidth:'true'}">Sales</th>
            	<th data-options="{name:'COGS',width:150,align:'left',finalWidth:'true'}">COGS</th>
            	<th data-options="{name:'COGS_Sales',width:150,align:'left',finalWidth:'true'}">COGS/Sales</th>
            	<th data-options="{name:'GrossProfit',width:150,align:'left',finalWidth:'true'}">GrossProfit</th>
            	<th data-options="{name:'OperatingExpense',width:150,align:'left',finalWidth:'true'}">OperatingExpense</th>
            	<th data-options="{name:'OperatingProfit',width:150,align:'left',finalWidth:'true'}">OperatingProfit</th>
            	<th data-options="{name:'IrrecoverableVAT',width:150,align:'left',finalWidth:'true'}">IrrecoverableVAT</th>
            	
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
				
				<th data-options="{name:'Notes',width:150,align:'left',finalWidth:'true'}">Notes</th>
				
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
        </thead>
    </table>
</div>