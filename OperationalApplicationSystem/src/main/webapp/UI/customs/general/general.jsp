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
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-customs-general-filter')}" id="datagrid-customs-general-query">
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
    <table class="table table-bordered" id="datagrid-customs-general-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : '总表',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh',
        dataUrl: 'customs/queryCustomsGeneral.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
            	<th data-options="{name:'date',width:150,align:'center',finalWidth:'true',hide:'true'}">日期</th>
				<th data-options="{name:'materialNo',width:120,align:'left',finalWidth:'true'}">海关物料编码</th>
				<th data-options="{name:'jdeMaterialNo',width:50,align:'left',finalWidth:'true'}">JDE物料编码</th>
				<th data-options="{name:'no',width:200,align:'left',finalWidth:'true'}">料件序号</th>
				<th data-options="{name:'productName',width:200,align:'left',finalWidth:'true'}">商品编码</th>
				<th data-options="{name:'materialName',width:200,align:'left',finalWidth:'true'}">物料名称</th>
				<th data-options="{name:'materialSpecification',width:200,align:'left',finalWidth:'true'}">物料规格</th>
				<th data-options="{name:'unit',width:200,align:'left',finalWidth:'true'}">单位</th>
				<th data-options="{name:'earlyNumber',width:200,align:'left',finalWidth:'true'}">期初数</th>
				<th data-options="{name:'incomingVolume',width:200,align:'left',finalWidth:'true'}">进厂量</th>
				<th data-options="{name:'writeOffVolume',width:200,align:'left',finalWidth:'true'}">核销量</th>
				<th data-options="{name:'regulatoryInventory',width:200,align:'left',finalWidth:'true'}">监管库存</th>
				<th data-options="{name:'pickingVolume',width:200,align:'left',finalWidth:'true'}">在制品领料</th>
				<th data-options="{name:'warehouseVolume',width:200,align:'left',finalWidth:'true'}">实际在库数量</th>
				<th data-options="{name:'price',width:200,align:'left',finalWidth:'true'}">单价</th>
				<th data-options="{name:'amount',width:200,align:'left',finalWidth:'true'}">金额</th>
            </tr>
        </thead>
    </table>
</div>