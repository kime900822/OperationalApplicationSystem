<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
//department
//操作列
function datagrid_tree_operation() {
 var html = '<button type="button" class="btn-green" data-toggle="edit.datagrid.tr">View</button><button type="button" class="btn-green" onclick="editStamp(this);" >Doc.</button>'   
 return html
}



function datagrid_urgent() {
    return [{'Y':'Y'},{'N':'Y'}]
}

$(function(){
	$.CurrentNavtab.find("#q_stamp_urgent").on('ifChecked',function(){
		$.CurrentNavtab.find("#q_stamp_urgent").val("1");
	})
	$.CurrentNavtab.find("#q_stamp_urgent").on('ifUnchecked',function(){
		$.CurrentNavtab.find("#q_stamp_urgent").val("0");
	})	
	
})	

$(function() {
	
	//获取文件类型
	BJUI.ajax('doajax', {
	    url: 'getCheckType4Select.action',
	    loadingmask: false,
	    okCallback: function(json, options) {
            $.each(json, function (i, item) {
                $.CurrentNavtab.find('#q_stamp_documentType').append("<option value='" + item.id + "'>" + item.value + "</option>")           
            })
            $.CurrentNavtab.find('#q_stamp_documentType').selectpicker('refresh');
	    }
	})	
	
})


function editStamp(o){
	BJUI.dialog({
	    id:'usedFiles',
	    url:'sign/stamp/stampform_all_usedFile.jsp',
	    title:'Used Files',
	    width:'800',
	    data:{id:$(o).parent().parent().siblings().eq(2).children().html()}
	})

	

}


</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-stamp-all-filter')}" id="datagrid-stamp-all-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="150px">
        		<span>Application Date：</span>
        		</td>
        		<td width="220px">
            	<input type="text" name="applicationDate_f"  data-nobtn="true"  id="q_stamp_applicationDate_f" value="" data-toggle="datepicker" size="9" data-rule="date">to:
            	<input type="text" name="applicationDate_t"  data-nobtn="true"  id="q_stamp_applicationDate_t" value="" data-toggle="datepicker" size="9" data-rule="date">
        		</td>
        		<td width="150px">
        		<span>Application Code：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="applicationCode" value="" id="q_stamp_applicationCode" size="15">
        		</td>
	        	<td width="80px">
        		<span>Cimtas ID:</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="applicantID" value="" id="q_stamp_applicantID" size="15">
        		</td>
        		<td width="80px">
        		<span>Urgent：</span>
        		</td>
        		<td width="80px">
            		<select name="urgent" data-toggle="selectpicker" id="q_stamp_urgent"  data-width="80px">
		              <option value=""></option>
		              <option value="1">Y</option>
		              <option value="0">N</option>
	            	</select>
         		</td>
        	</tr>    
        	<tr>
        		<td colspan="8" height="10px"></td>
        	</tr>
        	<tr>
        		<td>
        		<span>Stamp Type：</span>
        		</td>
        		<td>
            	<select name="stampType" data-toggle="selectpicker" id="q_stamp_stampType"  data-width="200px">
	              <option value=""></option>
	              <option value="CompanyChop">CompanyChop</option>
	              <option value="LegalDeputyChop">LegalDeputyChop</option>
	              <option value="FinancialChop">FinancialChop</option>
            	</select>
        		</td>
        		<td>
        		<span>Document Type：</span>
        		</td>
        		<td>
            	<select name="documentType" data-toggle="selectpicker" id="q_stamp_documentType"  data-width="150px">
	              <option value=""></option>
	              <option value="1" >Invoice Contract 发票合同</option>
            	</select>
        		</td>
        		<td>
        		<span>BU NO:</span>
        		</td>
        		<td>
            	<input type="text" name="departmentOfFormFillerID" value="" id="q_stamp_departmentOfFormFillerID" size="15">
        		</td>
        		<td>
        		<span>Status：</span>
        		</td>
        		<td>
	            	<select name="state" data-toggle="selectpicker" id="q_stamp_state"  data-width="200px">
		              <option value=""></option>
		              <option value="Submit Required" >Submit Required</option>
		              <option value="Level1 Approval" >Level1 Approval</option>
		              <option value="Level1 Rejected" >Level1 Rejected</option>
		              <option value="Level2 Approval" >Level2 Approval</option>
		              <option value="Level2 Rejected" >Level2 Rejected</option>
		              <option value="Level3 Approval" >Level3 Approval</option>	
		              <option value="Level3 Rejected" >Level3 Rejected</option>	              
		              <option value="Inform Approval" >Inform Approval</option>
		              <option value="Inform Rejected" >Inform Rejected</option>		              
		              <option value="END Approve" >END Approve</option>
	            	</select>
        		</td>
        	</tr>   
        	<tr>
        		<td colspan="8" height="10px"></td>
        	</tr> 
        	<tr>
        		<td><span> Chop Object:</span></td>
        		<td>
        			<input type="text" name="chopObject" value="" id="q_stamp_chopObject" size="15">
        		</td>
        		<td colspan="2"></td>
        		<td colspan="4" align="center">
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
    <table class="table table-bordered" id="datagrid-stamp-all-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'stamp management',
        dataUrl: 'getStamp.action?queryType=all',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'export,printPDF',
        editMode: {navtab:{width:'830',height:800,title:'Edit Seal',mask:true,fresh:true}},
        delUrl:'deleteUser.action',
        editUrl: 'sign/stamp/stampform.jsp',
        paging: {pageSize:30, pageCurrent:1},
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false,
        exportOption: {type:'file', options:{url:'exportStampExcel.action?queryType=all', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-stamp-all-query')}},
        printPDFOption: {type:'file', options:{url:'exportStampPDF.action?queryType=all', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-stamp-all-query')}}
    }">
        <thead>
            <tr>
            	<th data-options="{render:datagrid_tree_operation,align:'center'}">Operation</th>
            	<th data-options="{name:'usedFile',width:150,align:'center',finalWidth:'true',type:'select', items:datagrid_urgent}">Chopped Doc. Uploaded</th>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
            	<th data-options="{name:'applicationCode',width:150,align:'center',finalWidth:'true'}" >Application Code</th>
            	<th data-options="{name:'applicationDate',width:150,align:'center',finalWidth:'true'}" >Application Date</th>
				<th data-options="{name:'state',width:150,align:'center',finalWidth:'true'}">Approval Status</th>
				<th data-options="{name:'departmentOfFormFillerID',width:80,align:'center',finalWidth:'true'}">BU NO.</th>
				<th data-options="{name:'applicantID',width:100,align:'center' ,finalWidth:'true'}">Cimtas ID</th>
				<th data-options="{name:'applicant',width:200,align:'center',finalWidth:'true'}">User Name</th>
				<th data-options="{name:'chopObject',width:200,align:'center',finalWidth:'true' }">Chop Object</th>
				<th data-options="{name:'documentType',width:200,align:'center',finalWidth:'true',type:'select',itemattr:{value:'id',label:'value'},items:$.getJSON('getCheckType4Select.action')}">Document Type</th>
				<th data-options="{name:'stampType',width:180,align:'left',finalWidth:'true'}">Stamp Type</th>
				<th data-options="{name:'urgent',width:80,align:'center',finalWidth:'true',type:'select', items:datagrid_urgent}">Urgent</th>
				<th data-options="{name:'usageDescription',width:300,align:'left',finalWidth:'true'}">Usage Description</th>            
			</tr>
        </thead>
    </table>
</div>