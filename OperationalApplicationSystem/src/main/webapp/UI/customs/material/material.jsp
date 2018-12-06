<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">


function customsMaterialHandingOK(){
	var batchNumber=$.CurrentNavtab.find('#customsMaterialBatchNumber').val();
	if(batchNumber==undefined || batchNumber==null ||batchNumber==''){
		BJUI.alertmsg('warn', '上传批次号不能为空！'); 
		return false;
	}
	
	BJUI.ajax('doajax', {
	    url: 'customs/materialHandingOK.action',
	    loadingmask: true,
	    data:{batchNumber:batchNumber},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	BJUI.alertmsg('info', json.message); 
            	$.CurrentNavtab.find('#datagrid-customs-material-filter').data('bjui.datagrid').refresh(true);
            }else{
            	BJUI.alertmsg('error', json.message); 
            }
	    }
	})	
	
	
	
}

function customsMaterialHandingNO(){
	var batchNumber=$.CurrentNavtab.find('#customsMaterialBatchNumber').val();
	if(batchNumber==undefined || batchNumber==null ||batchNumber==''){
		BJUI.alertmsg('warn', '上传批次号不能为空！'); 
		return false;
	}
	
	BJUI.ajax('doajax', {
	    url: 'customs/materialHandingNO.action',
	    loadingmask: true,
	    data:{batchNumber:batchNumber},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	BJUI.alertmsg('info', json.message); 
            	$.CurrentNavtab.find('#datagrid-customs-material-filter').data('bjui.datagrid').refresh(true);
            }else{
            	BJUI.alertmsg('error', json.message); 
            }
	    }
	})	
}

</script>


<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-customs-material-filter')}" id="datagrid-customs-material-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="150px">
        		<span>序号：</span>
        		</td>
        		<td width="220px">
            	<input type="text" name="no_f"  data-nobtn="true" value="" data-toggle="datepicker" size="9" data-rule="number">to:
            	<input type="text" name="no_t"  data-nobtn="true" value="" data-toggle="datepicker" size="9" data-rule="number">
        		</td>
        		<td width="150px">
        		<span>料号：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="materialNo" value=""  size="15">
        		</td>
	        	<td width="80px">
        		<span>商品编码:</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="productNo" value="" size="15">
        		</td>
        		<td width="80px">
        		<span>物料名称：</span>
        		</td>
        		<td width="80px">
            	<input type="text" name="materialName" value="" size="15">
         		</td>
        	</tr>    
        	<tr>
        		<td colspan="8" height="10px"></td>
        	</tr>
        	<tr>
        		<td>
        		<span>征免方式：</span>
        		</td>
        		<td>
            	<input type="text" name="exemptedMode" value="" size="15">
        		</td>
        		<td>
        		<span>上传批次号：</span>
        		</td>
        		<td>
            	<input type="text" name="batchNumber" id="customsMaterialBatchNumber" value="" size="15">
        		</td>
        		<td colspan="2">
	        		<div class="btn-group">
	                <button type="button" class="btn-green" data-icon="check" onclick="customsMaterialHandingOK()">海关系统已操作</button>
	                <button type="button" class="btn-orange" data-icon="undo" onclick="customsMaterialHandingNO()">海关系统取消</button>
	            	</div>
        		</td>
        		<td colspan="2">
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
    <table class="table table-bordered" id="datagrid-customs-material-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Materials Import',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh,|,import,export',
        dataUrl: 'customs/queryMaterial.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'customs/material/material-import.html', width:500, height:300, title:'Import materials'}},
        exportOption: {type:'file', options:{url:'customs/exportCustomsMaterial.action', loadingmask:true}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
				<th data-options="{name:'no',width:50,align:'left',finalWidth:'true'}">序号</th>
				<th data-options="{name:'materialNo',width:200,align:'left',finalWidth:'true'}">料号</th>
				<th data-options="{name:'productNo',width:200,align:'left',finalWidth:'true'}">商品编码</th>
				<th data-options="{name:'materialName',width:200,align:'left',finalWidth:'true'}">商品名称</th>
				<th data-options="{name:'specification',width:200,align:'left',finalWidth:'true'}">规格型号</th>
				<th data-options="{name:'declareUnitCode',width:200,align:'left',finalWidth:'true'}">申报计量单位代码</th>
				<th data-options="{name:'legalUnitCode1',width:200,align:'left',finalWidth:'true'}">法定计量单位代码</th>
				<th data-options="{name:'legalUnitCode2',width:200,align:'left',finalWidth:'true'}">法定第二计量单位代码</th>
				<th data-options="{name:'declareNumber',width:200,align:'left',finalWidth:'true'}">申报数量</th>
				<th data-options="{name:'declarePrice',width:200,align:'left',finalWidth:'true'}">申报单价</th>
				<th data-options="{name:'currency',width:200,align:'left',finalWidth:'true'}">币制</th>
				<th data-options="{name:'exemptedMode',width:200,align:'left',finalWidth:'true'}">征免方式</th>
				<th data-options="{name:'isMain',width:100,align:'left',finalWidth:'true'}">主料标志</th>
				<th data-options="{name:'executionMode',width:200,align:'left',finalWidth:'true'}">企业执行标志</th>
				<th data-options="{name:'modifyFlag',width:200,align:'left',finalWidth:'true'}">修改标志</th>
				<th data-options="{name:'maxNumber',width:200,align:'left',finalWidth:'true'}">批准最大余数量</th>
				<th data-options="{name:'comments',width:200,align:'left',finalWidth:'true'}">备注</th>
				<th data-options="{name:'uploadOperator',width:200,align:'left',finalWidth:'true'}">上传操作员工号</th>
				<th data-options="{name:'uploadDate',width:200,align:'left',finalWidth:'true'}">上传日期</th>
				<th data-options="{name:'batchNumber',width:200,align:'left',finalWidth:'true'}">上传批次号</th>
				<th data-options="{name:'declaration',width:200,align:'left',finalWidth:'true'}">海关系统是否申报</th>
				<th data-options="{name:'declarationOperatior',width:200,align:'left',finalWidth:'true'}">海关系统申报人员</th>
				<th data-options="{name:'declarationDate',width:200,align:'left',finalWidth:'true'}">海关系统操作日期</th>
            </tr>
        </thead>
    </table>
</div>