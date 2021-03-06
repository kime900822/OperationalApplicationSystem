<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
//department
//操作列
function datagrid_tree_operation() {
 var html = '<button type="button" class="btn-green" onclick="viewPayment(this);" >View</button>'   
 return html
}


function datagrid_paymentSubject() {
    return [{'1':'Fixed Asset 固定资产'},{'2':'Raw Material 原材料'},{'3':'Consumable 消耗品'},{'4':'Subcontractor 外包'},{'5':'Service 服务'},{'6':'Petty Cash备用金'},{'7':'Other 其他'},{'8':'Travel 差旅费'}]
}


function datagrid_urgent() {
    return [{'0':''},{'1':'Y'}]
}

$(function(){
	$.CurrentNavtab.find("#q_payment_urgent").on('ifChecked',function(){
		$.CurrentNavtab.find("#q_payment_urgent").val("1");
	})
	$.CurrentNavtab.find("#q_payment_urgent").on('ifUnchecked',function(){
		$.CurrentNavtab.find("#q_payment_urgent").val("0");
	})	
	
})	

function getCheckId(){
	var ids= new Array();
	var datas = $('#datagrid-payment-cashier-filter').data('selectedTrs')
	if(datas!=undefined){
		$.each(datas,function(i,n){
			ids.push(n.cells[3].innerText.replace('\n',''));						
		})		
	}
	return ids;
}

function viewPayment(o){
	var id=$(o).parent().parent().siblings().eq(2).children().html();
	BJUI.navtab({
	    id:'view_payment',
	    url:'sign/payment/paymentform_show.jsp',
	    title:'查看Payment',
	    data:{id:$(o).parent().parent().siblings().eq(2).children().html()}
	})
	

}



function FinanceReject(){

  var ids=getCheckId();
  
  if(ids==null||ids==undefined||ids==''){
	  BJUI.alertmsg('info', 'No row selected');	
	  return false
  }
  
  BJUI.alertmsg('prompt','Reject原因', {
	  okCall:function(val){ 
				BJUI.ajax('doajax', {
				    url: 'financeRejectPayment.action',
				    loadingmask: true,
				    data: {json:JSON.stringify(ids),message:val},
				    okCallback: function(json, options) {
				    	if(json.status='200'){
				    		BJUI.alertmsg('info', json.message);					    		
				    	}else{
				    		BJUI.alertmsg('error', json.message);		
				    	}
				    }
				})	
				
		  }
		  })
 
}

function weeklyReport(){	
	  var ids=getCheckId();
	  if(ids==null||ids==undefined||ids==''){
		  BJUI.alertmsg('info', 'No row selected');	
		  return false
	  }

		BJUI.ajax('doajax', {
		    url: 'checkWeeklyReportPayment.action',
		    loadingmask: true,
		    data: {json:JSON.stringify(ids)},
		    okCallback: function(json, options) {
		    	if(json.status='200'){
		    	 	  BJUI.ajax('ajaxdownload', {
		    			    url:'weeklyReportPayment.action',
		    			    data: {json:JSON.stringify(ids)},
		    			    loadingmask: true
		    			})				    		
		    	}else{
		    		BJUI.alertmsg('error', json.message);		
		    	}
		    }
		})
	  
	  


}


function paidPayment(){	
	  var ids=getCheckId();
	  
	  if(ids==null||ids==undefined||ids==''){
		  BJUI.alertmsg('info', 'No row selected');	
		  return false
	  }
	  
	  BJUI.alertmsg('prompt','Ref Bank NO', {
		  okCall:function(val){ 
			  BJUI.ajax('doajax', {
				    url:'paidPayment.action',
				    data: {json:JSON.stringify(ids),refNoofBank:value},
				    loadingmask: true,
				    okCallback: function(json, options) {
				    	if(json.status='200'){
				    		BJUI.alertmsg('info', json.message);					    		
				    	}else{
				    		BJUI.alertmsg('error', json.message);		
				    	}
				    }
				})
					
			  }
			  })

}


function paidResetPayment(){
	  var ids=getCheckId();
	  
	  if(ids==null||ids==undefined||ids==''){
		  BJUI.alertmsg('info', 'No row selected');	
		  return false
	  }
	  
	  BJUI.alertmsg('confirm', 'Paid Reset?',{
		  okCall:function(){ 
					BJUI.ajax('doajax', {
					    url: 'paidResetPayment.action',
					    loadingmask: true,
					    data: {json:JSON.stringify(ids)},
					    okCallback: function(json, options) {
					    	if(json.status='200'){
					    		BJUI.alertmsg('info', json.message);					    		
					    	}else{
					    		BJUI.alertmsg('error', json.message);		
					    	}
					    }
					})	
					
			  }
			  })
}


function mailInformPayment(){
	  var ids=getCheckId();
	  
	  if(ids==null||ids==undefined||ids==''){
		  BJUI.alertmsg('info', 'No row selected');	
		  return false
	  }
	  
	  BJUI.alertmsg('confirm', 'Mail Inform?',{
		  okCall:function(){ 
					BJUI.ajax('doajax', {
					    url: 'mailInformPayment.action',
					    loadingmask: true,
					    data: {json:JSON.stringify(ids)},
					    okCallback: function(json, options) {
					    	if(json.status='200'){
					    		BJUI.alertmsg('info', json.message);					    		
					    	}else{
					    		BJUI.alertmsg('error', json.message);		
					    	}
					    }
					})	
					
			  }
			  })
}

</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-payment-cashier-filter')}" id="datagrid-payment-cashier-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="150px">
        		<span>Application Date：</span>
        		</td>
        		<td width="220px">
            	<input type="text" name="applicationDate_f"  data-nobtn="true"  id="q_payment_applicationDate_f" value="" data-toggle="datepicker" size="9" data-rule="date">to:
            	<input type="text" name="applicationDate_t"  data-nobtn="true"  id="q_payment_applicationDate_t" value="" data-toggle="datepicker" size="9" data-rule="date">
        		</td>
        		<td width="150px">
        		<span>Sequential Code：</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="code" value="" id="q_payment_code" size="15">
        		</td>
        		<td>
        		<span>Cimtas ID：</span>
        		</td>
        		<td width="180px">
        		<input type="text" name="UID" value="" id="q_payment_UID" size="15">
        		</td>
        		<td width="80px">
        		<span>Urgent：</span>
        		</td>
        		<td width="80px">
            		<select name="urgent" data-toggle="selectpicker" id="q_payment_urgent"  data-width="80px">
		              <option value=""></option>
		              <option value="1">Y</option>
		              <option value="0">N</option>
	            	</select>
        		</td>
        	</tr>    
        	<tr>
        		<td colspan="8" height="5px"></td>
        	</tr>
        	<tr>
        		<td>
        		<span>Payment Subject：</span>
        		</td>
        		<td>
            	<select name="paymentSubject" data-toggle="selectpicker" id="q_payment_paymentSubject"  data-width="200px">
	              <option value=""></option>
	              <option value="1">Fixed Asset 固定资产</option>
	              <option value="2">Raw Material 原材料</option>
	              <option value="3">Consumable 消耗品</option>
	              <option value="4">Subcontractor 外包</option>
	              <option value="5">Service 服务</option>
	              <option value="6">Petty Cash备用金</option>
	              <option value="8">Travel 差旅费</option>
	              <option value="7">Other 其他</option>
            	</select>
        		</td>
        		<td>
        		<span>BU NO：</span>
        		</td>
        		<td>
            	<input type="text" name="departmentID" class="form-control" size="15" data-rule="number" >
        		</td>
        		<td>
        		<span>Status</span>
        		</td>
        		<td>
            	<select name="state" data-toggle="selectpicker" id="q_payment_state"  data-width="150px">
	              <option value="" >all</option>
	              <option value="0">Submit Required</option>
	              <option value="1">Manager Approval</option>
	              <option value="9">Manager2 Approval</option>
	              <option value="2">Audit Approval</option>
	              <option value="3">Audit Rejected</option>
	              <option value="4">Finance Approval</option>
	              <option value="5">Invalid</option>
	              <option value="6">Finance Rejected</option>
	              <option value="7">GM Approval</option>
	              <option value="8">Payment Completed</option>
            	</select>
        		</td>
        		<td colspan="2" align="center">
	        		<div class="btn-group">
	                <button type="submit" class="btn-green" data-icon="search">Search</button>
	                <button type="reset" class="btn-orange" data-icon="times">Reset</button>
	            	</div>
        		</td>
        	</tr>    
        	<tr>
        	<td colspan="8" height="5px"></td>
        	</tr>
        	<tr>
        		<td >
        		<span>Paid Date：</span>
        		</td>
        		<td width="220px">
            	<input type="text" name="paidDate_f"  data-nobtn="true"  id="q_payment_paidDate_f" value="" data-toggle="datepicker" size="9" data-rule="date">to:
            	<input type="text" name="paidDate_t"  data-nobtn="true"  id="q_payment_paidDate_t" value="" data-toggle="datepicker" size="9" data-rule="date">
        		</td>
        		<td >
        		<span>GM Approval Date：</span>
        		</td>
        		<td width="220px">
            	<input type="text" name="gmDate_f"  data-nobtn="true"  id="q_payment_paidDate_f" value="" data-toggle="datepicker" size="9" data-rule="date">to:
            	<input type="text" name="gmDate_t"  data-nobtn="true"  id="q_payment_paidDate_t" value="" data-toggle="datepicker" size="9" data-rule="date">
        		</td>
        		<td colspan="4"></td>
        	</tr>
        </table>
            
                       
			
            
            
        </div>

    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-payment-cashier-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Payment management',
        dataUrl: 'getPayment.action?queryType=cashier',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'export,|,edit,cancel,save',
        saveName:'Paid',
        toolbarCustom:[
             {name:'Paid Reset',
        	 icon:'save',
        	 class:'btn-green',
        	 function:paidResetPayment},
        	 {name:'Weekly Report',
        	 icon:'save',
        	 class:'btn-green',
        	 function:weeklyReport},
        	 {name:'Mail Inform',
        	 icon:'save',
        	 class:'btn-green',
        	 function:mailInformPayment},
        	 {name:'Finance Reject',
        	 icon:'save',
        	 class:'btn-green',
        	 function:FinanceReject}],
        delUrl:'deleteUser.action',
        editUrl: 'paidPayment.action',
        paging: {pageSize:30, pageCurrent:1},
        linenumberAll: true,
        showCheckboxcol: true,
        contextMenuB: true,
        hScrollbar: true,
        filterThead:false,
        exportOption: {type:'file', options:{url:'exportPaymentExcel.action?queryType=cashier', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-payment-cashier-query')}},
        printPDFOption: {type:'file', options:{url:'exportPaymentExcel.action?queryType=cashier', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-payment-cashier-query')}},
        printPDFOption2: {type:'file', options:{url:'exportPaymentExcel.action?queryType=cashier', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-payment-cashier-query')}}
    }">
        <thead>
            <tr>
            	<th data-options="{render:datagrid_tree_operation,align:'center'}">Operation</th>
            	<th data-options="{name:'id',width:150,align:'center',finalWidth:'true',hide:'true'}">id</th>
            	<th data-options="{name:'refNoofBank',width:200,align:'center',finalWidth:'true'}">Ref.No. of Bank</th>
            	<th data-options="{name:'applicationDate',width:150,align:'center',finalWidth:'true',edit:false}" >Application Date</th>
				<th data-options="{name:'state',width:150,align:'center',finalWidth:'true',type:'select', items:datagrid_paymentState,edit:false}">Approval Status</th>
				<th data-options="{name:'GMApproveDate',width:150,align:'center',finalWidth:'true',edit:false}">GM Approval Date</th>
				<th data-options="{name:'UID',width:150,align:'center',finalWidth:'true',edit:false}">Cimtas ID</th>
				<th data-options="{name:'UName',width:150,align:'center',finalWidth:'true',edit:false}">User Name</th>
				<th data-options="{name:'deptManagerID',width:150,align:'center',finalWidth:'true',edit:false}">Manager Cimtas ID</th>
				<th data-options="{name:'deptManager',width:150,align:'center',finalWidth:'true',edit:false}">Manager Name</th>
				<th data-options="{name:'code',width:150,align:'center',finalWidth:'true',edit:false}">Sequential Code</th>
				<th data-options="{name:'urgent',width:60,align:'center' ,finalWidth:'true',type:'select', items:datagrid_urgent,edit:false}">Urgent</th>
				<th data-options="{name:'paymentSubject',width:200,align:'center',finalWidth:'true',type:'select', items:datagrid_paymentSubject,edit:false}">Payment Subject</th>
				<th data-options="{name:'currency_1',width:80,align:'right',finalWidth:'true',edit:false}">Currency</th>
				<th data-options="{name:'amountInFigures',width:120,align:'right',finalWidth:'true',edit:false}">Amount</th>
				<th data-options="{name:'usageDescription',width:400,align:'left',finalWidth:'true',edit:false}">Usage Description</th>            
			</tr>
        </thead>
    </table>
</div>