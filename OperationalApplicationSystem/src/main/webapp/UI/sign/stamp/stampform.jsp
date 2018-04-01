<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	var today = new Date().formatDate('yyyy-MM-dd');
	var chopDate=new Date(new Date().setDate(new Date().getDate()+5)).formatDate('yyyy-MM-dd');
	$.CurrentNavtab.find('#j_stamp_applicationDate').val(today);
	$.CurrentNavtab.find('#j_stamp_chopDate').val(chopDate);
	
	if('${param.id}'!=null&&'${param.id}'!=''){
		dataToFaceStamp('${param.id}');
	}
	//获取文件类型
	BJUI.ajax('doajax', {
	    url: 'getCheckType4Select.action',
	    loadingmask: true,
	    okCallback: function(json, options) {
            $.each(json, function (i, item) {
                $.CurrentNavtab.find('#j_stamp_documentType').append("<option value='" + item.id + "'>" + item.value + "</option>")           
            })
            $.CurrentNavtab.find('#j_stamp_documentType').selectpicker('refresh');
	    }
	})	
	
	
})





function faceToDataStamp(){
	var o=$.CurrentNavtab.find("#j_stamp_form").serializeJson();
	o.formFiller='${user.name}';
	o.formFillerID='${user.uid}';
	o.departmentOfFormFiller='${user.department.name}';
	o.departmentOfFormFillerID='${user.department.did}';
	o.id=$.CurrentNavtab.find("#j_stamp_id").val();
	o.attacmentUpload=listToString($.CurrentNavtab.find('#upfile_attacment_list'));
	var tmp='';
	if(typeof o.stampType == 'object' &&  o.stampType ){
		$.each(o.stampType,function(i,item){
			tmp+=item+'|';	
		})	
		o.stampType=tmp;
	}
	return o;
}

function dataToFaceStamp(id){
	BJUI.ajax('doajax', {
	    url: 'getStampByID.action',
	    loadingmask: true,
	    data:{id:id},	    
	    okCallback: function(json, options) {
	    	if(json.status='200'){
	    		$.CurrentNavtab.find('#upfile_attacment_list').children().eq(0).siblings().remove();
	    		
	    		$.CurrentNavtab.find("#j_stamp_applicationDate").val(json.applicationDate);
	    		$.CurrentNavtab.find("#j_stamp_applicationCode").val(json.applicationCode);
	    		$.CurrentNavtab.find("#j_stamp_formFiller").val(json.formFiller+"-"+json.formFillerID);
	    		$.CurrentNavtab.find("#j_stamp_departmentOfFormFiller").val(json.departmentOfFormFiller+"-"+json.departmentOfFormFillerID);
	    		$.CurrentNavtab.find("#j_stamp_applicantID").val(json.applicantID);
	    		$.CurrentNavtab.find("#j_stamp_applicant").val(json.applicant);
	    		$.CurrentNavtab.find("#j_stamp_departmentOfApplicant").val(json.departmentOfApplicant+"-"+json.departmentOfApplicantID);
	    		$.CurrentNavtab.find("#j_stamp_contactNumber").val(json.contactNumber);
	    		$.CurrentNavtab.find("#j_stamp_documentType").selectpicker().selectpicker('val',json.documentType).selectpicker('refresh');
	    		setProjectResponsible();	
	    		if(json.projectResponsible!=undefined&&json.projectResponsible!=""){
		    		$.CurrentNavtab.find("#j_stamp_projectResponsible").selectpicker().selectpicker('val',json.projectResponsible).selectpicker('refresh');
	    		}
	    		$.CurrentNavtab.find("#j_stamp_chopDate").val(json.chopDate);
	    		$.CurrentNavtab.find("#j_stamp_lendDate").val(json.lendDate);
	    		$.CurrentNavtab.find("#j_stamp_giveBackDate").val(json.giveBackDate);
	    		$.CurrentNavtab.find("#j_stamp_chopQty").val(json.chopQty);
	    		if(json.urgent=='1'){
            		$.CurrentNavtab.find("#j_stamp_urgent").iCheck('check'); 
            	}
	    		$.CurrentNavtab.find("#j_stamp_usageDescription").val(json.usageDescription);
            	$.CurrentNavtab.find("#j_stamp_chopObject").val(json.chopObject);
	    		var stype=json.stampType.split('|');
        		
        		$.each(stype,function(i,item){
					if(item=='CompanyChop')
						$.CurrentNavtab.find("#j_stamp_companyChop").iCheck('check'); 
					if(item=='LegalDeputyChop')
						$.CurrentNavtab.find("#j_stamp_legalDeputyChop").iCheck('check'); 
        		})
        		
        		var b=false;
        		if(json.state==0&&'${user.uid}'==json.applicantID){
        			b=true;
        		}
        		
        		
	    		if(json.attacmentUpload!=undefined&&json.attacmentUpload!=""){
            		var file=json.attacmentUpload.split('|');
            		
            		$.each(file,function(i,n){
            			var filename=n.split('/')[1];
            			if(filename!=undefined&&filename!=''){         				
            				$.CurrentNavtab.find('#upfile_attacment_list').append(fileToTr(filename,n,b));	   
            			}
            		})
            	}
	    		
        		var obj=$.CurrentNavtab.find('#stamp_approve_his');
        		var isReject=false;
        		var maxLevel=-1;
        		if(json.approveHis!=undefined&&json.approveHis!=""){
        			if(json.approveHis[json.approveHis.length-1].status=='Rejected'){
        				isReject=true;        				
        			}
        			$.each(json.approveHis,function(i,item){	  		
	    				obj.append("<tr><td>"+item.name+"</td><td style='display:none'></td><td>"+item.uId+"</td><td>"+item.uName+"</td><td>"+item.dId+"</td><td>"+item.comment+"</td><td>"+item.status+"</td><td>"+item.date+"</td><td></td></tr>");	    				
	    				maxLevel=item.level;
        			})
        						
        		}
	    		
	    		if(json.approve!=undefined&&json.approve!=""){	    			
	    			$.each(json.approve,function(i,item){	 
	    				if(isReject){
		    				if(i==0){
		    					if('${user.uid}'==item.uid){
				    				obj.append("<tr><td>"+item.name+"</td><td style='display:none'>"+item.id+"</td><td>"+item.uid+"</td><td>"+item.uname+"</td><td>"+item.did+"</td><td></td><td></td><td></td><td><button type='button' id='stamp-approve' class='btn btn-success' vstyle='width:50px;' onclick='stampApprove(this)'   >√</button>&nbsp;&nbsp;<button type='button' id='stamp-reject'  style='width:50px;' class='btn btn-danger' onclick='stampApprove(this)' >×</button></td></tr>");	    				
		    					}else{
				    				obj.append("<tr><td>"+item.name+"</td><td style='display:none'>"+item.id+"</td><td>"+item.uid+"</td><td>"+item.uname+"</td><td>"+item.did+"</td><td></td><td></td><td></td><td></td></tr>");	    				
		    					}
		    				
		    				}else{
			    				obj.append("<tr><td>"+item.name+"</td><td style='display:none'>"+item.id+"</td><td>"+item.uid+"</td><td>"+item.uname+"</td><td>"+item.did+"</td><td></td><td></td><td></td><td></td></tr>");	    				
		    				}
	    				}else{
	    					if(i>maxLevel){
			    				if(i==maxLevel+1){
			    					if('${user.uid}'==item.uid){
				    				obj.append("<tr><td>"+item.name+"</td><td style='display:none'>"+item.id+"</td><td>"+item.uid+"</td><td>"+item.uname+"</td><td>"+item.did+"</td><td></td><td></td><td></td><td><button type='button' id='stamp-approve' class='btn btn-success' style='width:50px;' onclick='stampApprove(this)'   >√</button>&nbsp;&nbsp;<button type='button' id='stamp-reject'  style='width:50px;' class='btn btn-danger' onclick='stampApprove(this)' >×</button></td></tr>");	    				
			    					}else{
					    				obj.append("<tr><td>"+item.name+"</td><td style='display:none'>"+item.id+"</td><td>"+item.uid+"</td><td>"+item.uname+"</td><td>"+item.did+"</td><td></td><td></td><td></td><td></td></tr>");	    				
			    					}
			    				}else{
				    				obj.append("<tr><td>"+item.name+"</td><td style='display:none'>"+item.id+"</td><td>"+item.uid+"</td><td>"+item.uname+"</td><td>"+item.did+"</td><td></td><td></td><td></td><td></td></tr>");	    				
			    				}    						
	    					}
	    				}
	    			})	    			
	    		}
        		

	    		
	    		showButtonStamp(json.state)
	    	}
	    }
	})
	
}


function submitStamp(){
	var id=$.CurrentNavtab.find("#j_stamp_id").val();
	
	if(id==''){
		BJUI.alertmsg('confirm', 'Submit?', {
		    okCall: function() {
		    	submitStampAjax(id)
		    }
		})	
	}else{
		submitStampAjax(id)
	}
	
}

function submitStampAjax(id){
	var o=faceToDataStamp();	
	var err=checkSaveStamp(o);
	if(err!=''){
		BJUI.alertmsg('warn', err); 
		return false;
	}
	
	BJUI.ajax('doajax', {
	    url: 'submitStamp.action',
	    loadingmask: true,
	    data:{id:id,json:JSON.stringify(o)},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	BJUI.alertmsg('info', json.message); 
            	showButtonStamp('1');
				dataToFaceStamp(json.params.id)          		 
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	})	
}

function deleteStamp(){
	BJUI.alertmsg('confirm', 'Delete?', {
		   okCall: function() {
				BJUI.ajax('doajax', {
				    url: 'deleteStamp.action',
				    loadingmask: true,
				    data:{json:JSON.stringify(o)},	    
				    okCallback: function(json, options) {
			            if(json.status='200'){
			            	closeCurrentTab();
			            }else{
			            	 BJUI.alertmsg('error', json.message); 
			            }
				    }
				});	
			  			   
		   }
	})

	
}

function saveStamp(){
	var o=faceToDataStamp();	
	var err=checkSaveStamp(o);
	if(err!=''){
		BJUI.alertmsg('warn', err); 
		return false;
	}
	

		
	BJUI.ajax('doajax', {
	    url: 'saveStamp.action',
	    loadingmask: true,
	    data:{json:JSON.stringify(o)},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find("#j_stamp_id").val(json.params.id);
            	 $.CurrentNavtab.find("#j_stamp_applicationCode").val(json.params.applicationCode);
            	 showButtonStamp('0');
               	 BJUI.alertmsg('confirm', 'Save successfully, submit or not ? ',{
             		    okCall: function() {
             		    	submitStamp();
           		    	 }	  
           		    });  

            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});		
	
	
}


function stampApprove(o){
	
	bootbox.prompt({
		size:"small",
		title:"Comment?", 
		callback:function (result) {
		if(result!=null){
		if($(o).html()=='√'){
			status='Approved'
		}else{
			status='Rejected'
		}
		if(status=='Rejected'&&(result==''||result==undefined)){
			bootbox.alert("Comment can`t empty!");
			 return false;
		}
		
		approveId = $(o).parent().siblings().eq(1).html();
		
		
			BJUI.ajax('doajax', {
		    url: 'submitApprove.action',
		    loadingmask: true,
		    data:{status:status,comment:result,tradeId:$.CurrentNavtab.find("#j_stamp_id").val(),approveId:approveId,type:'STAMP'},	    
		    okCallback: function(json, options) {
	            if(json.status='200'){
	            	 BJUI.alertmsg('info', json.message); 
	            	 $(o).parent().siblings().eq(5).html(json.params.data.comment);
	            	 $(o).parent().siblings().eq(6).html(json.params.data.status);
	            	 $(o).parent().siblings().eq(7).html(json.params.data.date);
	            	 $(o).hide().siblings().hide();
	            }else{
	            	 BJUI.alertmsg('error', json.message); 
	            }
		    }
		});		
		}
	}
})
		             
		             
		             
/* 	BJUI.alertmsg('prompt', '', {
		title:'Comment',
		okCall:function(val) {
			
			if($(o).html()=='√'){
				status='Approved'
			}else{
				status='Rejected'
			}
			approveId = $(o).parent().siblings().eq(1).html();
			
			
 			BJUI.ajax('doajax', {
			    url: 'submitApprove.action',
			    loadingmask: true,
			    data:{status:status,comment:val,tradeId:$.CurrentNavtab.find("#j_stamp_id").val(),approveId:approveId,type:'STAMP'},	    
			    okCallback: function(json, options) {
		            if(json.status='200'){
		            	 BJUI.alertmsg('info', json.message); 
		            	 $(o).parent().siblings().eq(5).html(json.params.data.comment);
		            	 $(o).parent().siblings().eq(6).html(json.params.data.status);
		            	 $(o).parent().siblings().eq(7).html(json.params.data.date);
		            	 $(o).hide().siblings().hide();
		            }else{
		            	 BJUI.alertmsg('error', json.message); 
		            }
			    }
			});		 
			
			
		}
	}
	
	) */
}


function showButtonStamp(state){
	if(state==0){
		 $.CurrentNavtab.find('#stamp-delete').show();
	}else if(state==1){		
		 $.CurrentNavtab.find('#stamp-save').hide();
		 $.CurrentNavtab.find('#stamp-submit').hide();
		 $.CurrentNavtab.find('#file').attr('disabled','disabled');
		 $.CurrentNavtab.find('#j_stamp_delete').hide();
		 $.CurrentNavtab.find('#stamp-delete').hide();
		 $("input[id*='j_stamp']").attr('disabled','disabled');
		 $("select[id*='j_stamp']").attr('disabled','disabled');
		 $("textarea[id*='j_stamp']").attr('disabled','disabled');
		 $.CurrentNavtab.find('#stamp_approve_his').show();
	}else if(state==2){		
		 $.CurrentNavtab.find('#stamp-save').hide();
		 $.CurrentNavtab.find('#stamp-submit').hide();
		 $.CurrentNavtab.find('#file').attr('disabled','disabled');
		 $.CurrentNavtab.find('#j_stamp_delete').hide();
		 $.CurrentNavtab.find('#stamp-delete').hide();
		 $("input[id*='j_stamp']").attr('disabled','disabled');
		 $("select[id*='j_stamp']").attr('disabled','disabled');
		 $("textarea[id*='j_stamp']").attr('disabled','disabled').show();	
	}

}

function listToString(id){
	var tr=$.CurrentNavtab.find(id).find('tr');
	var s="";
	$.each(tr,function(i,n){
		if(i>0){
			s=s+$.CurrentNavtab.find(n).children().eq(0).find('a').attr('url')+"|";		
		}
	})
	return s;
}

function fileToTr(name,path,b){
	if(b){
		return "<tr><td align='center'><a onclick=\"getFile('"+path.replace('\\','\\\\')+"')\" url='"+path.replace('\\','\\\\')+ "' >"+name+"</></td><td align='center'><a onclick=\"deleteFile('"+path.replace('\\','\\\\')+"',this)\" >Delete</a></td></tr>"
	}else{
		return "<tr><td align='center'><a onclick=\"getFile('"+path.replace('\\','\\\\')+"')\" url='"+path.replace('\\','\\\\')+ "' >"+name+"</></td><td align='center'></td></tr>"
	}
}


function deleteFile(path,o){
	var pid=$.CurrentNavtab.find("#j_stamp_id").val();
	BJUI.ajax('doajax', {
	    url:'deleteFileOfStamp.action',
	    data:{dfile:path,id:pid},
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	$.CurrentNavtab.find(o).parent().parent().remove();	
        		
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	})
	
}


function checkSaveStamp(o){
	var err='';
	if(o.stampType==null||o.stampType==''){
		err+=" Stamp Type can`t be empty！<br>";		
	}
	if(o.applicant==null||o.applicant==''){
		err+=" Applicant can`t be empty！<br>";				
	}
	if(o.documentType==null||o.documentType==''){
		err+=" Document Type can`t be  empty！<br>";				
	}
	
	if(o.documentType=='1'){
		if(o.projectResponsible==null||o.projectResponsible==''){
			err+=" Project Responsible can`t be  empty！<br>";				
		}	
	}
	
	if((o.chopDate==null||o.chopDate=='')&&(o.lendDate==null||o.lendDate=='')){
		err+=" Chop Date And Lend Date can`t be all empty！<br>";				
	}
	
	if(o.lendDate!=null&&o.lendDate!=''){
		if(o.giveBackDate==null||o.giveBackDate==''){
			err+=" Give Back Date can`t be  empty！<br>";		
		}	
	}
	if(o.chopQty==null||o.chopQty==''){
		err+=" Chop Qtye can`t be  empty！<br>";				
	}
	if(o.chopObject==null||o.chopObject==''){
		err+=" Chop Object can`t be  empty！<br>";				
	}
	
	if(o.urgent&&(o.urgentReason==null||o.urgentReason=='')){
		err+=" Uregnt Reason can`t be  empty！<br>";		
	}
	if(o.usageDescription==null||o.usageDescription==''){
		err+=" Usage Description can`t be  empty！<br>";		
	}
	if(o.formFillerID==null||o.formFillerID==''||o.formFillerID==undefined){
		err+= "数据异常，用户为空，请刷新页面重新填写！"; 
	}
	if($.CurrentNavtab.find('#j_stamp_projectResponsible').find('option').length>1){
		if($.CurrentNavtab.find('#j_stamp_projectResponsible').val()==null||$.CurrentNavtab.find('#j_stamp_projectResponsible').val()==''){
			err+= "Project Responsible can`t be  empty！<br>"; 
		}
	}

	return err;
}


function getUser() {
	var id = $.CurrentNavtab.find('#j_stamp_applicantID').val();
	BJUI.ajax('doajax', {
		url : 'getUserByID.action',
		loadingmask : true,
		data : {
			uid : id
		},
		okCallback : function(json, options) {
			if (json.length > 0) {
				$.CurrentNavtab.find('#j_stamp_applicant').val(json[0].name);
				$.CurrentNavtab.find('#j_stamp_departmentOfApplicant').val(json[0].department.name+"-"+json[0].department.did);
			} else {
				BJUI.alertmsg('error', 'userid不存在');
				$.CurrentNavtab.find('#j_stamp_applicant').val('');
				$.CurrentNavtab.find('#j_stamp_departmentOfApplicant').val('');
			}
		}
	})

}

function setProjectResponsible(){
	var type=$.CurrentNavtab.find('#j_stamp_documentType').val();
	$.CurrentNavtab.find('#j_stamp_projectResponsible').find('option').remove().append("<option value=''></option>").selectpicker('refresh');
	$.CurrentNavtab.find('#j_stamp_projectResponsible').append("<option value=''></option>").selectpicker('refresh');
	
	//获取一级签核人员
	BJUI.ajax('doajax', {
	    url: 'getFirstApproveOfStamp4Select.action',
	    loadingmask: false,
	    data:{type:type},
	    async:false,
	    okCallback: function(json, options) {
            $.each(json, function (i, item) {
            	if(item.fistUID!='Dept. Head'){
                    $.CurrentNavtab.find('#j_stamp_projectResponsible').append("<option value='" + item.uid + "'>" + item.name + "</option>")                     		
            	}
            })
            $.CurrentNavtab.find('#j_stamp_projectResponsible').selectpicker('refresh');
	    }
	})
	
}

</script>




<div class="bjui-pageContent">
    <div class="bs-example" style="width:1000px">
        <form id="j_stamp_form" data-toggle="ajaxform">
			<input type="hidden" name="id" id="j_stamp_id" value="${param.id}">
			<input type="hidden" name="state" id="j_stamp_state" value="">
            <div class="bjui-row-0" align="center">
            <h2 class="row-label">Seal Application System 借/用 章 申 请 系 统</h2><br> 
            </div>
			<table class="table" style="font-size:12px;">
				<tr>
					<td width="250px">Application Date<br>申请日期:</td>
					<td width="250px"><input type="text" size="19" name="applicationDate" data-nobtn="true" id="j_stamp_applicationDate" value=""  readonly="" ></td>
					<td width="250px">Application Code<br>申请单号:</td>
					<td width="250px"><input type="text" size="19" name="applicationCode" data-nobtn="true" id="j_stamp_applicationCode" value="" placeholder="保存或者送审后生成"  readonly=""></td>					
				</tr>
				<tr>
					<td>
						Form Filler:<br>填单人:
					</td>
					<td>
						<input type="text" name="formFiller" size="19" value="${user.name}-${user.uid}" readonly=""  id="j_stamp_formFiller"  >
					</td>
					<td>
						Department of Form Filler:<br>填单人部门:
					</td>
					<td>
						<input type="text" name="departmentOfFormFiller" value="${user.department.name}-${user.department.did}" readonly="" id="j_stamp_departmentOfFormFiller" size="19">
					</td>					
				</tr>
				<tr>
					<td>
						ApplicantID <label style="color:red;font-size:12px"><b>*</b></label>:<br>申请人ID <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td>
						<input type="text" name="applicantID" value="${user.uid}" id="j_stamp_applicantID"  data-rule="required" size="19" onchange="getUser()">
					</td>
					<td>
						Applicant <label style="color:red;font-size:12px"><b>*</b></label>:<br>申请人 <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td>
						<input type="text" name="applicant" value="${user.name}" id="j_stamp_applicant" readonly="" data-rule="required" size="19">
					</td>
				</tr>
				<tr>
					<td>
						Department of Applicant:<br>申请人部门:
					</td>
					<td>
						<input type="text" name="departmentOfApplicant" value="${user.department.name}-${user.department.did}" id="j_stamp_departmentOfApplicant" readonly=""  size="19">
					</td>	
					<td>
						Contact Number:<br>联系方式 :
					</td>
					<td>
						<input type="text" name="contactNumber" id="j_stamp_contactNumber" value="" size="19" data-rule="phone">
					</td>
				</tr>
				<tr>
					<td>
						Seal Type<label style="color:red;font-size:12px"><b>*</b></label>:<br>
						印章种类 <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td>
						<input type="checkbox" name="stampType" data-toggle="icheck" id="j_stamp_companyChop" value="CompanyChop" data-label="Company Chop 公章">
					</td>
					<td>
						<input type="checkbox" name="stampType" data-toggle="icheck" id="j_stamp_legalDeputyChop" value="LegalDeputyChop" data-label="Legal Deputy Chop 法人章">
					</td>	
					<td>
					</td>				
				</tr>
				<tr>
					<td>
						Document Type <label style="color:red;font-size:12px"><b>*</b></label>:<br>文件类型  <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="3">
						<select name="documentType" data-toggle="selectpicker" id="j_stamp_documentType" data-rule="required" data-width="500px" onchange="setProjectResponsible()">
                        	<option value="" selected></option>
                    	</select>
					</td>
				</tr>
				<tr>
					<td>
						Project Responsible:<br>审批人选择 :
					</td>
					<td colspan="3">
						<select name="projectResponsible" data-toggle="selectpicker" id="j_stamp_projectResponsible" data-width="500px" >
                        	<option value="" selected></option>
                        </select>
					</td>					
				</tr>
				<tr>
					<td colspan="4">
					Remark: Stamp which lend in the same day need to give back, if need special usage need to make request again.
					<br>备注：原则上，印章当天借出，当天应该归还，若有特殊需求，请再提单
					</td>
				</tr>
				<tr>
					<td>
						Chop Date <label style="color:red;font-size:12px"><b>*</b></label>:<br>用印日期 <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td>
						<input type="text" size="19" name="chopDate" data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_stamp_chopDate" value=""  />
					</td>
					<td colspan="2">

					</td>					
				</tr>
				<tr>
					<td>
						Lend Date:<br>借印日期 :
					</td>
					<td>
						<input type="text" size="19" name="lendDate" data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_stamp_lendDate" value=""  />
					</td>
					<td>
						Give Back Date:<br>归还日期 :
					</td>
					<td>
						<input type="text" size="19" name="giveBackDate" data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_stamp_giveBackDate" value=""  />
					</td>					
				</tr>
				<tr>
					<td>
						Chop Qty <label style="color:red;font-size:12px"><b>*</b></label>:<br>用印份数  <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td>
						<input type="text" name="chopQty" value="" id="j_stamp_chopQty" size="19" data-rule="required;int" />
					</td>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td>
						Chop Object<label style="color:red;font-size:12px"><b>*:</b></label>:<br>受文单位 <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="3">
						<textarea cols="80" rows="3" id="j_stamp_chopObject"  name="chopObject" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Urgent:<br>急件:
					</td>
					<td>
						<input type="checkbox" name="urgent"  data-toggle="icheck" id="j_stamp_urgent" value="1" data-label="">
					</td>
					<td>
						Urgent Reason:<br>申请急件原因:
					</td>
					<td colspan="3">
						<input type="text" name="urgentReason" value="" id="j_stamp_urgentReason" size="19"  />
					</td>
				</tr>
				<tr>
					<td>
						Usage Description<label style="color:red;font-size:12px"><b>*</b></label>:<br>申请原因<label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="3">
						<textarea cols="80" rows="3" id="j_stamp_usageDescription"  name="usageDescription" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Attacment Upload:<br>附件上传:
					</td>
					<td>
						<input type="file" id="file" name="file" onchange="ajaxFileUpload('file','upfile_attacment_list');"/>
					</td>
					<td colspan="2" >
						<table class="table" id="upfile_attacment_list" >	
							<tr>
								<th width="400px" align="center">File Name</th>
								<th width="100px" align="center" id="j_stamp_delete">Delete</th>
							</tr>									
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					Mark: Please point the location where need chop
					<br>备注：请在附件中指出盖章的位置
					</td>
				</tr>

				
				
				<tr>
					<td colspan="4" align="center">
	            		<button type="button" id="stamp-save" class="btn-default" data-icon="save" onClick="saveStamp()" >Save</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="stamp-submit" class="btn-default" data-icon="arrow-up" onClick="submitStamp()">Submit</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="stamp-delete" class="btn-default" data-icon="close" onClick="deleteStamp()" style="display:none">Delete</button>
            		</td>				
				</tr>	
				<tr>
				<td colspan="4" >
				Approval Route:<br>
				签核路径:
				</td>
				</tr>		
				<tr>
					<td colspan="4">
						<table class="table" width="100%" id="stamp_approve_his" style="display:none">
							<tr>
								<th width="80px">
								
								</th>
								<th style="display:none;">
								
								</th>
								<th width="80px" >
									Cimtas ID
								</th>
								<th width="120px" >
									Approvel Name
								</th>
								<th width="80px" >
									BU NO.
								</th>
								<th width="230px" >
									Comment
								</th>
								<th width="80px" >
									Status
								</th>
								<th width="150px" >
									Operation Date
								</th>
								<th width="150px"align="center" >
									Operation
								</th>
							</tr>					
						</table>	
					</td>
				</tr>
			</table>		


        </form>
    </div>
</div>