<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<style type="text/css">
tr { font-family:"Microsoft YaHei",微软雅黑,"MicrosoftJhengHei",华文细黑,STHeiti,MingLiu;font-weight:bold} 
.bg { background:#B7DEE8 }
.Beneficiarychange{background-color: #9ACD32}
.BeneficiaryIDchange{background-color: #EEC900}
</style>

<style type="text/css" media="print">
tr { font-family:"Microsoft YaHei",微软雅黑,"MicrosoftJhengHei",华文细黑,STHeiti,MingLiu;font-weight:bold} 
.bg { background:#B7DEE8 }
.Beneficiarychange{background-color: #9ACD32}
.BeneficiaryIDchange{background-color: #EEC900}
</style>

<script type="text/javascript">
$(function(){
	dataToFace();
	
	
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
		
});

function dataToFace(){
	
	BJUI.ajax('doajax', {
	    url: 'getPaymentByID.action',
	    loadingmask: true,
	    data:{id:'${param.id}'},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	
            	
            	$.CurrentNavtab.find("#applicationDate").html(json.applicationDate);
            	$.CurrentNavtab.find("#requestPaymentDate").html(json.requestPaymentDate);
            	$.CurrentNavtab.find("#contacturalPaymentDate").html(json.contacturalPaymentDate);
            	$.CurrentNavtab.find("#code").html(json.code);
            	if(json.payType=='Cash'){
            		$.CurrentNavtab.find("#Cash").attr('checked','checked');
            	}else if(json.payType=='Banking'){
            		$.CurrentNavtab.find("#Banking").attr('checked','checked');
            	}else if(json.payType=='AdvanceWriteoff'){
            		$.CurrentNavtab.find("#AdvanceWriteoff").attr('checked','checked');
            		$.CurrentNavtab.find("#AdvanceWriteoffText").html(json.advanceWriteoffWay+",Amount:"+json.advanceWriteOffCurrency+" "+formatCurrency(json.advanceWriteOffAmount))
            	}
            	if(json.urgent=='1'){
            		$.CurrentNavtab.find("#urgent").html("Urgent : ●");
            	}
            	$.CurrentNavtab.find("#UID").html(json.UID+'<br>'+json.UName);
            	$.CurrentNavtab.find("#departmentID").html(json.departmentName+'<br>'+json.departmentID);
            	$.CurrentNavtab.find("#beneficiary").html(json.beneficiary+'<br>'+json.beneficiaryE)
            	$.CurrentNavtab.find("#beneficiaryAccountNO").html(json.beneficiaryAccountBank+'<br>'+json.beneficiaryAccountNO)
                if(json.beneficiaryChange=='1'){
                	$.CurrentNavtab.find("#beneficiaryChange").html("●"); 
                	$.CurrentNavtab.find("#beneficiary_td").attr("class","Beneficiarychange");
            	}
                if(json.beneficiaryAccountNOChange=='1'){
                	$.CurrentNavtab.find("#beneficiaryAccountNOChange").html("●") 
            		$.CurrentNavtab.find("#beneficiaryAccountNO_td").attr("class","BeneficiaryIDchange");
            	}
                
                
                
                $.CurrentNavtab.find("#paymentSubject_"+json.paymentSubject).html("Y")  
                $.CurrentNavtab.find("#paymentDays_"+json.paymentTerm).html(json.paymentDays_1)  

            	
            	if(json.amount_1!=''&&json.amount_1!=null&&json.amount_1!='0.00'&&json.amount_1!=undefined){
            		$.CurrentNavtab.find("#amount_1").html(formatCurrency(json.amount_1)+"&nbsp&nbsp");
            		$.CurrentNavtab.find("#receivingOrApprovalDate_1").html(json.receivingOrApprovalDate_1);
            		$.CurrentNavtab.find("#PONo_1").html(json.PONo_1);
            		$.CurrentNavtab.find("#currency_1").html(json.currency_1);
            	}
    
            	
            	if(json.amount_2!=''&&json.amount_2!=null&&json.amount_2!='0.00'&&json.amount_2!=undefined){
            		$.CurrentNavtab.find("#amount_2").html(formatCurrency(json.amount_2)+"&nbsp&nbsp");
            		$.CurrentNavtab.find("#receivingOrApprovalDate_2").html(json.receivingOrApprovalDate_2);
            		$.CurrentNavtab.find("#PONo_2").html(json.PONo_2);
            		$.CurrentNavtab.find("#currency_2").html(json.currency_2);
            	}           		

            	
            	if(json.amount_3!=''&&json.amount_3!=null&&json.amount_3!='0.00'&&json.amount_3!=undefined){
            		$.CurrentNavtab.find("#amount_3").html(formatCurrency(json.amount_3)+"&nbsp&nbsp");
            		$.CurrentNavtab.find("#receivingOrApprovalDate_3").html(json.receivingOrApprovalDate_3);
            		$.CurrentNavtab.find("#PONo_3").html(json.PONo_3);
            		$.CurrentNavtab.find("#currency_3").html(json.currency_3);
            	}
            		
            	
            	if(json.amount_4!=''&& json.amount_4!=null&&json.amount_4!='0.00'&&json.amount_4!=undefined){
            		$.CurrentNavtab.find("#amount_4").html(formatCurrency(json.amount_4)+"&nbsp&nbsp");
            		$.CurrentNavtab.find("#receivingOrApprovalDate_4").html(json.receivingOrApprovalDate_4);
            		$.CurrentNavtab.find("#PONo_4").html(json.PONo_4);
            		$.CurrentNavtab.find("#currency_4").html(json.currency_4);
            	}
            		
            	
            	if(json.amount_5!=''&& json.amount_5!=null&&json.amount_5!='0.00'&&json.amount_5!=undefined){
            		$.CurrentNavtab.find("#amount_5").html(formatCurrency(json.amount_5)+"&nbsp&nbsp");
            		$.CurrentNavtab.find("#receivingOrApprovalDate_5").html(json.receivingOrApprovalDate_5);
            		$.CurrentNavtab.find("#PONo_5").html(json.PONo_5);
            		$.CurrentNavtab.find("#currency_5").html(json.currency_5);
            	}
            		
            	
            	if(json.amount_6!=''&& json.amount_6!=null&&json.amount_6!='0.00'&&json.amount_6!=undefined){
            		$.CurrentNavtab.find("#amount_6").html(formatCurrency(json.amount_6)+"&nbsp&nbsp");
            		$.CurrentNavtab.find("#receivingOrApprovalDate_6").html(json.receivingOrApprovalDate_6);
            		$.CurrentNavtab.find("#PONo_6").html(json.PONo_6);
            		$.CurrentNavtab.find("#currency_6").html(json.currency_6);
            	}
            		
            	$.CurrentNavtab.find("#supplierCode").html(json.supplierCode);
            	$.CurrentNavtab.find("#refNoofBank").html(json.refNoofBank);
            	$.CurrentNavtab.find("#usageDescription").html(json.usageDescription.replace(/\r\n/g,"<br>"));

            	if(json.amountInFigures!=''&&json.amountInFigures!=null){
            		$.CurrentNavtab.find("#amountInFigures").html(json.currency_1+"&nbsp&nbsp"+formatCurrency(json.amountInFigures)+"&nbsp&nbsp");
            		$.CurrentNavtab.find("#amountInWords").html("&nbsp&nbsp"+smalltoBIG(json.amountInFigures));
            	}
            	
            	$.CurrentNavtab.find("#documentAudit").html("&nbsp&nbsp"+json.documentAudit);
            	
            	if(json.deptManagerDate!=''&&json.deptManagerDate!=null&&json.deptManagerDate!=undefined){
            		$("#deptManager").html("&nbsp&nbsp"+json.deptManager+"/"+json.deptManagerDate);            		
            	}
            	else{
            		$("#deptManager").html("&nbsp&nbsp"+json.deptManager);
            	}
            	
            	if(json.state=='5'){
            		$.CurrentNavtab.find("#invalid-img").show();
            	}
				if(json.handingFee!=''&& json.handingFee!=null&&json.handingFee!='0.00'&&json.handingFee!=undefined)
            		$("#handingFee").html("&nbsp&nbsp"+json.handingFee);
            	
            	if(json.file_invoice!=undefined&&json.file_invoice!=""){
            		var file=json.file_invoice.split('|');
            		
            		$.each(file,function(i,n){
            			var filename=n.split('/')[1];
            			if(filename!=undefined&&filename!=''){         				
            				$.CurrentNavtab.find('#upfile_invoice_list').append(fileToTr(filename,n));	   
            			}
            		})
            	}
            	if(json.file_other!=undefined&&json.file_other!=""){
            		var file=json.file_other.split('|');
            		$.each(file,function(i,n){
            			var filename=n.split('/')[1];
            			if(filename!=undefined&&filename!=''){
            				$.CurrentNavtab.find('#upfile_other_list').append(fileToTr(filename,n));	 
            			}
            		})
            	}
            	if(json.file_contract!=undefined&&json.file_contract!=""){
            		var file=json.file_contract.split('|');
            		$.each(file,function(i,n){
            			var filename=n.split('/')[1];
            			if(filename!=undefined&&filename!=''){
            				$.CurrentNavtab.find('#upfile_contract_list').append(fileToTr(filename,n));
            			}
            			
            		})
            	}
            	
            	showButton(json.state,json.isPrint,json.UID,json.documentAuditID,json.deptManagerID);
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	

	
}

function fileToTr(name,path){
	return "<tr><td align='center'><a onclick=\"getFile('"+path.replace('\\','\\\\')+"')\" url='"+path.replace('\\','\\\\')+ "' >"+name+"</></td><td align='center'></td></tr>"
}


function showButton(state,print,uid,documentAuditid,deptManagerid){	
	if('${param.viewtype}'=='admin'){
		if((state=="0"||state=="1")&&print!='1'){
			$.CurrentNavtab.find('#payment-delete').show();
			$.CurrentNavtab.find('#payment-print').hide();
		}else{
			$.CurrentNavtab.find('#payment-delete').hide();
			$.CurrentNavtab.find('#payment-print').show();
		}
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
	}else if(state=="4"){//财务处理完成  非财务人员查看。可打印
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').show();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
	}else if(state=="1"&&(deptManagerid=='${user.uid}'||'{param.viewtype}'=='sign')){//部门经理审批
		$.CurrentNavtab.find('#payment-approve').show();
		$.CurrentNavtab.find('#payment-reject').show();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
	}else if(state=="2"&&documentAuditid=='${user.uid}'){//审批通过 财务处理
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-print').show();
		$.CurrentNavtab.find('#payment-delete').hide();
		$.CurrentNavtab.find('#payment-assign').show();
		$.CurrentNavtab.find('#payment-acc').show();
		$.CurrentNavtab.find('#payment-invalid-tr').show();
		$.CurrentNavtab.find('#payment-return-tr').show();	
		$.CurrentNavtab.find('#payment-invalid-tr').show();
		$.CurrentNavtab.find('#payment-return-tr').show();	
		$.CurrentNavtab.find('#j_payment_documentAudit').val('${user.name}')
 	}else if(state=="2"&&(deptManagerid=='${user.uid}'||'{param.viewtype}'=='sign')){//审批通过 普通员工打印
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').show();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
	}else if(state=="3"){//审批未通过，单据作废，
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
	}else if(state=="5"){
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-print').show();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
	}else{
		if(print=='1'){
			$.CurrentNavtab.find('#payment-delete').hide();
		}else{
			$.CurrentNavtab.find('#payment-delete').show();
		}
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	

	}
	
	
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

function accPayment(){	
	BJUI.ajax('doajax', {
	    url: 'accPayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val(),documentAudit:'${user.name}'},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find('#payment-return').hide();
            	 $.CurrentNavtab.find('#payment-acc').hide();
            	 $.CurrentNavtab.find('#payment-assign').hide();
            	 $.CurrentNavtab.find('#payment-invalid').hide();
            	 $.CurrentNavtab.find('#payment-invalid-tr').hide();
         		 $.CurrentNavtab.find('#payment-return-tr').hide();
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
}


function returnPayment(){
	var err=checkReturn()
	if(err!=''){
		BJUI.alertmsg('warn', err); 
		return false;
	}
	BJUI.ajax('doajax', {
	    url: 'returnPayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val(),returnDescription:$.CurrentNavtab.find("#payment-returnDescription").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message);
            	 $.CurrentNavtab.find('#payment-print').hide();
            	 $.CurrentNavtab.find('#payment-return').hide();
            	 $.CurrentNavtab.find('#payment-acc').hide();
            	 $.CurrentNavtab.find('#payment-assign').hide();
            	 $.CurrentNavtab.find('#payment-invalid').hide();
            	 $.CurrentNavtab.find('#payment-invalid-tr').hide();
         		 $.CurrentNavtab.find('#payment-return-tr').hide();
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
	
}

function invalidPayment(){
	var err=checkInvalid()
	if(err!=''){
		BJUI.alertmsg('warn', err); 
		return false;
	}
	
	BJUI.ajax('doajax', {
	    url: 'invalidPayment.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_id").val(),invalidDescription:$.CurrentNavtab.find("#payment-invalidDescription").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find('#payment-return').hide();
            	 $.CurrentNavtab.find('#payment-acc').hide();
            	 $.CurrentNavtab.find('#payment-assign').hide();
            	 $.CurrentNavtab.find('#payment-invalid').hide();
            	 $.CurrentNavtab.find('#payment-invalid-tr').hide();
         		 $.CurrentNavtab.find('#payment-return-tr').hide();
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

function checkInvalid(){
	var err='';
	if($.CurrentNavtab.find('#payment-invalidDescription').val()==null||$.CurrentNavtab.find('#payment-invalidDescription').val()==''){
		err+=" InvalidDescription can`t be  empty！\r\n";				
	}
	return err;
}



function checkReturn(){
	var err='';
	if($.CurrentNavtab.find('#payment-returnDescription').val()==null||$.CurrentNavtab.find('#payment-returnDescription').val()==''){
		err+=" ReturnDescription can`t be  empty！\r\n";				
	}
	return err;
}

</script>
<div class="bjui-pageContent">
    <div  class="bs-example" style="width:1300px;padding:20px;" >
    		<input type="hidden" name="id" id="j_payment_id" value="${param.id} ">
			<table  style="font-size:10px;">
				<tr>
					<td  width="200px"></td>
					<td  width="930px"></td>
					<td  width="70px"></td>
				</tr>
				<tr height="10px" style="font-size:14px;">
					<td colspan="2" align="right" >
						<label>NO:</label>
					</td>
					<td align="center"><label id="code"></label></td>
				</tr>
				<tr>
					<td colspan="3"  align="center"><h3 align="left" style="font-weight:bold">Cimtas(NingBo) Steel Processing CO.,LTD 庆达西（宁波）钢构制造有限公司</h3>
					<h5 align="center" style="font-weight:bold">Payment Application Form 付款申请单</h5>
					</td>
				</tr>
				<tr>
					<td  colspan="2" align="right" width="20px">
					&nbsp&nbsp&nbsp<label id="urgent" style="font-size:14px;color: red" ></label>
					</td>
					<td></td>
				</tr>
			</table>		
			
			<table style="font-size:10px;">
				<tr>
					<td width="180px" ><label>Application Date(申请日期):</label></td>
					<td width="120px" align="left"><label id="applicationDate"></label></td>
					<td width="230px" ><label>Request Payment Date(要求付款日期):</label></td>
					<td width="170px"  align="left"><label id="requestPaymentDate"></label></td>
					<td width="250px" ><label>Contactural Payment Date(合同付款日期):</label></td>
					<td width="150px" align="left"><label id="contacturalPaymentDate"></label></td>
					<td width="100px"></td>
				</tr>
				<tr height="30px">
					<td>
						<input type="checkbox" id="Cash" disabled="disabled" ><label>&nbsp&nbsp支付现金 Cash</label>
					</td>
					<td colspan="2">
						<input type="checkbox" id="Banking" disabled="disabled"><label>&nbsp&nbsp银行支付 Banking</label>
					</td>
					<td >
					</td>
					<td colspan="3">
						<input type="checkbox" id="AdvanceWriteoff" disabled="disabled"><label>&nbsp&nbsp核销预付 Advance Write-off&nbsp&nbsp&nbsp&nbsp</label>
						<u id="AdvanceWriteoffText"></u>
					</td>
				</tr>
			</table>            
			
			<table width="1200px" border="1" cellspacing="0" border-bottom="0px" bordercolor="black" style="font-size:10px;">
				<tr height="30px">
					<td colspan="2" rowspan="2" class="bg">&nbsp申请人:<br>&nbspApplication:</td>
					<td colspan="2" rowspan="2" style="text-align:center;" ><label id="UID"></label></td>
					<td colspan="4"  class="bg">&nbsp收款人（全称）:</td>
					<td colspan="2" rowspan="2"  id="beneficiary_td" style="text-align:center;" >
						<label id="beneficiary"></label>
                    </td>
					<td width="40px" align="center" style="text-align:center;" class="bg">change<br/>变更</td>
					<td width="90px" rowspan="2" style="text-align:center;" class="bg">供应商代码:<br/>Supplier Code:</td>
					<td width="90px" rowspan="2" style="text-align:center"><label id="supplierCode"></label> </td>
				</tr>
				<tr height="30px">
					<td colspan="4"  class="bg" height="12px">&nbspBeneficiary:</td>
					<td style="text-align:center">
						<label id="beneficiaryChange" ></label>
					</td>
				</tr>
				<tr height="30px">
					<td colspan="2" rowspan="2" class="bg">&nbsp所属部门:<br>&nbspDepartment of Applicant:</td>
					<td colspan="2" rowspan="2" style="text-align:center"><label id="departmentID"></label></td>
					<td colspan="4"  class="bg">&nbsp银行及帐号:</td>
					<td colspan="2" rowspan="2" id="beneficiaryAccountNO_td" style="text-align:center;" >
						<label id="beneficiaryAccountNO"></label>
					</td>
					<td align="center" style="text-align:center;" class="bg">change<br/>变更</td>
					<td rowspan="2" style="text-align:center;" class="bg">银行交易编码:<br/>Ref. No. of Bank:</td>
					<td rowspan="2" ><label id="refNoofBank"></label></td>
				</tr>
				<tr height="30px">
					<td colspan="4"  class="bg">&nbspBeneficiary Account NO:</td>
					<td style="text-align:center">
						<label id="beneficiaryAccountNOChange" ></label>
					</td>
				</tr>
				<tr height="20px">
					<td colspan="2" align="center" class="bg">付款项目<br/>Payment Subject</td>
					<td colspan="2" align="center" class="bg">结算方式 <br/>Payment By</td>
					<td width="100px" align="center" class="bg" >收货或验收日期<br/>Receiving or Approval date</td>
					<td width="50px" align="center" class="bg" >订单号<br/>PO No.</td>
					<td width="60px" align="center" class="bg">币别<br/>Currency</td>
					<td width="110px" align="center" class="bg">金额<br/>Amount</td>
					<td width="100px" rowspan="8" align="center" >支付用途<br/>Usage<br/>Description</td>
					<td colspan="4" rowspan="6"  id="usageDescription" style="word-wrap: break-word; word-break: normal;"></td>
				</tr>
				
				
				
				<tr  align="center">
					<td width="30px" align="center"><label id="paymentSubject_1"></label></td>
					<td width="140px" align="left">&nbsp Fixed Asset 固定资产</td>
					<td width="50px" ><label id="paymentDays_1"></label></td>
					<td width="120px" align="right">Advance &nbsp<br/>预付款 &nbsp</td>
					<td width="100px" ><label id="receivingOrApprovalDate_1"></label></td>
					<td width="50px" ><label id="PONo_1"></label></td>
					<td width="60px" ><label id="currency_1"></label></td>
					<td width="110px" style="text-align: right"><label id="amount_1"></label></td>
				</tr>	
				<tr align="center">
					<td align="center"><label id="paymentSubject_2"></label></td>
					<td align="left">&nbsp Raw Material 原材料</td>
					<td><label id="paymentDays_2"></label></td>
					<td align="right">Payment at sight &nbsp<br/>见票即付 &nbsp</td>
					<td><label id="receivingOrApprovalDate_2"></label></td>
					<td><label id="PONo_2"></label></td>
					<td><label id="currency_2"></label></td>
					<td style="text-align: right"><label id="amount_2"></label></td>
				</tr>	
				<tr align="center">
					<td align="center"><label id="paymentSubject_3"></label></td>
					<td align="left">&nbspConsumable 消耗品</td>
					<td><label id="paymentDays_3"></label></td>
					<td align="right">Upon receiving&nbsp<br/>收货后&nbsp</td>
					<td><label id="receivingOrApprovalDate_3"></label></td>
					<td><label id="PONo_3"></label></td>
					<td><label id="currency_3"></label></td>
					<td style="text-align: right"><label id="amount_3"></label></td>
				</tr>	
				<tr align="center">
					<td align="center"><label id="paymentSubject_4"></label></td>
					<td align="left">&nbspSubcontractor 外包</td>
					<td><label id="paymentDays_4"></label></td>
					<td align="right">Upon Approval&nbsp<br/>验收后&nbsp</td>
					<td><label id="receivingOrApprovalDate_4"></label></td>
					<td><label id="PONo_4"></label></td>
					<td><label id="currency_4"></label></td>
					<td style="text-align: right"><label id="amount_4"></label></td>
				</tr>	
				<tr align="center">
					<td align="center"><label id="paymentSubject_5"></label></td>
					<td align="left">&nbspService 服务</td>
					<td><label id="paymentDays_5"></label></td>
					<td align="right">Upon invoice&nbsp<br/>见票后&nbsp</td>
					<td><label id="receivingOrApprovalDate_5"></label></td>
					<td><label id="PONo_5"></label></td>
					<td><label id="currency_5"></label></td>
					<td style="text-align: right"><label id="amount_5"></label></td>
				</tr>	
				<tr align="center">
					<td align="center"><label id="paymentSubject_6"></label></td>
					<td align="left">&nbspPetty Cash 备用金</td>
					<td><label id="paymentDays_6"></label></td>
					<td align="right">Other&nbsp<br/>其他&nbsp</td>
					<td><label id="receivingOrApprovalDate_6"></label></td>
					<td><label id="PONo_6"></label></td>
					<td><label id="currency_6"></label></td>
					<td style="text-align: right"><label id="amount_6"></label></td>
					<td colspan="2" rowspan="2">手续费<br>Handing&nbsp&nbspFee</td>
					<td colspan="2" rowspan="2" id="handingFee"></td>
				</tr>	
				<tr align="center">
					<td align="center"><label id="paymentSubject_7"></label></td>
					<td align="left">&nbspOther 其他</td>
					<td></td>
					<td align="right">&nbsp<br>&nbsp</td>
					<td></td>
					<td></td>
					<td> 
						
                    </td>
					<td></td>
				</tr>		
				<tr>
					<td colspan="2" class="bg" align="right">金额(小写)<br/>Amount in figures:</td>
					<td colspan="6" align="right"><label id="amountInFigures"></label></td>
					<td align="right" class="bg">金额(大写)<br/>Amount in words:</td>
					<td colspan="2" ><label id="amountInWords"></label></td>
					<td align="right" class="bg" >Document Audit:<br/>单据审核</td>
					<td><label id="documentAudit"></label></td>
				</tr>		
			</table>
			<table width="1200px" style="font-size:10px">
				<tr >
					<td width="25%" height="160px">General Manager:<br/>总经理</td>
					<td width="25%">Finance Manager:<br/>财务经理</td>
					<td width="25%">Audit:<br/>审计</td>
					<td width="10%">Dept. Manager:<br/>部门经理</td>		
					<td width="15%" align="left"><label id="deptManager"></label></td>		
				</tr>
			</table>
			
			<table>
				<tr>
					<td valign="top">
						 <table class="table" id="upfile_invoice_list" >	
							<tr>
								<th width="400px" align="center">File Name</th>
								<th width="100px" align="center">Delete</th>
							</tr>									
						</table>
					</td>
					<td valign="top"> 
						<table class="table" id="upfile_contract_list" >	
							<tr>
								<th width="400px" align="center">File Name</th>
								<th width="100px" align="center">Delete</th>
							</tr>									
						</table>
					</td>
					<td valign="top">
						<table class="table" id="upfile_other_list" >	
							<tr>
								<th width="400px" align="center">File Name</th>
								<th width="100px" align="center">Delete</th>
							</tr>									
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
					
						<button type="button" id="payment-approve" class="btn-default" data-icon="check" >Approve(同意)</button>
	            		<button type="button" id="payment-reject" class="btn-default" data-icon="close" >Reject(拒绝)</button>
	            		<button type="button" id="payment-assign" class="btn-default" data-icon="undo" >Assign(转交)</button>
	            		<button type="button" id="payment-acc" class="btn-default" data-icon="check" >Approve(同意)</button>
	            		<button type="button" id="payment-print" class="btn-default" data-icon="print" >Print Out(打印)</button>
					</td>
				</tr>
				<tr id="payment-invalid-tr">
            		<td colspan="3" align="center">
            			<button type="button" id="payment-invalid" class="btn-default" data-icon="close">Doc. Invalid(作废)</button>
            			<textarea name="invalidDescription"  id="payment-invalidDescription" cols="30" rows="1" data-toggle="autoheight"></textarea><br><br>
            		</td>
            	</tr>
            	<tr id="payment-return-tr">
            		<td colspan="3" align="center">
            			<button type="button" id="payment-return" class="btn-default" data-icon="arrow-down">Doc. Return(退回)</button>
            			<textarea name="returnDescription" id="payment-returnDescription" cols="30" rows="1" data-toggle="autoheight"></textarea>
            		</td>
            	</tr>
			</table>
    </div>
</div>