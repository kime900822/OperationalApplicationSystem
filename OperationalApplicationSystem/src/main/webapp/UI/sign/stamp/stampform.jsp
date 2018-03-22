<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	var today = new Date().formatDate('yyyy-MM-dd');
	$.CurrentNavtab.find('#j_stamp_applicationDate').val(today);
	
	if('${param.id}'!=null&&'${param.id}'!=''){
		dataToFace();
	}
	//获取文件类型
	BJUI.ajax('doajax', {
	    url: 'getCheckType4Select.action',
	    loadingmask: false,
	    okCallback: function(json, options) {
            $.each(json, function (i, item) {
                $.CurrentNavtab.find('#j_stamp_documentType').append("<option value='" + item.Id + "'>" + item.value + "</option>")           
            })
            $.CurrentNavtab.find('#j_stamp_documentType').selectpicker('refresh');
	    }
	})	
	
	//获取一级签核人员
	BJUI.ajax('doajax', {
	    url: 'getFirstApproveOfStamp4Select.action',
	    loadingmask: false,
	    okCallback: function(json, options) {
            $.each(json, function (i, item) {
            	if(item.fistUID!='Dept. Head'){
                    $.CurrentNavtab.find('#j_stamp_projectResponsible').append("<option value='" + item.uid + "'>" + item.name + "</option>")                     		
            	}
            })
            $.CurrentNavtab.find('#j_stamp_projectResponsible').selectpicker('refresh');
	    }
	})
	
	
	
	
})

function faceToData(){
	var o=$.CurrentNavtab.find("#j_stamp_form").serializeJson();
	o.formFiller='${user.name}';
	o.formFillerID='${user.uid}';
	o.departmentOfFormFiller='${user.department.name}';
	o.departmentOfFormFillerID='${user.department.did}';
	o.id=$.CurrentNavtab.find("#j_stamp_id").val();
	o.attacmentUpload=listToString($.CurrentNavtab.find('#upfile_invoice_list'));
	var tmp='';
	$.each(o.stampType,function(i,item){
		tmp+=item+'|';	
	})
	o.stampType=tmp;
	return o;
}

function dataToFace(){
	
	
	
}

function saveStamp(){

	
	var o=faceToData();	
	
	var err=checkSave(o);
	if(err!=''){
		BJUI.alertmsg('warn', err); 
		return false;
	}
	
	if(o.formFillerID==null||o.formFillerID==''||o.formFillerID==undefined){
		BJUI.alertmsg('error', '数据异常，用户为空，请刷新页面重新填写！'); 
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
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});		
	
	
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


function checkSave(o){
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
	if(type!='1'){
		$.CurrentNavtab.find('#j_stamp_projectResponsible').attr('disabled','disabled')		
	}else{
		$.CurrentNavtab.find('#j_stamp_projectResponsible').removeAttr('disabled','disabled')		
	}
	
}

</script>
<div class="bjui-pageContent">
    <div class="bs-example" style="width:1000px">
        <form id="j_stamp_form" data-toggle="ajaxform">
			<input type="hidden" name="id" id="j_stamp_id" value="${param.id}">
			<input type="hidden" name="state" id="j_stamp_state" value="">
            <div class="bjui-row-0" align="center">
            <h2 class="row-label">Stamp Using Application System 借/用 章 申 请 系 统</h2><br> 
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
						<input type="text" name="formFiller" size="19" value="${user.uid}-${user.name}" readonly=""  id="j_stamp_contacturalstampDate"  >
					</td>
					<td>
						Department of Form Filler:<br>填单人部门:
					</td>
					<td>
						<input type="text" name="departmentOfFormFiller" value="${user.department.name}-${user.department.did}" readonly="" id="j_stamp_code" size="19">
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
						Contact Number<label style="color:red;font-size:12px"><b>*</b></label>:<br>联系方式 <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td>
						<input type="text" name="contactNumber" id="j_stamp_contactNumber" value="" size="19" data-rule="required;phone">
					</td>
				</tr>
				<tr>
					<td>
						Stamp Type<label style="color:red;font-size:12px"><b>*</b></label>:<br>
						印章种类 <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td>
						<input type="checkbox" name="stampType" data-toggle="icheck" id="j_stamp_companyChop" value="CompanyChop" data-label="Company Chop 公章">
					</td>
					<td>
						<input type="checkbox" name="stampType" data-toggle="icheck" id="j_stamp_legalDeputyChop" value="LegalDeputyChop" data-label="Legal Deputy Chop 法人章">
					</td>	
					<td>
						<input type="checkbox" name="stampType" data-toggle="icheck" id="j_stamp_financialChop" value="FinancialChop" data-label="Financial Chop 财务章">
					</td>				
				</tr>
				<tr>
					<td>
						Document Type <label style="color:red;font-size:12px"><b>*</b></label>:<br>文件类型  <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td>
						<select name="documentType" data-toggle="selectpicker" id="j_stamp_documentType" data-rule="required" data-width="190px" onchange="setProjectResponsible()">
                        	<option value="" selected></option>
                        	<option value="1" >Invoice Contract 发票合同</option>
                    	</select>
					</td>
					<td>
						Project Responsible:<br>审批人选择 :
					</td>
					<td>
						<select name="projectResponsible" data-toggle="selectpicker" id="j_stamp_projectResponsible" data-width="190px" >
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
						用印日期与借印日期必须填一个<br>
						填了借印日期，一定要填归还日期
					</td>					
				</tr>
				<tr>
					<td>
						Lend Date <label style="color:red;font-size:12px"><b>*</b></label>:<br>借印日期  <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td>
						<input type="text" size="19" name="lendDate" data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_stamp_lendDate" value=""  />
					</td>
					<td>
						Give Back Date <label style="color:red;font-size:12px"><b>*</b></label>:<br>归还日期 <label style="color:red;font-size:12px"><b>*</b></label>:
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
						Chop Object<label style="color:red;font-size:12px"><b>*:</b></label><br>受文单位 <label style="color:red;font-size:12px"><b>*</b></label>:
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
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td>
						Usage Description:<br>申请原因:
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
								<th width="100px" align="center">Delete</th>
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
	            		<button type="button" id="stamp-submit" class="btn-default" data-icon="arrow-up" >Submit</button>
            		</td>				
				</tr>			
			</table>		


        </form>
    </div>
</div>