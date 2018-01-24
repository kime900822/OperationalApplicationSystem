<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">

</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-signmane4management-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'signMan4management ',
        showToolbar: true,
        toolbarItem: 'add,edit,del',
        dataUrl: 'getSignMan4Management.action',
        dataType: 'jsonp',
        editMode: {dialog:{width:'800',height:200,title:'Edit SignMan4Manager',mask:true}},
        delUrl:'deleteDict.action',
        editUrl: 'sign/signMan/signMan4Management/signman4management-edit.jsp',
        paging: {pageSize:60, pageCurrent:1},
        showCheckboxcol: true,
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false
    }">
        <thead>
            <tr>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}" >id</th>
				<th data-options="{name:'type',width:150,align:'center',finalWidth:'true',edit:false}">Type</th>
				<th data-options="{name:'key',width:200,align:'center',finalWidth:'true'}">ManagerID</th>
				<th data-options="{name:'keyExplain',width:200,align:'center',finalWidth:'true'}">ManagerName</th>
				<th data-options="{name:'value',width:150,align:'center',finalWidth:'true'}">SignID</th>
				<th data-options="{name:'valueExplain',width:150,align:'center',finalWidth:'true'}">SignName</th>
            </tr>
        </thead>
    </table>
</div>