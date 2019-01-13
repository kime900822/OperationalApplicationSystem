<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">

function deleteCustomsJDE(){
	var batchNumber=$.CurrentNavtab.find('#customsJDEBatchNumber').val();
	if(batchNumber==undefined || batchNumber==null ||batchNumber==''){
		BJUI.alertmsg('warn', '上传批次号不能为空！'); 
		return false;
	}
	
	BJUI.ajax('doajax', {
	    url: 'customs/deleteCustomsJDE.action',
	    loadingmask: true,
	    data:{batchNumber:batchNumber},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	BJUI.alertmsg('info', json.message); 
            	$.CurrentNavtab.find('#datagrid-customs-jde-filter').data('bjui.datagrid').refresh(true);
            }else{
            	BJUI.alertmsg('error', json.message); 
            }
	    }
	})	
	
	
	
}


</script>

<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-customs-jde-filter')}" id="datagrid-customs-jde-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="120px" >
        		<span>上传批次号：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="batchNumber" id="customsJDEBatchNumber" value="" size="15">
        		</td>
        		<td colspan="6" width="860px">
        		 	<button type="button" class="btn-red" data-icon="times" onclick="deleteCustomsJDE();">Delete</button>
        		</td>
        	
        	</tr>      
        
        </table>
            
                       
			
            
            
        </div>

    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-customs-jde-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'JDE Import',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh,|,import,export',
        dataUrl: 'customs/queryCustomsJDE.action',
        fieldSortable: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'customs/jde/jde-import.html', width:500, height:300, title:'Import JDE'}},
        exportOption: {type:'file', options:{url:'customs/exportCustomsJDE.action', loadingmask:true}}
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
				<th data-options="{name:'batchNumber',width:200,align:'left',finalWidth:'true'}">上传批次号</th>
				
            </tr>
        </thead>
    </table>
</div>