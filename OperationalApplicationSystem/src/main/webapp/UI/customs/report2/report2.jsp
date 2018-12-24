<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">



</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-customs-report2-filter')}" id="datagrid-customs-report2-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="100px">
        		<span>企业物料编码：</span>
        		</td>
        		<td width="150px">
            	<input type="text" name="materialNo"  value="" size="13" >
        		</td>
        		<td width="130px">
        		<span>Order Number：</span>
        		</td>
        		<td width="110px">
            	<input type="text" name="orderNumber" value=""  size="9" >
        		</td>
	        	<td width="150px">
        		<span>Cimtas Long Item No:</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="cimtasLongItemNo" value="" size="15">
        		</td>
        		<td width="80px">
        		<span>数量差异:</span>
        		</td>
        		<td width="80px">
        			<select name="dvalue" data-toggle="selectpicker" data-width="80px">
		              <option value=""></option>
		              <option value="-1">小于0</option>
		              <option value="0">等于0</option>
		              <option value="1">大于0</option>
	            	</select>
        		</td>
        	</tr>  
        	<tr>
        		<td colspan="8" height="10px"></td>
        	</tr>
        	<tr>
        		<td colspan="8" >
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
    <table class="table table-bordered" id="datagrid-customs-report2-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : '表2',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh',
        dataUrl: 'customs/queryCustomsReport2.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true
    }">
        <thead>
            <tr>
				<th data-options="{name:'orderNumber',width:120,align:'left',finalWidth:'true'}">Order Number</th>
				<th data-options="{name:'cimtasLongItemNo',width:160,align:'left',finalWidth:'true'}">企业物料编码</th>
				<th data-options="{name:'newCimtasLongItemNo',width:160,align:'left',finalWidth:'true'}">Cimtas Long Item No</th>
				<th data-options="{name:'customsQuality',width:120,align:'left',finalWidth:'true'}">Custom Quantity</th>
				<th data-options="{name:'JEDTransQty',width:200,align:'left',finalWidth:'true'}">JDE Trans QTY</th>
				<th data-options="{name:'dvalue',width:200,align:'left',finalWidth:'true'}">数量差异</th>
            </tr>
        </thead>
    </table>
</div>