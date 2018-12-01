<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>



<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-customs-product-filter')}" id="datagrid-customs-product-query">
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
            	<input type="text" name="productName" value="" size="15">
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
            	<input type="text" name="batchNumber" value="" size="15">
        		</td>
        		<td colspan="2">
	        		<div class="btn-group">
	                <button type="submit" class="btn-green" data-icon="check">海关系统已操作</button>
	                <button type="reset" class="btn-orange" data-icon="undo">海关系统取消</button>
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
    <table class="table table-bordered" id="datagrid-customs-product-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Products Import',
        showToolbar: true,
        toolbarItem: 'refresh,|,import,export',
        dataUrl: 'customs/queryProducts.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: false,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'customs/product/product-import.html', width:500, height:300, title:'Import Products'}},
        exportOption: {type:'file', options:{url:'exportProductExcel.action', loadingmask:true}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
				<th data-options="{name:'no',width:200,align:'left',finalWidth:'true'}">序号</th>
				<th data-options="{name:'materialNo',width:200,align:'left',finalWidth:'true'}">料号</th>
				<th data-options="{name:'productNo',width:200,align:'left',finalWidth:'true'}">商品编码</th>
				<th data-options="{name:'productName',width:200,align:'left',finalWidth:'true'}">商品名称</th>
				<th data-options="{name:'specifications',width:200,align:'left',finalWidth:'true'}">规格型号</th>
				<th data-options="{name:'declareUnitCode',width:200,align:'left',finalWidth:'true'}">申报计量单位代码</th>
				<th data-options="{name:'legalUnitCode1',width:200,align:'left',finalWidth:'true'}">法定计量单位代码</th>
				<th data-options="{name:'legalUnitCode2',width:200,align:'left',finalWidth:'true'}">法定第二计量单位代码</th>
				<th data-options="{name:'declareNumber',width:200,align:'left',finalWidth:'true'}">申报数量</th>
				<th data-options="{name:'declarePrice',width:200,align:'left',finalWidth:'true'}">申报单价</th>
				<th data-options="{name:'currency',width:200,align:'left',finalWidth:'true'}">币制</th>
				<th data-options="{name:'exemptedMode',width:200,align:'left',finalWidth:'true'}">征免方式</th>
				<th data-options="{name:'executionMode',width:200,align:'left',finalWidth:'true'}">企业执行标志</th>
				<th data-options="{name:'modifyFlag',width:200,align:'left',finalWidth:'true'}">修改标志</th>
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