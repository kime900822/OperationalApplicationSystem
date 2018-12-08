<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">


</script>


<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-customs-importsandexports-filter')}" id="datagrid-customs-material-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="150px">
        		<span>Order Number：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="orderNumber" value=""  size="15">
        		</td>
        		<td width="150px">
        		<span>Cimtas Long Item No：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="cimtasLongItemNo" value=""  size="15">
        		</td>
	        	<td width="80px">
        		<span>报关进料日期:</span>
        		</td>
        		<td width="220px">
            	<input type="text" name="entryDate_f"  data-nobtn="true"  value="" data-toggle="datepicker" size="9" data-rule="date">to:
            	<input type="text" name="entryDate_t"  data-nobtn="true"  value="" data-toggle="datepicker" size="9" data-rule="date">
        		</td>
        		<td width="160px" colspan="2">
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
    <table class="table table-bordered" id="datagrid-customs-importsandexports-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'ImportsAndExports Import',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'edit,refresh,|,import,export',
        dataUrl: 'customs/queryImportsAndExports.action',
        editMode: {dialog:{width:'400',height:250,title:'Edit No',mask:true}},
        editUrl: 'customs/importsandexports/importsandexports-edit.jsp',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'customs/importsandexports/importsandexports-import.html', width:500, height:300, title:'Import materials'}},
        exportOption: {type:'file', options:{url:'customs/exportImportsAndExports.action', loadingmask:true}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
				<th data-options="{name:'no',width:60,align:'left',finalWidth:'true'}">料件序号</th>
				<th data-options="{name:'entryDate',width:200,align:'left',finalWidth:'true'}">报关进料日期</th>
				<th data-options="{name:'orTY',width:200,align:'left',finalWidth:'true'}">Or Ty</th>
				<th data-options="{name:'orderNumber',width:200,align:'left',finalWidth:'true'}">Order Number</th>
				<th data-options="{name:'cimtasLongItemNo',width:200,align:'left',finalWidth:'true'}">Cimtas Long Item No</th>
				<th data-options="{name:'materialLongDescription',width:200,align:'left',finalWidth:'true'}">Material Long Description</th>
				<th data-options="{name:'quantity',width:200,align:'left',finalWidth:'true'}">Quantity</th>
				<th data-options="{name:'unit',width:200,align:'left',finalWidth:'true'}">单位</th>
				<th data-options="{name:'unitPrice',width:200,align:'left',finalWidth:'true'}">Unit Price</th>
				<th data-options="{name:'totalPrice',width:200,align:'left',finalWidth:'true'}">Total Price</th>
				<th data-options="{name:'operator',width:200,align:'left',finalWidth:'true'}">操作员</th>
				<th data-options="{name:'operationDate',width:200,align:'left',finalWidth:'true'}">操作日期</th>
            </tr>
        </thead>
    </table>
</div>