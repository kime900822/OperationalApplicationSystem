<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	
	$.CurrentNavtab.find('#upfile_invoice').uploadify({
	        swf           : 'B-JUI/plugins/uploadify/uploadify.swf',
	        uploader      : 'savefile.action',
	        width         : 120,
	        onUploadSuccess:function(file, data, response){
	        	if(data.statusCode='200'){
	        		$.CurrentNavtab.find('#upfile_invoice_list').append(fileToTr(file.name,file.name));	    
	        	}else{
	        		BJUI.alertmsg('error', data.message); 
	        	}
	        	    	
	        }
	    });
	 
	$.CurrentNavtab.find('#upfile_contract').uploadify({
	        swf           : 'B-JUI/plugins/uploadify/uploadify.swf',
	        uploader      : 'savefile.action',
	        width         : 120,
	        onUploadSuccess:function(file, data, response){
	        	if(data.statusCode='200'){
	        		$.CurrentNavtab.find('#upfile_contract_list').append(fileToTr(file.name,file.name));
	        	}else{
	        		BJUI.alertmsg('error', data.message); 
	        	}

	        	
	        }
	    });

	 
	$.CurrentNavtab.find('#upfile_other').uploadify({
	        swf           : 'B-JUI/plugins/uploadify/uploadify.swf',
	        uploader      : 'savefile.action',
	        width         : 120,
	        onUploadSuccess:function(file, data, response){
	        	if(data.statusCode='200'){
	        		$.CurrentNavtab.find('#upfile_other_list').append(fileToTr(file.name,file.name));	
	        	}else{
	        		BJUI.alertmsg('error', data.message); 
	        	}

	        	
	        }
	    });


	//初始化全部缩进
	$.CurrentNavtab.find('tr.table-parent').each(
			function(){
				  $(this)
				  .toggleClass("table-selected") 
				  .siblings('.child_'+this.id).toggle();
	});
	
	//表格缩进展开
	$.CurrentNavtab.find('tr.table-parent').click(function(){ 
		  $(this)
		  .toggleClass("table-selected") 
		  .siblings('.child_'+this.id).toggle(); 
	});
	


	$.CurrentNavtab.find('#payment-save').click(function(){		
		savePayment();
	});
	
	$.CurrentNavtab.find('#payment-submit').click(function(){		
		submitPayment();
	});
	
	$.CurrentNavtab.find('#payment-approve').click(function(){		
		approvePayment();
	});
	
	$.CurrentNavtab.find('#payment-reject').click(function(){		
		rejectPayment();
	});
	
	$.CurrentNavtab.find('#payment-assign').click(function(){		
		assignPayment();
	});
	
	$.CurrentNavtab.find('#payment-acc').click(function(){		
		accPayment();
	});
	
	$.CurrentNavtab.find('#payment-print').click(function(){	
		printPayment();
	});
	
	$.CurrentNavtab.find('#payment-invalid').click(function(){		
		invalidPayment();
	});
	
	$.CurrentNavtab.find('#payment-return').click(function(){		
		returnPayment();
	});
		
	$.CurrentNavtab.find('#payment-delete').click(function(){		
		deletePayment();
	});

	if('${param.id}'!=null&&'${param.id}'!=''){
		dataToFace();
	}else{
		addBeneficiary('');
		showButton('','');
	}
	

	//初始化金额
	changeAmount();
	isChange();

})

function addBeneficiary(o){
	BJUI.ajax('doajax', {
	    url: 'getAllBeneficiary.action',
	    loadingmask: true,
	    okCallback: function(json, options) {
            $.each(json, function (i, item) {
                $.CurrentNavtab.find('#j_payment_beneficiary').append("<option value='" + item.accno + "'>" + item.name + "</option>")           
            })
            $.CurrentNavtab.find('#j_payment_beneficiary').selectpicker().selectpicker('val',o).selectpicker('refresh');
            changeBeneficiary();
	    }
	});	
}


function savePayment(){
	var o=faceToDate();	
	BJUI.ajax('doajax', {
	    url: 'savePayment.action',
	    loadingmask: true,
	    data:{json:JSON.stringify(o),file_invoice:listToString($.CurrentNavtab.find('#upfile_invoice_list')),file_contract:listToString($.CurrentNavtab.find('#upfile_contract_list')),file_other:listToString($.CurrentNavtab.find('#upfile_other_list'))},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find('#payment-submit').show();
            	 $.CurrentNavtab.find("#j_payment_id").val(json.params.id);
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});		
}



function deletePayment(){
	BJUI.ajax('doajax', {
	    url: 'deletePayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find('#payment-delete').hide();
         		 $("input[id*='j_payment']").attr('disabled','disabled');
        		 $("select[id*='j_payment']").attr('disabled','disabled');
        		 $("textarea[id*='j_payment']").attr('disabled','disabled')
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
	
}

function submitPayment(){
	BJUI.ajax('doajax', {
	    url: 'submitPayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find('#payment-save').hide();
         		 $.CurrentNavtab.find('#payment-submit').hide();
         		 $("input[id*='j_payment']").attr('disabled','disabled');
        		 $("select[id*='j_payment']").attr('disabled','disabled');
        		 $("textarea[id*='j_payment']").attr('disabled','disabled')
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
	
}

function approvePayment(){
	BJUI.ajax('doajax', {
	    url: 'approvePayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find('#payment-approve').hide();
            	 $.CurrentNavtab.find('#payment-print').show();
         		 $.CurrentNavtab.find('#payment-reject').hide();
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
	
}

function rejectPayment(){
	BJUI.ajax('doajax', {
	    url: 'rejectPayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find('#payment-approve').hide();
         		 $.CurrentNavtab.find('#payment-reject').hide();
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
	
}

function accPayment(){
	BJUI.ajax('doajax', {
	    url: 'accPayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val(),documentAudit:'${user.name}'},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find('#payment-assign').hide();
         		 $.CurrentNavtab.find('#payment-acc').hide();
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
	
}


function assignPayment(){
	
	var r=BJUI.dialog({
	    id:'assign',
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val()},
	    url:'sign/payment/paymentAssign.jsp',
	    width:550,
	    height:200,
	    title:'Assign',
	    onClose:function(){BJUI.navtab('reload')}
	});

	
/* 	BJUI.ajax('doajax', {
	    url: 'assignPayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find('#payment-assign').hide();
         		 $.CurrentNavtab.find('#payment-acc').hide();
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	 */
	
}

function returnPayment(){
	BJUI.ajax('doajax', {
	    url: 'returnPayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val(),returnDescription:CurrentNavtab.find("#payment-returnDescription").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message);
            	 showButton('5','');
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
	
}


function invalidPayment(){
	BJUI.ajax('doajax', {
	    url: 'returnPayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val(),invalidDescription:CurrentNavtab.find("#payment-invalidDescription").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 showButton('5','');
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
	
}

function printPayment(){
	var pid=$.CurrentNavtab.find("#j_payment_id").val();
	BJUI.ajax('doajax', {
	    url: 'printPayment.action',
	    loadingmask: true,
	    data:{id:pid},	   
	    okalert:false,
	    okCallback: function(json, options) {
            if(json.status='200'){
            	window.open("sign\\payment\\paymentPrint.jsp?id="+pid);  
            	$.CurrentNavtab.find("#j_payment_code").val(json.params.code);
        		
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
	
}

//change选项绑定事件
function isChange(){
	$.CurrentNavtab.find("#j_payment_beneficiaryChange").on('ifChecked',function(){
		$.CurrentNavtab.find("#j_payment_beneficiary_tr").attr("style","background-color: #9ACD32");
	})
	$.CurrentNavtab.find("#j_payment_beneficiaryChange").on('ifUnchecked',function(){
		$.CurrentNavtab.find("#j_payment_beneficiary_tr").removeAttr("style");
	})
	$.CurrentNavtab.find("#j_payment_beneficiaryAccountNOChange").on('ifChecked',function(){
		$.CurrentNavtab.find("#j_payment_beneficiaryAccountNO_tr").attr("style","background-color: #EEC900");
	})
	$.CurrentNavtab.find("#j_payment_beneficiaryAccountNOChange").on('ifUnchecked',function(){
		$.CurrentNavtab.find("#j_payment_beneficiaryAccountNO_tr").removeAttr("style");
	})
	
}

//删除当前所在行
function deleteFile(o){
	$.CurrentNavtab.find(o).parent().parent().remove();	
	
}

function getFile(path){
	BJUI.ajax('ajaxdownload', {
	    url:'getFile.action',
	    data:{dfile:path}
	})
	
}

function listToString(id){
	var tr=$.CurrentNavtab.find(id).find('tr');
	var s="";
	$.each(tr,function(i,n){
		if(i>0){
			s=s+$.CurrentNavtab.find(n).children().eq(0).children().html()+"|";		
		}
	})
	return s;
}


function fileToTr(name,path){
	return "<tr><td align='center'><a onclick=\"getFile('"+path.replace('\\','\\\\')+"')\">"+name+"</></td><td align='center'><a onclick='deleteFile(this)'>删除</a></td></tr>"
}


function showButton(state,print,uid,documentAuditid,deptManagerid){
	if(state==''){//新建 退回
		$.CurrentNavtab.find('#payment-save').show();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').hide();
		$.CurrentNavtab.find('#payment-delete').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
	}else if(state=='0'&&uid=='${user.uid}'){//保存后可提交
		$.CurrentNavtab.find('#payment-save').show();
		$.CurrentNavtab.find('#payment-submit').show();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').hide();
		$.CurrentNavtab.find('#payment-delete').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
	}else if(state=="4"&&documentAuditid=='${user.uid}'){//财务处理完成
		$.CurrentNavtab.find('#payment-save').hide();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-delete').hide();
		$.CurrentNavtab.find('#payment-print').show();
		if(print=='1'){
			$.CurrentNavtab.find('#payment-invalid-tr').show();
			$.CurrentNavtab.find('#payment-return-tr').show();		
		}else{
			$.CurrentNavtab.find('#payment-invalid-tr').hide();
			$.CurrentNavtab.find('#payment-return-tr').hide();					
		}
		$("input[id*='j_payment']").attr('disabled','disabled');
		$("select[id*='j_payment']").attr('disabled','disabled');
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
	}else if(state=="4"&&documentAuditid!='${user.uid}'){//财务处理完成  非财务人员查看。可打印
		$.CurrentNavtab.find('#payment-save').hide();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').show();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();					
		$.CurrentNavtab.find('#payment-delete').hide();
		$("input[id*='j_payment']").attr('disabled','disabled');
		$("select[id*='j_payment']").attr('disabled','disabled');	
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
	}else if(state=="1"&&deptManagerid=='${user.uid}'){//部门经理审批
		$.CurrentNavtab.find('#payment-save').hide();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').show();
		$.CurrentNavtab.find('#payment-reject').show();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-delete').hide();
		$.CurrentNavtab.find('#payment-print').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();		
		$("input[id*='j_payment']").attr('disabled','disabled');
		$("select[id*='j_payment']").attr('disabled','disabled');
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
	}else if(state=="2"&&documentAuditid=='${user.uid}'){//审批通过 财务处理
		$.CurrentNavtab.find('#payment-save').hide();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').show();
		$.CurrentNavtab.find('#payment-acc').show();
		$.CurrentNavtab.find('#payment-print').show();
		$.CurrentNavtab.find('#payment-delete').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
		$.CurrentNavtab.find('#j_payment_documentAudit').val('${user.name}')
		$("input[id*='j_payment']").attr('disabled','disabled');
		$("select[id*='j_payment']").attr('disabled','disabled');
		$.CurrentNavtab.find('#j_payment_refNoofBank').removeAttr('disabled');
		$.CurrentNavtab.find('#j_payment_refNoofBank').removeAttr('readonly').attr('data-rule','required');
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
 	}else if(state=="2"&&documentAuditid!='${user.uid}'){//审批通过 普通员工打印
		$.CurrentNavtab.find('#payment-save').hide();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').show();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
		$.CurrentNavtab.find('#payment-delete').hide();
		$("input[id*='j_payment']").attr('disabled','disabled');
		$("select[id*='j_payment']").attr('disabled','disabled'); 
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
	}else if(state=="3"){//审批未通过，单据作废，
		$.CurrentNavtab.find('#payment-save').hide();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').hide();
		$.CurrentNavtab.find('#payment-delete').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
		$("input[id*='j_payment']").attr('disabled','disabled');
		$("select[id*='j_payment']").attr('disabled','disabled');
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
	}else if(state=="5"){//单据作废
		$.CurrentNavtab.find('#payment-save').hide();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').hide();
		$.CurrentNavtab.find('#payment-delete').show();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
		$("input[id*='j_payment']").attr('disabled','disabled');
		$("select[id*='j_payment']").attr('disabled','disabled');
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
	}else{
		$.CurrentNavtab.find('#payment-save').hide();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
		$.CurrentNavtab.find('#payment-delete').hide();
		$("input[id*='j_payment']").attr('disabled','disabled');
		$("select[id*='j_payment']").attr('disabled','disabled');
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
	}
	
	
	
}

function changeBeneficiary(){
	$.CurrentNavtab.find('#j_payment_beneficiaryAccountNO').val($.CurrentNavtab.find('#j_payment_beneficiary').val())
}

function dataToFace(){
	
	BJUI.ajax('doajax', {
	    url: 'getPaymentByID.action',
	    loadingmask: true,
	    data:{id:'${param.id}'},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	$.CurrentNavtab.find("#j_payment_applicationDate").val(json.applicationDate);
            	$.CurrentNavtab.find("#j_payment_requestPaymentDate").val(json.requestPaymentDate);
            	$.CurrentNavtab.find("#j_payment_contacturalPaymentDate").val(json.contacturalPaymentDate);
            	$.CurrentNavtab.find("#j_payment_code").val(json.code);
            	if(json.payType=='Cash'){
            		$.CurrentNavtab.find("#j_payment_cash").iCheck('check'); 
            	}else if(json.payType=='Banking'){
            		$.CurrentNavtab.find("#j_payment_banking").iCheck('check'); 
            	}else if(json.payType=='AdvanceWriteoff'){
            		$.CurrentNavtab.find("#j_payment_advanceWriteoff").iCheck('check'); 
            	}
            	if(json.urgent=='1'){
            		$.CurrentNavtab.find("#j_payment_urgent").iCheck('check'); 
            	}
            	$.CurrentNavtab.find("#j_payment_UID").val(json.UID+'-'+json.UName);
            	$.CurrentNavtab.find("#j_payment_departmentID").val(json.departmentName+'-'+json.departmentID);
            	addBeneficiary(json.beneficiaryAccountNO);
                if(json.beneficiaryChange=='1'){
            		$.CurrentNavtab.find("#j_payment_beneficiaryChange").iCheck('check'); 
            		$.CurrentNavtab.find("#j_payment_beneficiary_tr").attr("style","background-color: #9ACD32");
            	}
                if(json.beneficiaryAccountNOChange=='1'){
            		$.CurrentNavtab.find("#j_payment_beneficiaryAccountNOChange").iCheck('check'); 
            		$.CurrentNavtab.find("#j_payment_beneficiaryAccountNO_tr").attr("style","background-color: #EEC900");
            	}
                $.CurrentNavtab.find("#j_payment_paymentSubject").selectpicker().selectpicker('val',json.paymentSubject).selectpicker('refresh');
                $.CurrentNavtab.find("#j_payment_paymentTerm").selectpicker().selectpicker('val',json.paymentTerm).selectpicker('refresh');
                
            	$.CurrentNavtab.find("#j_payment_paymentDays_1").selectpicker().selectpicker('val',json.paymentDays_1).selectpicker('refresh');
            	$.CurrentNavtab.find("#j_payment_receivingOrApprovalDate_1").val(json.receivingOrApprovalDate_1);
            	$.CurrentNavtab.find("#j_payment_PONo_1").val(json.PONo_1);
            	$.CurrentNavtab.find("#j_payment_currency_1").selectpicker().selectpicker('val',json.currency_1).selectpicker('refresh');
            	if(json.amount_1!=''&&json.amount_1!=null){
            		$.CurrentNavtab.find("#j_payment_amount_1_t").val(json.amount_1);
            	}
            	$.CurrentNavtab.find("#j_payment_paymentDays_2").selectpicker().selectpicker('val',json.paymentDays_2).selectpicker('refresh');
            	$.CurrentNavtab.find("#j_payment_receivingOrApprovalDate_2").val(json.receivingOrApprovalDate_2);
            	$.CurrentNavtab.find("#j_payment_PONo_2").val(json.PONo_2);
            	$.CurrentNavtab.find("#j_payment_currency_2").selectpicker().selectpicker('val',json.currency_2).selectpicker('refresh');
            	if(json.amount_2!=''&&json.amount_2!=null){
            		$.CurrentNavtab.find("#j_payment_amount_2_t").val(json.amount_2);
            	}
            		
            	$.CurrentNavtab.find("#j_payment_paymentDays_3").selectpicker().selectpicker('val',json.paymentDays_3).selectpicker('refresh');
            	$.CurrentNavtab.find("#j_payment_receivingOrApprovalDate_3").val(json.receivingOrApprovalDate_3);
            	$.CurrentNavtab.find("#j_payment_PONo_3").val(json.PONo_3);
            	$.CurrentNavtab.find("#j_payment_currency_3").selectpicker().selectpicker('val',json.currency_3).selectpicker('refresh');
            	if(json.amount_3!=''&&json.amount_3!=null){
            		$.CurrentNavtab.find("#j_payment_amount_3_t").val(json.amount_3);
            	}
            		
            	$.CurrentNavtab.find("#j_payment_paymentDays_4").selectpicker().selectpicker('val',json.paymentDays_4).selectpicker('refresh');
            	$.CurrentNavtab.find("#j_payment_receivingOrApprovalDate_4").val(json.receivingOrApprovalDate_4);
            	$.CurrentNavtab.find("#j_payment_PONo_4").val(json.PONo_4);
            	$.CurrentNavtab.find("#j_payment_currency_4").selectpicker().selectpicker('val',json.currency_4).selectpicker('refresh');
            	if(json.amount_4!=''&& json.amount_4==null){
            		$.CurrentNavtab.find("#j_payment_amount_4_t").val(json.amount_4);
            	}
            		
            	$.CurrentNavtab.find("#j_payment_paymentDays_5").selectpicker().selectpicker('val',json.paymentDays_5).selectpicker('refresh');
            	$.CurrentNavtab.find("#j_payment_receivingOrApprovalDate_5").val(json.receivingOrApprovalDate_5);
            	$.CurrentNavtab.find("#j_payment_PONo_5").val(json.PONo_5);
            	$.CurrentNavtab.find("#j_payment_currency_5").selectpicker().selectpicker('val',json.currency_5).selectpicker('refresh');
            	if(json.amount_5!=''&& json.amount_5!=null){
            		$.CurrentNavtab.find("#j_payment_amount_5_t").val(json.amount_5);
            	}
            		
            	$.CurrentNavtab.find("#j_payment_paymentDays_6").selectpicker().selectpicker('val',json.paymentDays_6).selectpicker('refresh');
            	$.CurrentNavtab.find("#j_payment_receivingOrApprovalDate_6").val(json.receivingOrApprovalDate_6);
            	$.CurrentNavtab.find("#j_payment_PONo_6").val(json.PONo_6);
            	$.CurrentNavtab.find("#j_payment_currency_6").selectpicker().selectpicker('val',json.currency_6).selectpicker('refresh');
            	if(json.amount_6!=''&& json.amount_6!=null){
            		$.CurrentNavtab.find("#j_payment_amount_6_t").val(json.amount_6);
            	}
            		
            	$.CurrentNavtab.find("#j_payment_supplierCode").val(json.supplierCode);
            	$.CurrentNavtab.find("#j_payment_refNoofBank").val(json.refNoofBank);
            	$.CurrentNavtab.find("#j_payment_usageDescription").val(json.usageDescription);
            	if(json.amountInFigures!=''&&json.amountInFigures!=null){
            		$.CurrentNavtab.find("#j_payment_amountInFigures").val(json.amountInFigures);
            	}
            	
            	$.CurrentNavtab.find("#j_payment_documentAudit").val(json.documentAudit);
            	$.CurrentNavtab.find("#j_payment_deptManager").val(json.deptManager);
            		
            	if(json.file_invoice!=undefined&&json.file_invoice!=""){
            		var file=json.file_invoice.split('|');
            		$.each(file,function(i,n){
            			var filename=n.split('/')[1];
            			if(filename!=undefined){
            				$.CurrentNavtab.find('#upfile_invoice_list').append(fileToTr(filename,n));	   
            			}
            		})
            	}
            	if(json.file_other!=undefined&&json.file_other!=""){
            		var file=json.file_other.split('|');
            		$.each(file,function(i,n){
            			var filename=n.split('/')[1];
            			if(filename!=undefined){
            				$.CurrentNavtab.find('#upfile_other_list').append(fileToTr(filename,n));	 
            			}
            		})
            	}
            	if(json.file_contract!=undefined&&json.file_contract!=""){
            		var file=json.file_contract.split('|');
            		$.each(file,function(i,n){
            			var filename=n.split('/')[1];
            			if(filename!=undefined){
            				$.CurrentNavtab.find('#upfile_contract_list').append(fileToTr(filename,n));
            			}
            			
            		})
            	}
            		
            	$.CurrentNavtab.find("#j_payment_id").val(json.id);
            	$.CurrentNavtab.find("#j_payment_state").val(json.state);
            	showButton(json.state,json.isPrint,json.UID,json.documentAuditID,json.deptManagerID);
            	changeAmount();
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	

	
}


function faceToDate(){
	var o=$.CurrentNavtab.find("#j_payment_form").serializeJson();
	o.beneficiary=$.CurrentNavtab.find('#j_payment_beneficiary').find("option:selected").text();
	o.UID='${user.uid}';
	o.UName='${user.name}';
	o.departmentName='${user.department.name}';
	o.departmentID='${user.department.did}';
		
	o.invoice=$.CurrentNavtab.find("#j_payment_update_invoice").val();
	o.contract=$.CurrentNavtab.find("#j_payment_update_contract").val();
	o.other=$.CurrentNavtab.find("#j_payment_update_other").val();	

	return o;
}



//金额变动
function changeAmount(){
	
	
	
	var amount1=$.CurrentNavtab.find("#j_payment_amount_1_t").val().replace(",", "").replace(" ", "");
	var amount2=$.CurrentNavtab.find("#j_payment_amount_2_t").val().replace(",", "").replace(" ", "");
	var amount3=$.CurrentNavtab.find("#j_payment_amount_3_t").val().replace(",", "").replace(" ", "");
	var amount4=$.CurrentNavtab.find("#j_payment_amount_4_t").val().replace(",", "").replace(" ", "");
	var amount5=$.CurrentNavtab.find("#j_payment_amount_5_t").val().replace(",", "").replace(" ", "");
	var amount6=$.CurrentNavtab.find("#j_payment_amount_6_t").val().replace(",", "").replace(" ", "");
	
	var c1=$.CurrentNavtab.find("#j_payment_amount_1_t").val(formatCurrency(amount1)).val()
	var c2=$.CurrentNavtab.find("#j_payment_amount_2_t").val(formatCurrency(amount2)).val()
	var c3=$.CurrentNavtab.find("#j_payment_amount_3_t").val(formatCurrency(amount3)).val()
	var c4=$.CurrentNavtab.find("#j_payment_amount_4_t").val(formatCurrency(amount4)).val()
	var c5=$.CurrentNavtab.find("#j_payment_amount_5_t").val(formatCurrency(amount5)).val()
	var c6=$.CurrentNavtab.find("#j_payment_amount_6_t").val(formatCurrency(amount6)).val()
	
	
	var total=0;
	if(amount1!="0.00"&&amount1!=""){
		$.CurrentNavtab.find("#j_payment_amount_1").val(amount1)
		total+=parseFloat(amount1);
		$.CurrentNavtab.find("#row_01_title").html("PO&nbsp1 &nbsp&nbsp&nbsp&nbsp PO No:"+$.CurrentNavtab.find("#j_payment_PONo_1").val()+"&nbsp&nbsp  Amount(金额):"+$.CurrentNavtab.find("#j_payment_currency_1").val()+"&nbsp&nbsp"+c1);
	}else{
		$.CurrentNavtab.find("#row_01_title").html("PO&nbsp1");
	}
	if(amount2!="0.00"&&amount2!=""){
		$.CurrentNavtab.find("#j_payment_amount_2").val(amount2)
		total+=parseFloat(amount2);
		$.CurrentNavtab.find("#row_02_title").html("PO&nbsp2 &nbsp&nbsp&nbsp&nbsp PO No:"+$.CurrentNavtab.find("#j_payment_PONo_2").val()+"&nbsp&nbsp  Amount(金额):"+$.CurrentNavtab.find("#j_payment_currency_2").val()+"&nbsp&nbsp"+c2);
	}else{
		$.CurrentNavtab.find("#row_02_title").html("PO&nbsp2");
	}
	if(amount3!="0.00"&&amount3!=""){
		$.CurrentNavtab.find("#j_payment_amount_3").val(amount3)
		total+=parseFloat(amount3);
		$.CurrentNavtab.find("#row_03_title").html("PO&nbsp3 &nbsp&nbsp&nbsp&nbsp PO No:"+$.CurrentNavtab.find("#j_payment_PONo_3").val()+"&nbsp&nbsp  Amount(金额):"+$.CurrentNavtab.find("#j_payment_currency_3").val()+"&nbsp&nbsp"+c3);
	}else{
		$.CurrentNavtab.find("#row_03_title").html("PO&nbsp3");
	}
	if(amount4!="0.00"&&amount4!=""){
		$.CurrentNavtab.find("#j_payment_amount_4").val(amount4)
		total+=parseFloat(amount4);
		$.CurrentNavtab.find("#row_04_title").html("PO&nbsp4 &nbsp&nbsp&nbsp&nbsp PO No:"+$.CurrentNavtab.find("#j_payment_PONo_4").val()+"&nbsp&nbsp  Amount(金额):"+$.CurrentNavtab.find("#j_payment_currency_4").val()+"&nbsp&nbsp"+c4);
	}else{
		$.CurrentNavtab.find("#row_04_title").html("PO&nbsp4");
	}
	if(amount5!="0.00"&&amount5!=""){
		$.CurrentNavtab.find("#j_payment_amount_5").val(amount5)
		total+=parseFloat(amount5);
		$.CurrentNavtab.find("#row_05_title").html("PO&nbsp5 &nbsp&nbsp&nbsp&nbsp PO No:"+$.CurrentNavtab.find("#j_payment_PONo_5").val()+"&nbsp&nbsp  Amount(金额):"+$.CurrentNavtab.find("#j_payment_currency_5").val()+"&nbsp&nbsp"+c5);
	}else{
		$.CurrentNavtab.find("#row_05_title").html("PO&nbsp5");
	}
	if(amount6!="0.00"&&amount6!=""){
		$.CurrentNavtab.find("#j_payment_amount_6").val(amount6)
		total+=parseFloat(amount6);
		$.CurrentNavtab.find("#row_06_title").html("PO&nbsp6 &nbsp&nbsp&nbsp&nbsp PO No:"+$.CurrentNavtab.find("#j_payment_PONo_6").val()+"&nbsp&nbsp  Amount(金额):"+$.CurrentNavtab.find("#j_payment_currency_6").val()+"&nbsp&nbsp"+c6);
	}else{
		$.CurrentNavtab.find("#row_06_title").html("PO&nbsp6");
	}
	
	$.CurrentNavtab.find("#j_payment_amountInFigures").val(total);
	$.CurrentNavtab.find("#j_payment_amountInFigures_t").val(formatCurrency(total));
	$.CurrentNavtab.find("#j_payment_amountInWords").val(smalltoBIG(total));
	
	
	
}

function changeCurrency(o){
	var a=$(o).val();
	$.CurrentNavtab.find("#j_payment_currency_1").selectpicker().selectpicker('val',a).selectpicker('refresh');
	$.CurrentNavtab.find("#j_payment_currency_2").selectpicker().selectpicker('val',a).selectpicker('refresh');
	$.CurrentNavtab.find("#j_payment_currency_3").selectpicker().selectpicker('val',a).selectpicker('refresh');
	$.CurrentNavtab.find("#j_payment_currency_4").selectpicker().selectpicker('val',a).selectpicker('refresh');
	$.CurrentNavtab.find("#j_payment_currency_5").selectpicker().selectpicker('val',a).selectpicker('refresh');
	$.CurrentNavtab.find("#j_payment_currency_6").selectpicker().selectpicker('val',a).selectpicker('refresh');
	changeAmount();
}

function checkPoNO(o){
	 var str = $(o).val();
     var ret =  /^[A-Z]{2}\d{6,7}$/;
     if(!ret.test(str)){
    	 $.CurrentNavtab.find(o).val("")
    	 BJUI.alertmsg('error', 'Plese Enter Right Type! Like YY00000'); 
     }
     changeAmount();

     
}

</script>
<div class="bjui-pageContent">
    <div class="bs-example" style="width:1000px">
        <form id="j_payment_form" data-toggle="ajaxform">
			<input type="hidden" name="id" id="j_payment_id" value="">
			<input type="hidden" name="state" id="j_payment_state" value="">
            <div class="bjui-row-0" align="center">
            <h2 class="row-label">Payment Application Form 付款申请单</h2><br> 
            </div>
			<table class="table" style="font-size:12px;">
				<tr>
					<td width="250px">Application Date<br>（申请日期）</td>
					<td width="250px"><input type="text" size="19" name="applicationDate" data-nobtn="true" id="j_payment_applicationDate" value="" data-toggle="datepicker" data-rule="required;date"></td>
					<td width="250px">Request Payment Date<br>(要求付款日期）</td>
					<td width="250px"><input type="text" size="19" name="requestPaymentDate" data-nobtn="true" id="j_payment_requestPaymentDate" value="" data-toggle="datepicker" data-rule="required;date"></td>					
				</tr>
				<tr>
					<td>
						Contactural Payment Date<br>（合同付款日期）
					</td>
					<td>
						<input type="text" name="contacturalPaymentDate" size="19" value="" data-nobtn="true" id="j_payment_contacturalPaymentDate"  data-toggle="datepicker" data-rule="required;date">
					</td>
					<td>
						CODE(流水码)
					</td>
					<td>
						<input type="text" name="code" value="" readonly="" id="j_payment_code" size="19">
					</td>					
				</tr>
				<tr>
					<td>
						支付方式
					</td>
					<td>
						<input type="radio" name="payType" data-toggle="icheck" id="j_payment_cash" value="Cash" data-label="支付现金 <br>Cash">
					</td>
					<td>
						<input type="radio" name="payType" data-toggle="icheck" id="j_payment_banking" value="Banking" data-label="银行支付 <br>Banking">
					</td>	
					<td>
						<input type="radio" name="payType" data-toggle="icheck" id="j_payment_advanceWriteoff" value="AdvanceWriteoff" data-label="核销预付  <br>Advance Write-off (Amount)">
					</td>				
				</tr>
				<tr>

					<td>
						Urgent
					</td>
					<td>
						<input type="checkbox" name="urgent"  data-toggle="icheck" id="j_payment_urgent" value="1" data-label="">
					</td>		
					<td colspan="2"></td>			
				</tr>
				<tr>
					<td>
						申请人<br>Applicant:
					</td>
					<td>
						<input type="text" name="UID" value="${user.uid}-${user.name}" id="j_payment_UID" readonly="" data-rule="required" size="19">
					</td>
					<td>
						所属部门<br>Department of Applicant:
					</td>
					<td>
						<input type="text" name="departmentID" value="${user.department.name}-${user.department.did}" id="j_payment_departmentID" readonly="" data-rule="required" size="19">
					</td>					
				</tr>
				<tr>
					<td>
						收款人（全称）<br>Beneficiary:
					</td>
					<td id="j_payment_beneficiary_tr">
						<select name="beneficiary" id="j_payment_beneficiary" data-toggle="selectpicker" data-rule="required" onchange="changeBeneficiary()" data-width="190px">
                        	<option value=""></option>
                    	</select>
					</td>
					<td>
						银行及帐号<br>Beneficiary Account NO.
					</td>
					<td id="j_payment_beneficiaryAccountNO_tr">
						<input type="text" name="beneficiaryAccountNO" id="j_payment_beneficiaryAccountNO" value="" readonly=""  size="19">
					</td>					
				</tr>
				<tr>
					<td>
						收款人变更<br>Change
					</td>
					<td>
						<input  type="checkbox" name="beneficiaryChange" id="j_payment_beneficiaryChange" data-toggle="icheck" value="1" data-label="">
					</td>
					<td>
						银行及账号变更<br>Change
					</td>
					<td>
						<input  type="checkbox" name="beneficiaryAccountNOChange" id="j_payment_beneficiaryAccountNOChange" data-toggle="icheck" value="1" data-label="">
					</td>					
				</tr>
				
				<!-- 付款   -->
				<tr>
					<td>
						付款项目<br>Payment Subject
					</td>
					<td>
						<select name="paymentSubject" data-toggle="selectpicker" id="j_payment_paymentSubject"  data-rule="required" data-width="190px">
	                        <option value=""></option>
	                        <option value="1">Fixed Asset 固定资产</option>
	                        <option value="2">Raw Material 原材料</option>
	                        <option value="3">Consumable 消耗品</option>
	                        <option value="4">Subcontractor 外包</option>
	                        <option value="5">Service 服务</option>
	                        <option value="6">Petty Cash 备用金</option>
	                        <option value="7">Other 其他</option>
                    	</select>
					</td>
					<td>
						结算期<br>Payment Term
					</td>	
					<td>
						<select name="paymentTerm" data-toggle="selectpicker" id="j_payment_paymentTerm"  data-rule="required" data-width="190px" >
	                        <option value=""></option>
	                        <option value="1">Advance 预付款</option>
	                        <option value="2">Payment at sight 见票即付</option>
	                        <option value="3">Upon receiving 收货后</option>
	                        <option value="4">Upon Approval 验收后</option>
	                        <option value="5">Upon invoice 见票后</option>
	                        <option value="6">Other 其他</option>
                    	</select>
					</td>				
				</tr>
				
				
				
				<tr>
					<td colspan="4">
						<table id="payterm" class="table" width="100%">
							<!-- Advance预付款 -->
							<tr class="table-parent" id="row_01">
								<td colspan="4">
									<label id="row_01_title">PO&nbsp1</label>
								</td>
							</tr>
							<tr class="child_row_01 child_row">
								<td>
									结算期 <br>Payment Term
								</td>
								<td>
									<select name="paymentDays_1" id="j_payment_paymentDays_1" data-toggle="selectpicker" data-width="190px" >
				                        <option value=""></option>
				                        <option value="30">30Days</option>
				                        <option value="45">45Days</option>
				                        <option value="60">60Days</option>
				                        <option value="90">90Days</option>
				                        <option value="120">120Days</option>
			                    	</select>
								</td>
								<td>
									收货或验收日期<br>Receiving or Approval date
								</td>
								<td>
									<input type="text" name="receivingOrApprovalDate_1" size="19" data-nobtn="true" id="j_payment_receivingOrApprovalDate_1" value="" data-toggle="datepicker" data-rule="date">
								</td>
							</tr>
							<tr class="child_row_01 child_row">
								<td>
									订单号<br>PO No.
								</td>
								<td>
									<input type="text" name="PONo_1" id="j_payment_PONo_1"  value="" size="19" onchange="checkPoNO(this)">
								</td>
								<td>
									<label class="row-label">币别<br>Currency</label>
								</td>
								<td>
									<select name="currency_1" data-toggle="selectpicker" id="j_payment_currency_1" data-width="190px" onchange="changeCurrency(this)">
				                        <option value=""></option>
				                        <option value="RMB">RMB</option>
				                        <option value="USD">USD</option>
				                        <option value="EUR">EUR</option>
				                        <option value="GBP">GBP</option>
			                    	</select>
								</td>
							</tr>
							<tr class="child_row_01 child_row">
								<td>
									金额<br>Amount
								</td>
								<td>
									<input type="text" name="amount_1_t" id="j_payment_amount_1_t" value="" data-rule="number" onchange="changeAmount()" size="19">
									<input type="hidden" name="amount_1" id="j_payment_amount_1" value="">
								</td>
								<td colspan="2">
								</td>
							</tr>
							
							
							<!-- Payment at sight 见票即付-->
							<tr class="table-parent" id="row_02">
								<td colspan="4">
									<label id="row_02_title">PO&nbsp2</label>					
								</td>
							</tr>
							<tr class="child_row_02 child_row">
								<td>
									结算期 <br>Payment Term
								</td>
								<td>
									<select name="paymentDays_2" id="j_payment_paymentDays_2" data-toggle="selectpicker" data-width="190px" >
				                        <option value=""></option>
				                        <option value="30">30Days</option>
				                        <option value="45">45Days</option>
				                        <option value="60">60Days</option>
				                        <option value="90">90Days</option>
				                        <option value="120">120Days</option>
			                    	</select>
								</td>
								<td>
									收货或验收日期<br>Receiving or Approval date
								</td>
								<td>
									<input type="text" name="receivingOrApprovalDate_2" data-nobtn="true" id="j_payment_receivingOrApprovalDate_2" value="" size="19" data-toggle="datepicker" data-rule="date">
								</td>
							</tr>
							<tr class="child_row_02 child_row">
								<td>
									订单号<br>PO No.
								</td>
								<td>
									<input type="text" name="PONo_2" id="j_payment_PONo_2"  value="" size="19" onchange="checkPoNO(this)">
								</td>
								<td>
									<label class="row-label">币别<br>Currency</label>
								</td>
								<td>
									<select name="currency_2" id="j_payment_currency_2" data-toggle="selectpicker" data-width="190px" onchange="changeCurrency(this)">
				                        <option value=""></option>
				                        <option value="RMB">RMB</option>
				                        <option value="USD">USD</option>
				                        <option value="EUR">EUR</option>
				                        <option value="GBP">GBP</option>
			                    	</select>
								</td>
							</tr>
							<tr class="child_row_02 child_row">
								<td>
									金额<br>Amount
								</td>
								<td>
									<input type="text" name="amount_2_t" id="j_payment_amount_2_t" value="" data-rule="number" size="19" onchange="changeAmount()">
									<input type="hidden" name="amount_2" id="j_payment_amount_2" value="">
								</td>
								<td colspan="2">
								</td>
							</tr>
							
							<!-- Upon receiving 收货后 -->
							<tr class="table-parent" id="row_03">
								<td colspan="4">
									<label id="row_03_title">PO&nbsp3</label>						
								</td>
							</tr>
							<tr class="child_row_03 child_row">
								<td>
									结算期 <br>Payment Term
								</td>
								<td>
									<select name="paymentDays_3" id="j_payment_paymentDays_3" data-toggle="selectpicker" data-width="190px" >
				                        <option value=""></option>
				                        <option value="30">30Days</option>
				                        <option value="45">45Days</option>
				                        <option value="60">60Days</option>
				                        <option value="90">90Days</option>
				                        <option value="120">120Days</option>
			                    	</select>
								</td>
								<td>
									收货或验收日期<br>Receiving or Approval date
								</td>
								<td>
									<input type="text" name="receivingOrApprovalDate_3" size="19" data-nobtn="true" id="j_payment_receivingOrApprovalDate_3" size="19"  value="" data-toggle="datepicker" data-rule="date">
								</td>
							</tr>
							<tr class="child_row_03 child_row">
								<td>
									订单号<br>PO No.
								</td>
								<td>
									<input type="text" name="PONo_3" id="j_payment_PONo_3" size="19" value="" onchange="checkPoNO(this)">
								</td>
								<td>
									<label class="row-label">币别<br>Currency</label>
								</td>
								<td>
									<select name="currency_3" id="j_payment_currency_3" data-width="190px" data-toggle="selectpicker" onchange="changeCurrency(this)">
				                        <option value=""></option>
				                        <option value="RMB">RMB</option>
				                        <option value="USD">USD</option>
				                        <option value="EUR">EUR</option>
				                        <option value="GBP">GBP</option>
			                    	</select>
								</td>
							</tr>
							<tr class="child_row_03 child_row">
								<td>
									金额<br>Amount
								</td>
								<td>
									<input type="text" name="amount_3_t" id="j_payment_amount_3_t" value="" size="19" data-rule="number" onchange="changeAmount();" >
									<input type="hidden" name="amount_3" id="j_payment_amount_3" value="">
								</td>
								<td colspan="2">
								</td>
							</tr>
							
							<!-- Upon Approval 验收后 -->
							<tr class="table-parent" id="row_04">
								<td colspan="4">
									<label id="row_04_title">PO&nbsp4</label>				
								</td>
							</tr>
							<tr class="child_row_04 child_row">
								<td>
									结算期 <br>Payment Term
								</td>
								<td>
									<select name="paymentDays_4" id="j_payment_paymentDays_4" data-toggle="selectpicker" data-width="190px" >
				                        <option value=""></option>
				                        <option value="30">30Days</option>
				                        <option value="45">45Days</option>
				                        <option value="60">60Days</option>
				                        <option value="90">90Days</option>
				                        <option value="120">120Days</option>
			                    	</select>
								</td>
								<td>
									收货或验收日期<br>Receiving or Approval date
								</td>
								<td>
									<input type="text" name="receivingOrApprovalDate_4" size="19" data-nobtn="true" size="19" id="j_payment_receivingOrApprovalDate_4" value="" data-toggle="datepicker" data-rule="date">
								</td>
							</tr>
							<tr class="child_row_04 child_row">
								<td>
									订单号<br>PO No.
								</td>
								<td>
									<input type="text" name="PONo_4" id="j_payment_PONo_4" size="19" value="" onchange="checkPoNO(this)">
								</td>
								<td>
									<label class="row-label">币别<br>Currency</label>
								</td>
								<td>
									<select name="currency_4" id="j_payment_currency_4" data-width="190px" data-toggle="selectpicker" onchange="changeCurrency(this)">
				                        <option value=""></option>
				                        <option value="RMB">RMB</option>
				                        <option value="USD">USD</option>
				                        <option value="EUR">EUR</option>
				                        <option value="GBP">GBP</option>
			                    	</select>
								</td>
							</tr>
							<tr class="child_row_04 child_row">
								<td>
									金额<br>Amount
								</td>
								<td>
									<input type="text" name="amount_4_t" id="j_payment_amount_4_t" size="19" value="" data-rule="number" onchange="changeAmount()" >
									<input type="hidden" name="amount_4" id="j_payment_amount_4" value="">
								</td>
								<td colspan="2">
								</td>
							</tr>
							
							<!-- Upon invoice 见票后 -->
							<tr class="table-parent" id="row_05">
								<td colspan="4">
									<label id="row_05_title">PO&nbsp5</label>							 
								</td>
							</tr>
							<tr class="child_row_05 child_row">
								<td>
									结算期 <br>Payment Term
								</td>
								<td>
									<select name="paymentDays_5" id="j_payment_paymentDays_5" data-toggle="selectpicker" data-width="190px" >
				                        <option value=""></option>
				                        <option value="30">30Days</option>
				                        <option value="45">45Days</option>
				                        <option value="60">60Days</option>
				                        <option value="90">90Days</option>
				                        <option value="120">120Days</option>
			                    	</select>
								</td>
								<td>
									收货或验收日期<br>Receiving or Approval date
								</td>
								<td>
									<input type="text" name="receivingOrApprovalDate_5" size="19" data-nobtn="true" id="j_payment_receivingOrApprovalDate_5" size="19" value="" data-toggle="datepicker" data-rule="date">
								</td>
							</tr>
							<tr class="child_row_05 child_row">
								<td>
									订单号<br>PO No.
								</td>
								<td>
									<input type="text" name="PONo_5" id="j_payment_PONo_5" size="19" value="" onchange="checkPoNO(this)">
								</td>
								<td>
									<label class="row-label">币别<br>Currency</label>
								</td>
								<td>
									<select name="currency_5" id="j_payment_currency_5" data-width="190px" data-toggle="selectpicker" onchange="changeCurrency(this)">
				                        <option value=""></option>
				                        <option value="RMB">RMB</option>
				                        <option value="USD">USD</option>
				                        <option value="EUR">EUR</option>
				                        <option value="GBP">GBP</option>
			                    	</select>
								</td>
							</tr>
							<tr class="child_row_05 child_row">
								<td>
									金额<br>Amount
								</td>
								<td>
									<input type="text" name="amount_5_t" id="j_payment_amount_5_t" size="19" value="" data-rule="number" onchange="changeAmount()" >
									<input type="hidden" name="amount_5" id="j_payment_amount_5" value="">
								</td>
								<td colspan="2">
								</td>
							</tr>
							
							<!-- Other 其他 -->
							<tr class="table-parent" id="row_06">
								<td colspan="4">
									<label id="row_06_title">PO&nbsp6</label>						
								</td>
							</tr>
							<tr class="child_row_06 child_row">
								<td>
									结算期 <br>Payment Term
								</td>
								<td>
									<select name="paymentDays_6" id="j_payment_paymentDays_6" data-toggle="selectpicker" data-width="190px" >
				                        <option value=""></option>
				                        <option value="30">30Days</option>
				                        <option value="45">45Days</option>
				                        <option value="60">60Days</option>
				                        <option value="90">90Days</option>
				                        <option value="120">120Days</option>
			                    	</select>
								</td>
								<td>
									收货或验收日期<br>Receiving or Approval date
								</td>
								<td>
									<input type="text" name="receivingOrApprovalDate_6" size="19" data-nobtn="true" id="j_payment_receivingOrApprovalDate_6" size="19" value="" data-toggle="datepicker" data-rule="date">
								</td>
							</tr>
							<tr class="child_row_06 child_row">
								<td>
									订单号<br>PO No.
								</td>
								<td>
									<input type="text" name="PONo_6" id="j_payment_PONo_6" size="19" value="" onchange="checkPoNO(this)" >
								</td>
								<td>
									<label class="row-label">币别<br>Currency</label>
								</td>
								<td>
									<select name="currency_6" id="j_payment_currency_6" data-toggle="selectpicker" data-width="190px" onchange="changeCurrency(this)">
				                        <option value=""></option>
				                        <option value="RMB">RMB</option>
				                        <option value="USD">USD</option>
				                        <option value="EUR">EUR</option>
				                        <option value="GBP">GBP</option>
			                    	</select>
								</td>
							</tr>
							<tr class="child_row_06 child_row">
								<td>
									金额<br>Amount
								</td>
								<td>
									<input type="text" name="amount_6_t" id="j_payment_amount_6_t" value="" data-rule="number" size="19" onchange="changeAmount()" >
									<input type="hidden" name="amount_6" id="j_payment_amount_6" value="">
								</td>
								<td colspan="2">
								</td>
							</tr>
							


						</table>						
					</td>
				</tr>
				
				
				

				
				<tr>
					<td>
						供应商代码<br>Supplier Code
					</td>
					<td>
						<input type="text" name="supplierCode" id="j_payment_supplierCode" value="" size="19" data-rule="required">
					</td>
					<td>
						银行交易编码<br>Ref. No. of Bank
					</td>
					<td>
						<input type="text" name="refNoofBank" value="" id="j_payment_refNoofBank" size="19"  readonly="">
					</td>
				</tr>
				<tr>
					<td>
						支付用途 <br>Usage Description
					</td>
					<td colspan="3">
						<textarea cols="80" rows="3" id="j_payment_usageDescription"  name="usageDescription" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						金额(小写)<br>Amount in figures:
					</td>
					<td>
						<input type="text" name="amountInFigures_t" size="19" id="j_payment_amountInFigures_t" value="" readonly="" >
						<input type="hidden" name="amountInFigures" id="j_payment_amountInFigures" value="">
					</td>
					<td>
						金额（大写）<br>Amount in words:
					</td>
					<td>
						<input type="text" name="amountInWords" size="19" id="j_payment_amountInWords" value="" readonly="" >
					</td>
				</tr>
				<tr>
					<td>
						单据审核<br>Document Audit:
					</td>
					<td>
						<input type="text" name="documentAudit" size="19" id="j_payment_documentAudit" value="" readonly=""  >
					</td>
					<td>
						部门经理<br>Dept. Manager:
					</td>
					<td>
						<input type="text" name="deptManager" size="19" id="j_payment_deptManager" value="" readonly="" >
					</td>  
				</tr>
				<tr>
					<td>
						Attachment1 Invoice （附件：发票）
					</td>
					<td>
						<div id="upfile_invoice" > </div>
					</td>
					<td colspan="2" >
						<table class="table" id="upfile_invoice_list" >	
							<tr>
								<th width="400px" align="center">文件名</th>
								<th width="100px" align="center">删除</th>
							</tr>									
						</table>
					</td>
				</tr>
				<tr>
					<td>
						Attachment2 Contract （附件：合同）
					</td>
					<td>
						<div id="upfile_contract" > </div>
					</td>
					<td	colspan="2">
						<table class="table" id="upfile_contract_list" >	
							<tr>
								<th width="400px" align="center">文件名</th>
								<th width="100px" align="center">删除</th>
							</tr>									
						</table>
					</td>
				</tr>
				<tr>
					<td>
						Attachment3 Other （附件：其他）
					</td>
					<td>
						<div id="upfile_other" > </div>
					</td>
					<td colspan="2">
						<table class="table" id="upfile_other_list" >	
							<tr>
								<th width="400px" align="center">文件名</th>
								<th width="100px" align="center">删除</th>
							</tr>									
						</table>
					</td>
				</tr>

				
				
				<tr>
					<td colspan="4" align="center">
	            		<button type="button" id="payment-save" class="btn-default" data-icon="save" >Save(保存)</button>
	            		<button type="button" id="payment-submit" class="btn-default" data-icon="arrow-up" >Submit(送审)</button>
	            		<button type="button" id="payment-approve" class="btn-default" data-icon="check" >Approve(同意)</button>
	            		<button type="button" id="payment-reject" class="btn-default" data-icon="close" >Reject(拒绝)</button>
	            		<button type="button" id="payment-assign" class="btn-default" data-icon="undo" >Assign(转交)</button>
	            		<button type="button" id="payment-acc" class="btn-default" data-icon="check" >Approve(同意)</button>
	            		<button type="button" id="payment-print" class="btn-default" data-icon="print" >Print Out(打印)</button><br>
	            		<button type="button" id="payment-delete" class="btn-default" data-icon="close" >Delete(删除)</button>
            		</td>				
				</tr>
				<tr id="payment-invalid-tr">
            		<td colspan="4" align="center">
            			<button type="button" id="payment-invalid" class="btn-default" data-icon="close">Doc. Invalid(作废)</button>
            			<textarea name="invalidDescription"  id="payment-invalidDescription" cols="30" rows="1" data-toggle="autoheight"></textarea><br><br>
            		</td>
            	</tr>
            	<tr id="payment-return-tr">
            		<td colspan="4" align="center">
            			<button type="button" id="payment-return" class="btn-default" data-icon="arrow-down">Doc. Return(退回)</button>
            			<textarea name="returnDescription" id="payment-returnDescription" cols="30" rows="1" data-toggle="autoheight"></textarea>
            		</td>
            	</tr>
				
			</table>		






        </form>
    </div>
</div>