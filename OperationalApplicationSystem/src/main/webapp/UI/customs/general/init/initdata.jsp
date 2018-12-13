<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">


</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-customs-general-init-filter')}" id="datagrid-customs-general-init-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="120px" >
        		<span>月份：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="month" value="" data-toggle="datepicker" data-pattern="yyyy-MM" size="15" data-rule="month;required" placeholder="点击选择日期">
        		</td>
        		<td colspan="6" width="860px">
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
    <table class="table table-bordered" id="datagrid-customs-general-init-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : '期初数据导入',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh,|,import',
        dataUrl: 'customs/queryCustomsGeneralInit.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'customs/general/init/initdata-import.html', width:500, height:300, title:'Import materials'}}
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
				
            </tr>
        </thead>
    </table>
</div>