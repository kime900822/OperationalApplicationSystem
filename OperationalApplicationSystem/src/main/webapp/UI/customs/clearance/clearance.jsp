<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">


</script>


<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-customs-clearance-filter')}" id="datagrid-customs-clearance-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="120px">
        		<span>BOM申请日期：</span>
        		</td>
        		<td width="190px">
            	<input type="text" name="BOMDate"  data-nobtn="true"  value="" data-toggle="datepicker" size="9" data-rule="date">
        		</td>
        		<td colspan="6"  width="800px">
        		<button type="button" class="btn-green" data-icon="search">生成</button>
        		</td>
        	</tr>      
        	<tr>
        		<td colspan="8" height="10px"></td>
        	</tr>
        	<tr>
        		<td width="120px">
        		<span>上传批次号：</span>
        		</td>
        		<td width="190px">
            	<input type="text" name="batchNumber" value=""  size="15">
        		</td>
        		<td width="120px">
        		<span>料件序号：</span>
        		</td>
        		<td width="120px">
            	<input type="text" name="no" value=""  size="10">
        		</td>
        		<td width="120px">
        		<span>Pose Long Item Number：</span>
        		</td>
        		<td width="120px">
            	<input type="text" name="poseLongItemNo" value=""  size="10">
        		</td>
        		<td width="120px">
        		<span>海关出货项目：</span>
        		</td>
        		<td width="120px">
            	<input type="text" name="shipmentIems" value=""  size="10">
        		</td>
        		</tr>
        		<tr>
        		<td colspan="8" height="10px"></td>
        		</tr>
        		<tr>
        		<td  colspan="8">
        			<div class="btn-group">
	                	<button type="submit" class="btn-green" data-icon="search">Search</button>
	                	<button type="reset" class="btn-orange" data-icon="times">Reset</button>
	                	<button type="button" class="btn-orange" data-icon="times">Delete</button>
	            	</div>
         		</td>
        	</tr>
        </table>
            
        </div>

    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-customs-clearance-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Customs Clearance Import',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'edit,refresh,|,import,export',
        dataUrl: 'customs/queryCustomsClearance.action',
        editMode: {dialog:{width:'400',height:250,title:'Edit No',mask:true}},
        editUrl: 'customs/clearance/clearance-edit.jsp',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'customs/clearance/clearance-import.html', width:500, height:300, title:'Import materials'}},
        exportOption: {type:'file', options:{url:'customs/exportCustomsClearance.action', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-customs-clearance-query')}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
				<th data-options="{name:'no',width:60,align:'left',finalWidth:'true'}">料件序号</th>
				<th data-options="{name:'deliveryDate',width:200,align:'left',finalWidth:'true'}">提货日期</th>
				<th data-options="{name:'shipmentIems',width:200,align:'left',finalWidth:'true'}">海关出货项目</th>
				<th data-options="{name:'longProjectNo',width:200,align:'left',finalWidth:'true'}">Long Project No</th>
				<th data-options="{name:'cimtasNo',width:200,align:'left',finalWidth:'true'}">Cimtas No</th>
				<th data-options="{name:'poseLongItemNo',width:200,align:'left',finalWidth:'true'}">Pose Long Item No</th>
				<th data-options="{name:'oldItemNo',width:200,align:'left',finalWidth:'true'}">Old Item No</th>
				<th data-options="{name:'materialDescription',width:200,align:'left',finalWidth:'true'}">Material Description</th>
				<th data-options="{name:'dia',width:200,align:'left',finalWidth:'true'}">Dia-1 OD (mm)</th>
				<th data-options="{name:'sch',width:200,align:'left',finalWidth:'true'}">Sch(mm)-1</th>
				<th data-options="{name:'quantityOrdered',width:200,align:'left',finalWidth:'true'}">Quantity Ordered</th>
				<th data-options="{name:'quantityIssued',width:200,align:'left',finalWidth:'true'}">Quantity Issued</th>
				<th data-options="{name:'weight',width:200,align:'left',finalWidth:'true'}">Weight(kg) 1</th>
				<th data-options="{name:'scn',width:200,align:'left',finalWidth:'true'}">SCN</th>
				<th data-options="{name:'orTy',width:200,align:'left',finalWidth:'true'}">Or Ty</th>
				<th data-options="{name:'orderNumber',width:200,align:'left',finalWidth:'true'}">Order Number</th>
				<th data-options="{name:'manufacName',width:200,align:'left',finalWidth:'true'}">Manufac. Name</th>
				<th data-options="{name:'LotSerialNumber',width:200,align:'left',finalWidth:'true'}">Lot Serial Number</th>
				<th data-options="{name:'BatchNumber',width:200,align:'left',finalWidth:'true'}">上传批次号</th>
				<th data-options="{name:'operator',width:200,align:'left',finalWidth:'true'}">操作员</th>
				<th data-options="{name:'operationDate',width:200,align:'left',finalWidth:'true'}">操作日期</th>
				<th data-options="{name:'BOMDate',width:200,align:'left',finalWidth:'true'}">BOM申报日期</th>
            </tr>
        </thead>
    </table>
</div>