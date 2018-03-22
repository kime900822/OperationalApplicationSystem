/** 数字金额大写转换(可以处理整数,小数,负数) */    
function smalltoBIG(n)     
    {    
        var fraction = ['角', '分'];    
        var digit = ['零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'];    
        var unit = [ ['元', '万', '亿'], ['', '拾', '佰', '仟']  ];    
        var head = n < 0? '欠': '';  
        if((n+'').indexOf('.')!=-1){
        	n=n+'00000001'
        }
        n =  parseFloat(Math.abs(n));    
      
        var s = '';    
      
        for (var i = 0; i < fraction.length; i++)     
        {    
            s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');    
        }    
        s = s || '整';    
        n = Math.floor(n);    
      
        for (var i = 0; i < unit[0].length && n > 0; i++)     
        {    
            var p = '';    
            for (var j = 0; j < unit[1].length && n > 0; j++)     
            {    
                p = digit[n % 10] + unit[1][j] + p;    
                n = Math.floor(n / 10);    
            }    
            s = p.replace(/(零.)*零$/, '').replace(/^$/, '零')  + unit[0][i] + s;    
        }    
        return head + s.replace(/(零.)*零元/, '元').replace(/(零.)+/g, '零').replace(/^整$/, '零元整');    
    }

//文件上传
function ajaxFileUpload(id,tid) {
	if($.CurrentNavtab.find("#"+id).val()==""||$.CurrentNavtab.find("#"+id).val()==null||$.CurrentNavtab.find("#"+id).val()==undefined){
		BJUI.alertmsg('error', "Choice file"); 
		return false;	
	}else{
	    $.ajaxFileUpload
	    (
	        {
	            url: 'savefile.action', //用于文件上传的服务器端请求地址
	            secureuri: false, //是否需要安全协议，一般设置为false
	            fileElementId: id, //文件上传域的ID
	            dataType: 'json', //返回值类型 一般设置为json
	            async :false,
	            success: function (data, status)  //服务器成功响应处理函数
	            {
	                if(data.statusCode=='200'){	                      	
	                	$.each(data.params.url,function(i,n){               		
	                		var filename=n.split('/')[1];
	                		$.CurrentNavtab.find("#"+tid).append(fileToTr(filename,n,true))
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


function getFile(path){
	BJUI.ajax('ajaxdownload', {
	    url:'getFile.action',
	    data:{dfile:path}
	})
	
}


/** 
 * 将数值四舍五入(保留2位小数)后格式化成金额形式 
 * 
 * @param num 数值(Number或者String) 
 * @return 金额格式的字符串,如'1,234,567.45' 
 * @type String 
 */  
function formatCurrency(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
    num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*100+0.50000000001);  
    cents = num%100;  
    num = Math.floor(num/100).toString();  
    if(cents<10)  
    cents = "0" + cents;  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
}  
   
/** 
 * 将数值四舍五入(保留1位小数)后格式化成金额形式 
 * 
 * @param num 数值(Number或者String) 
 * @return 金额格式的字符串,如'1,234,567.4' 
 * @type String 
 */  
function formatCurrencyTenThou(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
    num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*10+0.50000000001);  
    cents = num%10;  
    num = Math.floor(num/10).toString();  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
}  
  
// 添加金额格式化  
jQuery.extend({  
    formatFloat:function(src, pos){  
        var num = parseFloat(src).toFixed(pos);  
        num = num.toString().replace(/\$|\,/g,'');  
        if(isNaN(num)) num = "0";  
        sign = (num == (num = Math.abs(num)));  
        num = Math.floor(num*100+0.50000000001);  
        cents = num%100;  
        num = Math.floor(num/100).toString();  
        if(cents<10) cents = "0" + cents;  
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
        num = num.substring(0,num.length-(4*i+3))+','+num.substring(num.length-(4*i+3));  
        return (((sign)?'':'-') + num + '.' + cents);  
    }  
});  