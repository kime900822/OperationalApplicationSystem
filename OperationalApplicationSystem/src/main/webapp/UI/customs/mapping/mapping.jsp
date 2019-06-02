<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">


</script>


<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-customs-mapping-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Materials Import',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh,|,import,export',
        dataUrl: 'customs/queryCustomsMapping.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'customs/mapping/mapping-import.html', width:500, height:300, title:'Import Mapping'}},
        exportOption: {type:'file', options:{url:'customs/exportCustomsMapping.action', loadingmask:true}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
				<th data-options="{name:'customsSerialNumber',width:50,align:'left',finalWidth:'true'}"> 海关备案号</th>
				<th data-options="{name:'customsCode',width:200,align:'left',finalWidth:'true'}">海关编码</th>
				<th data-options="{name:'customsMaterialDescription',width:200,align:'left',finalWidth:'true'}">海关物料描述</th>
				<th data-options="{name:'JDEItemde',width:200,align:'left',finalWidth:'true'}">JDE Item Code</th>
				<th data-options="{name:'JDEItemDescription',width:200,align:'left',finalWidth:'true'}">JDE Item Description</th>
            </tr>
        </thead>
    </table>
</div>