<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">

function deleteCustomsImportsAndExports(){
	var batchNumber=$.CurrentNavtab.find('#customsMaterialBatchNumber').val();
	if(batchNumber==undefined || batchNumber==null ||batchNumber==''){
		BJUI.alertmsg('warn', '上传批次号不能为空！'); 
		return false;
	}
	
	BJUI.ajax('doajax', {
	    url: 'customs/deleteCustomsImportsAndExports.action',
	    loadingmask: true,
	    data:{batchNumber:batchNumber},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	BJUI.alertmsg('info', json.message); 
            	$.CurrentNavtab.find('#datagrid-customs-importsandexports-filter').data('bjui.datagrid').refresh(true);
            }else{
            	BJUI.alertmsg('error', json.message); 
            }
	    }
	})	
	
	
	
}

</script>


<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-customs-importsandexports-filter')}" id="datagrid-customs-importsandexports-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="120px">
        		<span>Order Number：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="orderNumber" value=""  size="15">
        		</td>
        		<td width="160px">
        		<span>Cimtas Code：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="cimtasCode" value=""  size="15">
        		</td>
	        	<td width="100px">
        		<span>报关进料日期:</span>
        		</td>
        		<td width="220px">
            	<input type="text" name="entryDate_f"  data-nobtn="true"  value="" data-toggle="datepicker" size="9" data-rule="date">to:
            	<input type="text" name="entryDate_t"  data-nobtn="true"  value="" data-toggle="datepicker" size="9" data-rule="date">
        		</td>
        		<td width="100px">
        			<span>备案号:</span>
         		</td>
         		<td width="100px">
        			<input type="text" name="no" value=""  size="9">
         		</td>
        	</tr>  
        	<tr>
        		<td colspan="8" height="6px"></td>
        	</tr>
        	<tr>
        		<td colspan="8">
        			<div class="btn-group">
	                	<button type="submit" class="btn-green" data-icon="search">Search</button>
	                	<button type="reset" class="btn-orange" data-icon="times">Reset</button>
	            	</div>
         		</td>
        	</tr>
        	<tr>
        		<td colspan="8" height="6px"></td>
        	</tr>
        	<tr>
        		<td>
        		<span>上传批次号：</span>
        		</td>
        		<td>
            	<input type="text" name="batchNumber" id="customsMaterialBatchNumber" value="" size="15">
        		</td>
        		<td colspan="6">
        		 	<button type="button" class="btn-red" data-icon="times" onclick="deleteCustomsImportsAndExports();">Delete</button>
        		</td>
        	
        	</tr>    
        
        </table>
            
                       
			
            
            
        </div>

    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-customs-importsandexports-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'ImportsAndExports Import',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh,|,import,export',
        dataUrl: 'customs/queryCustomsImportsAndExports.action',
        editMode: {dialog:{width:'400',height:250,title:'Edit No',mask:true}},
        editUrl: 'customs/importsandexports/importsandexports-edit.jsp',
        fieldSortable: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'customs/importsandexports/importsandexports-import.html', width:500, height:300, title:'Import materials'}},
        exportOption: {type:'file', options:{url:'customs/exportCustomsImportsAndExports.action', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-customs-importsandexports-query')}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
				<th data-options="{name:'entryDate',width:200,align:'left',finalWidth:'true'}">报关进料日期</th>
				<th data-options="{name:'entryNo',width:200,align:'left',finalWidth:'true'}">报关单号</th>
				<th data-options="{name:'orTy',width:200,align:'left',finalWidth:'true'}">Or Ty</th>
				<th data-options="{name:'orderNumber',width:200,align:'left',finalWidth:'true'}">Order Number</th>
				<th data-options="{name:'cimtasCode',width:200,align:'left',finalWidth:'true'}">Cimtas Code</th>
				<th data-options="{name:'no',width:60,align:'left',finalWidth:'true'}">备案号</th>
				<th data-options="{name:'name',width:200,align:'left',finalWidth:'true'}"> name 中文 品名</th>
				<th data-options="{name:'description',width:200,align:'left',finalWidth:'true'}">Description 规格</th>
				<th data-options="{name:'quantity',width:200,align:'left',finalWidth:'true'}">Q'TY</th>
				<th data-options="{name:'unit',width:200,align:'left',finalWidth:'true'}">UNIT</th>
				<th data-options="{name:'currency',width:200,align:'left',finalWidth:'true'}">Currency</th>
				<th data-options="{name:'unitPrice',width:200,align:'left',finalWidth:'true'}">UNIT PRICE</th>
				<th data-options="{name:'amount',width:200,align:'left',finalWidth:'true'}">AMOUNT</th>
				<th data-options="{name:'unitPriceUSD',width:200,align:'left',finalWidth:'true'}">unit price USD</th>
				<th data-options="{name:'amountUSD',width:200,align:'left',finalWidth:'true'}">Amount USD</th>
				<th data-options="{name:'netWeight',width:200,align:'left',finalWidth:'true'}">NET WEIGHT (KG)</th>
				<th data-options="{name:'origin',width:200,align:'left',finalWidth:'true'}">origin</th>
				<th data-options="{name:'operator',width:200,align:'left',finalWidth:'true'}">操作员</th>
				<th data-options="{name:'operationDate',width:200,align:'left',finalWidth:'true'}">操作日期</th>
				<th data-options="{name:'batchNumber',width:200,align:'left',finalWidth:'true'}">batchNumber</th>
            </tr>
        </thead>
    </table>
</div>