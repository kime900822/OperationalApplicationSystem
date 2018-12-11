<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">


</script>



<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-customs-material-mapping-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'materialMapping',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'add,edit,refresh,del,|,import',
        dataUrl: 'customs/queryMaterialMapping.action',
        editMode: {dialog:{width:'400',height:250,title:'Edit No',mask:true}},
        editUrl: 'customs/materialMapping/materialMapping-edit.jsp',
        delUrl: 'customs/deleteMaterialMapping.action',
        fieldSortable: false,
        filterThead: false,
        showCheckboxcol: true,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'customs/materialMapping/materialMapping-import.html', width:500, height:300, title:'Import MaterialMapping'}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
				<th data-options="{name:'oldMaterialNo',width:200,align:'left',finalWidth:'true'}">海关系统旧料号</th>
				<th data-options="{name:'newMaterialNo',width:200,align:'left',finalWidth:'true'}">JDE新料号</th>
            </tr>
        </thead>
    </table>
</div>