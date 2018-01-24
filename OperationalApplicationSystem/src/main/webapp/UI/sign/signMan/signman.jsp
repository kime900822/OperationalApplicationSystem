<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
$(function(){
	
	BJUI.ajax('doajax', {
	    url: 'getALLSign.action',
	    loadingmask: false,
	    okCallback: function(json, options) {
            $.each(json, function (i, item) {
                $.CurrentNavtab.find('#j_signman_type').append("<option value='" + item.value + "'>" + item.key + "</option>")           
            })
            $.CurrentNavtab.find('#j_signman_type').selectpicker('refresh');
	    }
	})	
	
})

</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-signmane-filter')}">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
            <span>SignType：</span>
            <select name="type" id="j_signman_type" data-toggle="selectpicker" data-width="200">
	                <option value=""></option>
	        </select>
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
    <table class="table table-bordered" id="datagrid-signmane-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'sign management',
        showToolbar: true,
        toolbarItem: 'add,edit,del',
        dataUrl: 'querySignMan.action',
        dataType: 'jsonp',
        editMode: {dialog:{width:'800',height:200,title:'Edit SignMan',mask:true}},
        delUrl:'deleteSignMan.action',
        editUrl: 'sign/signMan/signman-edit.jsp',
        paging: {pageSize:60, pageCurrent:1},
        showCheckboxcol: true,
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false
    }">
        <thead>
            <tr>
            	<th data-options="{name:'sid',width:150,align:'center',finalWidth:'true',hide:'true'}" >sid</th>
				<th data-options="{name:'type',width:150,align:'center',finalWidth:'true'}">Type</th>
				<th data-options="{name:'did',width:150,align:'center',finalWidth:'true'}" >BU ID</th>
				<th data-options="{name:'dname',width:200,align:'center',finalWidth:'true'}">Department</th>
				<th data-options="{name:'uid',width:150,align:'center',finalWidth:'true' }" >Name ID</th>
				<th data-options="{name:'uname',width:150,align:'center',finalWidth:'true'}">Name</th>
            </tr>
        </thead>
    </table>
</div>