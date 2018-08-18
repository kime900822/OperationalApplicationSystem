<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
//department
//操作列
function datagrid_tree_operation() {
 var html = '<button type="button" class="btn-green" data-toggle="edit.datagrid.tr">View</button>'   
 return html
}


</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-payment-admin-filter')}" id="datagrid-payment-admin-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="150px">
        		<span>Application Date：</span>
        		</td>
        		<td width="220px">
            	<input type="text" name="applicationDate_f"  data-nobtn="true"  value="" data-toggle="datepicker" size="9" data-rule="date">to:
            	<input type="text" name="applicationDate_t"  data-nobtn="true"  value="" data-toggle="datepicker" size="9" data-rule="date">
        		</td>
        		<td width="150px">
        		<span>ReferenceNO：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="referenceN0" value=""  size="15">
        		</td>
        		<td>
        		<span>Cimtas ID：</span>
        		</td>
        		<td width="180px">
        		<input type="text" name="uId" value="" size="15">
        		</td>
        	</tr>    
        	<tr>
        		<td colspan="6" height="10px"></td>
        	</tr>
        	<tr>
        		<span>Status</span>
        		</td>
        		<td>
            	<select name="state" data-toggle="selectpicker"  data-width="80px">
	              <option value="" >all</option>
            	</select>
        		</td>
        		<td colspan="2"></td>
        		<td colspan="2" align="center">
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
    <table class="table table-bordered" id="datagrid-payment-admin-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Payment_Visit management',
        dataUrl: 'getPaymentVisit.action?queryType=manager',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'export',
        editMode: {navtab:{width:'830',height:800,title:'Edit Payment',mask:true,fresh:true}},
        editUrl: 'sign/payment/visit/payment_visit_form.jsp?viewtype=manager',
        paging: {pageSize:30, pageCurrent:1},
        linenumberAll: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false,
        exportOption: {type:'file', options:{url:'exportPaymentExcel.action?queryType=admin', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-payment-admin-query')}}
    }">
        <thead>
            <tr>
            	<th data-options="{render:datagrid_tree_operation,align:'center'}">Operation</th>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
            	<th data-options="{name:'applicantDate',width:150,align:'center',finalWidth:'true'}" >Applicant Date</th>
				<th data-options="{name:'referenceNo',width:150,align:'center',finalWidth:'true'}">ReferenceNo</th>
				<th data-options="{name:'uId',width:150,align:'center',finalWidth:'true'}">Cimtas ID</th>
				<th data-options="{name:'uName',width:150,align:'center',finalWidth:'true'}">User Name</th>
				<th data-options="{name:'state',width:150,align:'center',finalWidth:'true'}">Approval Status</th>
			</tr>
        </thead>
    </table>
</div>