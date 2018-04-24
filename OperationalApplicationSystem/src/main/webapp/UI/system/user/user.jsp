<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-user-filter')}">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
            <span>Name：</span>
            <input type="text" name="name" class="form-control" size="15">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <span>Role：</span>
            <input type="text" name="type" class="form-control" size="15">          
            <button type="button" class="showMoreSearch" data-toggle="moresearch" data-name="custom"><i class="fa fa-angle-double-down"></i></button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <div class="btn-group">
                <button type="submit" class="btn-green" data-icon="search">Search</button>
                <button type="reset" class="btn-orange" data-icon="times">Reset</button>
            </div>
        </div>
            <div class="bjui-moreSearch">
	            <label>JobNumber：</label><input type="text" value="" name="jobnumber" size="15" />
	            <label>&nbsp;Sex:</label>
	            <select name="sex" data-toggle="selectpicker">
	                <option value="">ALL</option>
	                <option value="M">Male</option>
	                <option value="F">Female</option>
	            </select>
	            <label>&nbsp;Date:</label>
	            <input type="text" value="" name="date" size="10">
        	</div>
    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-user-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'user management',
        showToolbar: true,
        toolbarItem: 'add,edit,del,|,import,export',
        dataUrl: 'getUser.action',
        dataType: 'jsonp',
        editMode: {dialog:{width:'800',height:280,title:'Edit User',mask:true}},
        delUrl:'deleteUser.action',
        editUrl: 'system/user/user-edit.jsp',
        paging: {pageSize:30, pageCurrent:1},
        showCheckboxcol: true,
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'system/user/user-import.html', width:500, height:300, title:'Import Users'}},
        exportOption: {type:'file', options:{url:'exportUserExcel.action', loadingmask:true}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'uid',width:100,align:'center',finalWidth:'true'}" >ID</th>
				<th data-options="{name:'name',width:180,align:'center',finalWidth:'true'}">UserName</th>
				<th data-options="{name:'position',width:230,align:'center',finalWidth:'true'}">Position</th>
				<th data-options="{name:'email',width:300,align:'center',finalWidth:'true'}">Email</th>
				<th data-options="{name:'rid',width:80,align:'center',finalWidth:'true',type:'select',itemattr:{value:'rid',label:'name'},items:$.getJSON('getAllRole.action')}">Role</th>
				<th data-options="{name:'did',width:100,align:'center',finalWidth:'true'}">BU ID</th>
				<th data-options="{name:'did',width:300,align:'center',finalWidth:'true',type:'select',itemattr:{value:'did',label:'name'},items:$.getJSON('getAllDepartment.action')}">Department</th>
				<th data-options="{name:'password',width:150,align:'center',finalWidth:'true',hide:'true'}">Password</th>
				<th data-options="{name:'quitDate',width:200,align:'center',finalWidth:'true'}">QuitDate</th>
                <th data-options="{name:'date',align:'center',width:200,type:'date',finalWidth:'true',pattern:'yyyy-MM-dd HH:mm:ss',render:function(value){return value?value.substr(0,19):value}}">Register Date</th>
            </tr>
        </thead>
    </table>
</div>