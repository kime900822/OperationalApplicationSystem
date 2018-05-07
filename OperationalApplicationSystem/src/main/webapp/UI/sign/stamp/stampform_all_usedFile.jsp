<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script type="text/javascript">

$(function(){
	

	
	
	
	BJUI.ajax('doajax', {
	    url: 'getStampByID.action',
	    loadingmask: false,
	    data:{id:'${param.id}'},
	    okCallback: function(json, options) {	
    		$.CurrentDialog.find('#upfile_usedFile_list').children().children().eq(0).siblings().remove();

    		if(json.usedFile!=undefined&&json.usedFile!=""){
        		var file=json.usedFile.split('|');
        		
        		$.each(file,function(i,n){
        			var filename=n.split('/')[1];
        			if(filename!=undefined&&filename!=''){         				
        				$.CurrentDialog.find('#upfile_usedFile_list').append(fileToTr(filename,n));	   
        			}
        		})
        	}
	    }
	})	
	

})

function deleteFile(path,o){
	BJUI.ajax('doajax', {
	    url:'deleteFileOfStamp4UsedFile.action',
	    data:{dfile:path,id:'${param.id}'},
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	$.CurrentDialog.find(o).parent().parent().remove();	
        		
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	})
	
}		

function fileToTr(name,path){
	if('${user.name}'=='admin'){
		return "<tr><td align='center'><a onclick=\"getFile('"+path.replace('\\','\\\\')+"')\" url='"+path.replace('\\','\\\\')+ "' >"+name+"</></td><td align='center'><a onclick=\"deleteFile('"+path.replace('\\','\\\\')+"',this)\" >Delete</a></td></tr>"
	}else{
		return "<tr><td align='center'><a onclick=\"getFile('"+path.replace('\\','\\\\')+"')\" url='"+path.replace('\\','\\\\')+ "' >"+name+"</></td><td align='center'></td></tr>"
	}
}

function usedFileUpload(id,tid) {
	if($.CurrentDialog.find("#"+id).val()==""||$.CurrentDialog.find("#"+id).val()==null||$.CurrentDialog.find("#"+id).val()==undefined){
		BJUI.alertmsg('error', "Choice file"); 
		return false;	
	}else{
	    $.ajaxFileUpload
	    (
	        {
	            url: 'savefile4UseFile.action', //用于文件上传的服务器端请求地址
	            secureuri: false, //是否需要安全协议，一般设置为false
	            fileElementId: id, //文件上传域的ID
	            dataType: 'json', //返回值类型 一般设置为json
	            data:{id:'${param.id}'},
	            async :false,
	            success: function (data, status)  //服务器成功响应处理函数
	            {
	                if(data.statusCode=='200'){	                      	
	                	$.each(data.params.url,function(i,n){               		
	                		var filename=n.split('/')[1];
	                		$.CurrentDialog.find("#"+tid).append(fileToTr(filename,n))
	                	})                	
	                	BJUI.alertmsg('info', data.message); 
	                }else{              	
	                	BJUI.alertmsg('error', data.message); 
	                }
	            },
	            error: function (data, status, e)//服务器响应失败处理函数
	            {
	            	BJUI.alertmsg('error', e); 
	            }
	        }
	    )
	    return false;		
	}

}
		

</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
            <table style="font-size: 10px">
            	<tr>
					<td width="120px">
						Used File Upload:<br>附件上传:
					</td>
					<td >
						<input type="file" id="file" name="file" width="120px" onchange="usedFileUpload('file','upfile_usedFile_list');"/>
					</td>
					<td colspan="2" >
						<table class="table" id="upfile_usedFile_list" >	
							<tr>
								<th width="300px" align="center">File Name</th>
								<th width="100px" align="center">Delete</th>
							</tr>									
						</table>
					</td>
				</tr>
            
            </table>
    </div>
</div>