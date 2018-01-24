<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
  



<!-- core - css -->
<link href="../../B-JUI/themes/css/style.css" rel="stylesheet">
<link href="../../B-JUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
<link href="../../B-JUI/themes/css/fontsize.css" id="bjui-link-theme" rel="stylesheet">
<!-- plug - css -->
<link href="../../B-JUI/plugins/kindeditor_4.1.11/themes/default/default.css" rel="stylesheet">
<link href="../../B-JUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="../../B-JUI/plugins/nice-validator-1.0.7/jquery.validator.css" rel="stylesheet">
<link href="../../B-JUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
<link href="../../B-JUI/plugins/webuploader/webuploader.css" rel="stylesheet">
<link href="../../B-JUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
<!--[if lte IE 7]>
<link href="B-JUI/themes/css/ie7.css" rel="stylesheet">
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
    <script src="B-JUI/other/html5shiv.min.js"></script>
    <script src="B-JUI/other/respond.min.js"></script>
<![endif]-->
<!-- jquery -->
<script src="../../B-JUI/js/jquery-1.11.3.min.js"></script>
<script src="../../B-JUI/js/jquery.cookie.js"></script>
<!--[if lte IE 9]>
<script src="B-JUI/other/jquery.iframe-transport.js"></script>
<![endif]-->
<!-- B-JUI -->
<%-- <script src="../../B-JUI/js/bjui-all.min.js"></script> --%>
<script src="../../B-JUI/js/bjui-all.js"></script>
<!-- plugins -->
<!-- swfupload for kindeditor -->
<script src="../../B-JUI/plugins/swfupload/swfupload.js"></script>
<!-- Webuploader -->
<script src="../../B-JUI/plugins/webuploader/webuploader.js"></script>
<!-- kindeditor -->
<script src="../../B-JUI/plugins/kindeditor_4.1.11/kindeditor-all-min.js"></script>
<script src="../../B-JUI/plugins/kindeditor_4.1.11/lang/zh-CN.js"></script>
<!-- colorpicker -->
<script src="../../B-JUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- ztree -->
<script src="../../B-JUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script src="../../B-JUI/plugins/nice-validator-1.0.7/jquery.validator.js"></script>
<script src="../../B-JUI/plugins/nice-validator-1.0.7/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script src="../../B-JUI/plugins/bootstrap.min.js"></script>
<script src="../../B-JUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<script src="../../B-JUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
<!-- icheck -->
<script src="../../B-JUI/plugins/icheck/icheck.min.js"></script>
<!-- HighCharts -->
<script src="../../B-JUI/plugins/highcharts/highcharts.js"></script>
<script src="../../B-JUI/plugins/highcharts/highcharts-3d.js"></script>
<script src="../../B-JUI/plugins/highcharts/themes/gray.js"></script>
<!-- other plugins -->
<script src="../../B-JUI/plugins/other/jquery.autosize.js"></script>
<link href="../../B-JUI/plugins/uploadify/uploadify.css" rel="stylesheet">
<script src="../../B-JUI/plugins/uploadify/jquery.uploadify.min.js"></script>
<script src="../../B-JUI/plugins/download/jquery.fileDownload.js"></script>
<!-- util -->
<script src="../../B-JUI/js/util.js"></script>
<!-- init -->

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
            	}
            	if(json.urgent=='1'){
            		$("#urgent").html("●");
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
/*                 if(json.paymentDays_1!=""&&json.paymentDays_1!=null){
                	$("#paymentDays_1").html(json.paymentDays_1+"days");
                }
                if(json.paymentDays_2!=""&&json.paymentDays_2!=null){
                	$("#paymentDays_2").html(json.paymentDays_2+"days");
                }
                if(json.paymentDays_3!=""&&json.paymentDays_3!=null){
                	$("#paymentDays_3").html(json.paymentDays_3+"days");
                }
                if(json.paymentDays_4!=""&&json.paymentDays_4!=null){
                	$("#paymentDays_4").html(json.paymentDays_4+"days");
                }
                if(json.paymentDays_5!=""&&json.paymentDays_5!=null){
                	$("#paymentDays_5").html(json.paymentDays_5+"days");
                }
                if(json.paymentDays_6!=""&&json.paymentDays_6!=null){
                	$("#paymentDays_6").html(json.paymentDays_6+"days");
                }
 */
               
            	
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

            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	

	
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
				<tr height="10px">
					<td colspan="2" align="right" >
						<label>流水码:</label>
					</td>
					<td align="center"><label id="code"></label></td>
				</tr>
				<tr>
					<td width="200px" align="left"><img  style="width:300px;height:60px;" alt="payment" src="../../images/printLogo.png"></td>
					<td colspan="2"  align="center"><h1 align="left" style="font-weight:bold">Cimtas(NingBo) Steel Processing CO.,LTD 庆达西（宁波）钢构制造有限公司</h1>
					<h2 align="center" style="font-weight:bold">Paymengt Application Form 付款申请单</h2>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right" style="">
						<label style="font-size:10px">Urgent:</label>
					</td>
					<td align="center" width="20px" style="text-align: left;">&nbsp&nbsp&nbsp<label id="urgent" ></label></td>
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
						<input type="checkbox" id="AdvanceWriteoff"><label>&nbsp&nbsp核销预付 Advance Write-off (Amount) .&nbsp&nbsp&nbsp&nbsp</label> 
					</td>
				</tr>
			</table>            
			
			<table width="1200px" border="1" cellspacing="0" border-bottom="0px" bordercolor="black" style="font-size:10px;">
				<tr style="display:none">
					<td width="30px" style="height:0px;line-height:0px;font-size:0px;"></td>
					<td width="140px"></td>
					<td width="50px"></td>
					<td width="120px"></td>
					<td width="100px"></td>
					<td width="50px"></td>
					<td width="60px"></td>
					<td width="110px"></td>
					<td width="100px"></td>
					<td width="160px"></td>
					<td width="50px"></td>
					<td width="130px"></td>
					<td width="100px"></td>
				</tr>
				<tr height="20px">
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
				<tr height="20px">
					<td colspan="4"  class="bg" height="12px">&nbspBeneficiary:</td>
					<td style="text-align:center">
						<label id="beneficiaryChange" ></label>
					</td>
				</tr>
				<tr height="20px">
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
				<tr height="20px">
					<td colspan="4"  class="bg">&nbspBeneficiary Account NO:</td>
					<td style="text-align:center">
						<label id="beneficiaryAccountNOChange" ></label>
					</td>
				</tr>
				<tr height="20px">
					<td colspan="2" align="center" class="bg">付款项目<br/>Payment Subject</td>
					<td colspan="2" align="center" class="bg">结算期 <br/>Payment Term</td>
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
					<td width="25%">Finance Supervisor:<br/>财务主管</td>
					<td width="10%">Dept. Manager:<br/>部门经理</td>		
					<td width="15%" align="left"><label id="deptManager"></label></td>		
				</tr>
			</table>
    </div>