<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">

$(function(){
	
	
	if('${user.uid}'=='admin'){
		$.CurrentNavtab.find('#customs_button_unlock').show();
	}
	var today = new Date().formatDate('yyyy-MM');
	$.CurrentNavtab.find('#customsGeneralMonth').val(today);
	
})


function saveCustomsGeneral(){
	var month=$.CurrentNavtab.find('#customsGeneralMonth').val();
	if(month==undefined || month==null ||month==''){
		BJUI.alertmsg('warn', '月份不能为空！'); 
		return false;
	}
	
	BJUI.ajax('doajax', {
	    url: 'customs/saveCustomsGeneral.action',
	    loadingmask: true,
	    data:{month:month},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	BJUI.alertmsg('info', json.message); 
            }else{
            	BJUI.alertmsg('error', json.message); 
            }
	    }
	})	
	
	
	
	
}



function unCustomsGeneral(){
	var month=$.CurrentNavtab.find('#customsGeneralMonth').val();
	if(month==undefined || month==null ||month==''){
		BJUI.alertmsg('warn', '月份不能为空！'); 
		return false;
	}
	
	BJUI.ajax('doajax', {
	    url: 'customs/unCustomsGeneral.action',
	    loadingmask: true,
	    data:{month:month},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	BJUI.alertmsg('info', json.message); 
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
        		<span>月份：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="month" value="" id="customsGeneralMonth" data-toggle="datepicker" data-pattern="yyyy-MM" size="15" data-rule="month;required" placeholder="点击选择日期">
        		</td>
        		<td width="120px">
        		<span>海关物料编码：</span>
        		</td>
        		<td width="180px">
            		<input type="text" name="materialNo" value=""  size="15">
        		</td>
	        	<td width="100px">
        			<span>JDE物料编码:</span>
        		</td>
        		<td width="120px">
        			<input type="text" name="jdeMaterialNo" value=""  size="9">
        		</td>
        		<td width="100px">
        			<span>料件序号:</span>
        		</td>
        		<td width="120px">
        			<input type="text" name="no" value=""  size="9">
        		</td>
        	<tr>
        		<td colspan="8" height="6px"></td>
        	</tr>
        	<tr>
        		<td >
        		<span>商品编码：</span>
        		</td>
        		<td>
            	<input type="text" name="productNo" value=""  size="15">
        		</td>
        		<td>
        		<span>物料名称：</span>
        		</td>
        		<td>
            		<input type="text" name="materialName" value=""  size="15">
        		</td>
        		<td colspan="4">
	        		<div class="btn-group">
	                <button type="submit" class="btn-green" data-icon="search">Search</button>
	                <button type="reset" class="btn-orange" data-icon="times">Reset</button>
	                <button type="button" class="btn-orange" data-icon="save" onclick="saveCustomsGeneral()">锁定</button>
	                <button type="button" id="customs_button_unlock" class="btn-orange" data-icon="times" style="display: none" onclick="unCustomsGeneral()">取消锁定</button>
	            	</div>
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
        gridTitle : '总表查询',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'export',
        toolbarItem: 'refresh,export',
        dataUrl: 'customs/queryCustomsGeneral.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        exportOption: {type:'file', options:{url:'customs/exportCustomsGeneral.action', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-customs-general-query')}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
				<th data-options="{name:'month',width:200,align:'left',finalWidth:'true'}">月份</th>
				<th data-options="{name:'materialNo',width:200,align:'left',finalWidth:'true'}">海关物料编码</th>
				<th data-options="{name:'jdeMaterialNo',width:200,align:'left',finalWidth:'true'}">JDE物料编码</th>
				<th data-options="{name:'no',width:100,align:'left',finalWidth:'true'}">料件序号</th>
				<th data-options="{name:'productNo',width:200,align:'left',finalWidth:'true'}">商品编码</th>
				<th data-options="{name:'materialName',width:200,align:'left',finalWidth:'true'}">物料名称</th>
				<th data-options="{name:'materialSpecification',width:200,align:'left',finalWidth:'true'}">物料规格</th>
				<th data-options="{name:'unit',width:100,align:'left',finalWidth:'true'}">单位</th>
				<th data-options="{name:'earlyNumber',width:100,align:'left',finalWidth:'true'}">期初数</th>
				<th data-options="{name:'incomingVolume',width:100,align:'left',finalWidth:'true'}">进厂量</th>
				<th data-options="{name:'writeOffVolume',width:100,align:'left',finalWidth:'true'}">核销量</th>
				<th data-options="{name:'regulatoryInventory',width:100,align:'left',finalWidth:'true'}">监管库存</th>
				<th data-options="{name:'price',width:100,align:'left',finalWidth:'true'}">单价</th>
				<th data-options="{name:'currency',width:100,align:'left',finalWidth:'true'}">单价币别</th>
				<th data-options="{name:'amount',width:100,align:'left',finalWidth:'true'}">总金额</th>
				<th data-options="{name:'lockMonth',width:100,align:'left',finalWidth:'true'}">锁定日期</th>
				
            </tr>
        </thead>
    </table>
</div>