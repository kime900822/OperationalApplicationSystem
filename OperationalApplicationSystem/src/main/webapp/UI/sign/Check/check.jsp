<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">


</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-check-filter')}">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
            <span>Type</span>
            <input type="text" name="type" id="j_check_type" size="19"  >
            &nbsp;&nbsp;&nbsp;&nbsp;

            <div class="btn-group">
                <button type="submit" class="btn-green" data-icon="search">Search</button>
                <button type="reset" class="btn-orange" data-icon="times">Reset</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-check-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'check management',
        showToolbar: true,
        toolbarItem: 'add,edit,del',
        dataUrl: 'getCheck.action',
        dataType: 'jsonp',
        editMode: {dialog:{width:'800',height:350,title:'Edit Check',mask:true}},
        delUrl:'deleteCheck.action',
        editUrl: 'sign/Check/check-edit.jsp',
        paging: {pageSize:30, pageCurrent:1},
        showCheckboxcol: true,
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false
    }">
        <thead>
            <tr>
            	<th  rowspan="2" data-options="{name:'id',width:50,align:'center',finalWidth:'true',hide:'true'}" >id</th>
            	<th  rowspan="2" data-options="{name:'type',width:150,align:'center',finalWidth:'true'}" >签核类型</th>
            	<th  colspan="2" align="center">First Level</th>
            	<th  colspan="2" align="center">Second Level</th>
            	<th  colspan="2" align="center">Audit & Filing</th>
            	<th  rowspan="2" data-options="{name:'attachment',width:150,align:'center',finalWidth:'true'}">Attachment</th>
           	</tr>
           	<tr>
				<th data-options="{name:'fistUID',width:150,align:'center',finalWidth:'true'}">ID</th>
				<th data-options="{name:'fistUname',width:150,align:'center',finalWidth:'true' }" >Name</th>
				<th data-options="{name:'secondUID',width:150,align:'center',finalWidth:'true'}">ID</th>
				<th data-options="{name:'secondUname',width:150,align:'center',finalWidth:'true'}">Name</th>
				<th data-options="{name:'thirdUID',width:150,align:'center',finalWidth:'true'}">ID</th>
				<th data-options="{name:'thirdUname',width:150,align:'center',finalWidth:'true'}">Name</th>
				<th data-options="{name:'attachment',width:150,align:'center',finalWidth:'true'}">Attachment</th>
            </tr>
        </thead>
    </table>
</div>