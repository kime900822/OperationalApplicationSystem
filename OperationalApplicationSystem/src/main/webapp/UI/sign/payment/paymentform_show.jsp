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
});

function dataToFace(){
	
	BJUI.ajax('doajax', {
	    url: 'getPaymentByID.action',
	    loadingmask: true,
	    data:{id:'${param.id}'},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	$("#applicationDate").html(json.applicationDate);
            	$("#requestPaymentDate").html(json.requestPaymentDate);
            	$("#contacturalPaymentDate").html(json.contacturalPaymentDate);
            	$("#code").html(json.code);
            	if(json.payType=='Cash'){
            		$("#Cash").attr('checked','checked');
            	}else if(json.payType=='Banking'){
            		$("#Banking").attr('checked','checked');
            	}else if(json.payType=='AdvanceWriteoff'){
            		$("#AdvanceWriteoff").attr('checked','checked');
            		$("#AdvanceWriteoffText").html(json.advanceWriteoffWay+",Amount:"+json.advanceWriteOffCurrency+" "+formatCurrency(json.advanceWriteOffAmount))
            	}
            	if(json.urgent=='1'){
            		$("#urgent").html("Urgent : ●");
            	}
            	$("#UID").html(json.UID+'<br>'+json.UName);
            	$("#departmentID").html(json.departmentName+'<br>'+json.departmentID);
            	$("#beneficiary").html(json.beneficiary+'<br>'+json.beneficiaryE)
            	$("#beneficiaryAccountNO").html(json.beneficiaryAccountBank+'<br>'+json.beneficiaryAccountNO)
                if(json.beneficiaryChange=='1'){
            		$("#beneficiaryChange").html("●"); 
            		$("#beneficiary_td").attr("class","Beneficiarychange");
            	}
                if(json.beneficiaryAccountNOChange=='1'){
            		$("#beneficiaryAccountNOChange").html("●") 
            		$("#beneficiaryAccountNO_td").attr("class","BeneficiaryIDchange");
            	}
                
                
                
                $("#paymentSubject_"+json.paymentSubject).html("Y")  
                $("#paymentDays_"+json.paymentTerm).html(json.paymentDays_1)  

            	
            	if(json.amount_1!=''&&json.amount_1!=null&&json.amount_1!='0.00'){
            		$("#amount_1").html(formatCurrency(json.amount_1)+"&nbsp&nbsp");
            		$("#receivingOrApprovalDate_1").html(json.receivingOrApprovalDate_1);
                	$("#PONo_1").html(json.PONo_1);
                	$("#currency_1").html(json.currency_1);
            	}
    
            	
            	if(json.amount_2!=''&&json.amount_2!=null&&json.amount_2!='0.00'){
            		$("#amount_2").html(formatCurrency(json.amount_2)+"&nbsp&nbsp");
            		$("#receivingOrApprovalDate_2").html(json.receivingOrApprovalDate_2);
                	$("#PONo_2").html(json.PONo_2);
                	$("#currency_2").html(json.currency_2);
            	}           		

            	
            	if(json.amount_3!=''&&json.amount_3!=null&&json.amount_3!='0.00'){
            		$("#amount_3").html(formatCurrency(json.amount_3)+"&nbsp&nbsp");
            		$("#receivingOrApprovalDate_3").html(json.receivingOrApprovalDate_3);
                	$("#PONo_3").html(json.PONo_3);
                	$("#currency_3").html(json.currency_3);
            	}
            		
            	
            	if(json.amount_4!=''&& json.amount_4!=null&&json.amount_4!='0.00'){
            		$("#amount_4").html(formatCurrency(json.amount_4)+"&nbsp&nbsp");
            		$("#receivingOrApprovalDate_4").html(json.receivingOrApprovalDate_4);
                	$("#PONo_4").html(json.PONo_4);
                	$("#currency_4").html(json.currency_4);
            	}
            		
            	
            	if(json.amount_5!=''&& json.amount_5!=null&&json.amount_5!='0.00'){
            		$("#amount_5").html(formatCurrency(json.amount_5)+"&nbsp&nbsp");
            		$("#receivingOrApprovalDate_5").html(json.receivingOrApprovalDate_5);
                	$("#PONo_5").html(json.PONo_5);
                	$("#currency_5").html(json.currency_5);
            	}
            		
            	
            	if(json.amount_6!=''&& json.amount_6!=null&&json.amount_6!='0.00'){
            		$("#amount_6").html(formatCurrency(json.amount_6)+"&nbsp&nbsp");
            		$("#receivingOrApprovalDate_6").html(json.receivingOrApprovalDate_6);
                	$("#PONo_6").html(json.PONo_6);
                	$("#currency_6").html(json.currency_6);
            	}
            		
            	$("#supplierCode").html(json.supplierCode);
            	$("#refNoofBank").html(json.refNoofBank);
            	$("#usageDescription").html(json.usageDescription.replace(/\r\n/g,"<br>"));

            	if(json.amountInFigures!=''&&json.amountInFigures!=null){
            		$("#amountInFigures").html(json.currency_1+"&nbsp&nbsp"+formatCurrency(json.amountInFigures)+"&nbsp&nbsp");
            		$("#amountInWords").html("&nbsp&nbsp"+smalltoBIG(json.amountInFigures));
            	}
            	
            	$("#documentAudit").html("&nbsp&nbsp"+json.documentAudit);
            	$("#deptManager").html("&nbsp&nbsp"+json.deptManager);
            	if(json.state=='5'){
            		$("#invalid-img").show();
            	}

            	
            	if(json.file_invoice!=undefined&&json.file_invoice!=""){
            		var file=json.file_invoice.split('|');
            		
            		$.each(file,function(i,n){
            			var filename=n.split('/')[1];
            			if(filename!=undefined&&filename!=''){         				
            				$.CurrentNavtab.find('#upfile_invoice_list').append(fileToTr(filename,n,b));	   
            			}
            		})
            	}
            	if(json.file_other!=undefined&&json.file_other!=""){
            		var file=json.file_other.split('|');
            		$.each(file,function(i,n){
            			var filename=n.split('/')[1];
            			if(filename!=undefined&&filename!=''){
            				$.CurrentNavtab.find('#upfile_other_list').append(fileToTr(filename,n,b));	 
            			}
            		})
            	}
            	if(json.file_contract!=undefined&&json.file_contract!=""){
            		var file=json.file_contract.split('|');
            		$.each(file,function(i,n){
            			var filename=n.split('/')[1];
            			if(filename!=undefined&&filename!=''){
            				$.CurrentNavtab.find('#upfile_contract_list').append(fileToTr(filename,n,b));
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
		$.CurrentNavtab.find('#payment-save').show();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-assign').hide();
		$.CurrentNavtab.find('#payment-acc').hide();
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
		$("input[id*='j_payment']").attr('disabled','disabled');
		$("select[id*='j_payment']").attr('disabled','disabled');
		$("textarea[id*='j_payment']").attr('disabled','disabled')
	}else{
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
		if(print=='1'){
			$.CurrentNavtab.find('#payment-delete').hide();
		}else{
			$.CurrentNavtab.find('#payment-delete').show();
		}
		$.CurrentNavtab.find('#payment-invalid-tr').hide();
		$.CurrentNavtab.find('#payment-return-tr').hide();	
	}else if(state=="4"){//财务处理完成  非财务人员查看。可打印
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
		 $("textarea[id*='j_payment']").attr('disabled','disabled')
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
		 $("textarea[id*='j_payment']").attr('disabled','disabled')
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
	}else if(state=="2"&&documentAuditid=='${user.uid}'){//审批通过 财务处理
		$.CurrentNavtab.find('#payment-save').hide();
		$.CurrentNavtab.find('#payment-submit').hide();
		$.CurrentNavtab.find('#payment-approve').hide();
		$.CurrentNavtab.find('#payment-reject').hide();
		$.CurrentNavtab.find('#payment-print').show();
		$.CurrentNavtab.find('#payment-delete').hide();
		$.CurrentNavtab.find('#j_payment_contacturalPaymentDate').removeAttr('readonly')
		$.CurrentNavtab.find('#payment-assign').show();
		$.CurrentNavtab.find('#payment-acc').show();
		$.CurrentNavtab.find('#payment-invalid-tr').show();
		$.CurrentNavtab.find('#payment-return-tr').show();	
		
		$.CurrentNavtab.find('#j_payment_documentAudit').val('${user.name}')
		$("input[id*='j_payment']").attr('disabled','disabled');
		$("select[id*='j_payment']").attr('disabled','disabled');
		$("textarea[id*='j_payment']").attr('disabled','disabled')
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
		$("textarea[id*='j_payment']").attr('disabled','disabled')
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
		$("textarea[id*='j_payment']").attr('disabled','disabled')
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
	}else if(state=="5"){
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
		$("textarea[id*='j_payment']").attr('disabled','disabled')
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
		$("textarea[id*='j_payment']").attr('disabled','disabled')
		$.CurrentNavtab.find('#upfile_other').hide();
		$.CurrentNavtab.find('#upfile_contract').hide();
		$.CurrentNavtab.find('#upfile_invoice').hide();
	}
	
	}
	
}


</script>
<div id="invalid-img" style="z-index:99;position:absolute;top:200px;left:200px;width:800px;height:400px;display: none;">
	<img alt="" src="../../images/invalid.png">
</div>
    <div style="width:1200px;padding:20px;" >
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
					<td width="200px" align="left"><img  style="width:300px;height:60px;" alt="payment" src="../../images/printLogo.png"></td>
					<td colspan="2"  align="center"><h1 align="left" style="font-weight:bold">Cimtas(NingBo) Steel Processing CO.,LTD 庆达西（宁波）钢构制造有限公司</h1>
					<h2 align="center" style="font-weight:bold">Payment Application Form 付款申请单</h2>
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
					<td width="160px" ><label>Application Date(申请日期):</label></td>
					<td width="140px" align="left"><label id="applicationDate"></label></td>
					<td width="210px" ><label>Request Payment Date(要求付款日期):</label></td>
					<td width="190px"  align="left"><label id="requestPaymentDate"></label></td>
					<td width="230px" ><label>Contactural Payment Date(合同付款日期):</label></td>
					<td width="170px" align="left"><label id="contacturalPaymentDate"></label></td>
					<td width="100px"></td>
				</tr>
				<tr height="30px">
					<td>
						<input type="checkbox" id="Cash" ><label>&nbsp&nbsp支付现金 Cash</label>
					</td>
					<td colspan="2">
						<input type="checkbox" id="Banking" ><label>&nbsp&nbsp银行支付 Banking</label>
					</td>
					<td >
					</td>
					<td colspan="3">
						<input type="checkbox" id="AdvanceWriteoff"><label>&nbsp&nbsp核销预付 Advance Write-off&nbsp&nbsp&nbsp&nbsp</label>
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
					<td colspan="4" rowspan="8"  id="usageDescription" style="word-wrap: break-word; word-break: normal;"></td>
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
					<td>
						 <table class="table" id="upfile_invoice_list" >	
							<tr>
								<th width="400px" align="center">File Name</th>
								<th width="100px" align="center">Delete</th>
							</tr>									
						</table>
					</td>
					<td>
						<table class="table" id="upfile_contract_list" >	
							<tr>
								<th width="400px" align="center">File Name</th>
								<th width="100px" align="center">Delete</th>
							</tr>									
						</table>
					</td>
					<td>
						<table class="table" id="upfile_other_list" >	
							<tr>
								<th width="400px" align="center">File Name</th>
								<th width="100px" align="center">Delete</th>
							</tr>									
						</table>
					</td>
				</tr>
				<tr>
					<td align="center">
					
						<button type="button" id="payment-approve" class="btn-default" data-icon="check" >Approve(同意)</button>
	            		<button type="button" id="payment-reject" class="btn-default" data-icon="close" >Reject(拒绝)</button>
	            		<button type="button" id="payment-assign" class="btn-default" data-icon="undo" >Assign(转交)</button>
	            		<button type="button" id="payment-acc" class="btn-default" data-icon="check" >Approve(同意)</button>
	            		<button type="button" id="payment-print" class="btn-default" data-icon="print" >Print Out(打印)</button>
					</td>
				</tr>
			</table>
    </div>