/** 数字金额大写转换(可以处理整数,小数,负数) */    
function smalltoBIG(n)     
    {    
	    n=(n+'').replace(/,/,'');
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
	if(num==null||num==undefined){
		return '';
	}
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



//2014-02-25 

/** 
   * 字符串转时间（yyyy-MM-dd HH:mm:ss） 
   * result （分钟） 
   */ 
  function stringToDate(fDate){  
    var fullDate = fDate.split("-");  
    return new Date(fullDate[0], fullDate[1]-1, fullDate[2], 0, 0, 0);  
  } 
  
  function GetDateStr(InDate,AddDayCount) {   
	   var dd = InDate;  
	   dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期  
	   var y = dd.getFullYear();   
	   var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0  
	   var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate();//获取当前几号，不足10补0  
	   return new Date(y, m-1, d, 0, 0, 0); 
	}  
	/** 
     * 格式化日期 
     * @param date 日期 
     * @param format 格式化样式,例如yyyy-MM-dd HH:mm:ss E 
     * @return 格式化后的金额 
     */
  	function formatDate (date, format) { 
      var v = ""; 
      if (typeof date == "string" || typeof date != "object") { 
        return; 
      } 
      var year  = date.getFullYear(); 
      var month  = date.getMonth()+1; 
      var day   = date.getDate(); 
      var hour  = date.getHours(); 
      var minute = date.getMinutes(); 
      var second = date.getSeconds(); 
      var weekDay = date.getDay(); 
      var ms   = date.getMilliseconds(); 
      var weekDayString = ""; 
        
      if (weekDay == 1) { 
        weekDayString = "星期一"; 
      } else if (weekDay == 2) { 
        weekDayString = "星期二"; 
      } else if (weekDay == 3) { 
        weekDayString = "星期三"; 
      } else if (weekDay == 4) { 
        weekDayString = "星期四"; 
      } else if (weekDay == 5) { 
        weekDayString = "星期五"; 
      } else if (weekDay == 6) { 
        weekDayString = "星期六"; 
      } else if (weekDay == 7) { 
        weekDayString = "星期日"; 
      } 
  
      v = format; 
      //Year 
      v = v.replace(/yyyy/g, year); 
      v = v.replace(/YYYY/g, year); 
      v = v.replace(/yy/g, (year+"").substring(2,4)); 
      v = v.replace(/YY/g, (year+"").substring(2,4)); 
  
      //Month 
      var monthStr = ("0"+month); 
      v = v.replace(/MM/g, monthStr.substring(monthStr.length-2)); 
  
      //Day 
      var dayStr = ("0"+day); 
      v = v.replace(/dd/g, dayStr.substring(dayStr.length-2)); 
  
      //hour 
      var hourStr = ("0"+hour); 
      v = v.replace(/HH/g, hourStr.substring(hourStr.length-2)); 
      v = v.replace(/hh/g, hourStr.substring(hourStr.length-2)); 
  
      //minute 
      var minuteStr = ("0"+minute); 
      v = v.replace(/mm/g, minuteStr.substring(minuteStr.length-2)); 
  
      //Millisecond 
      v = v.replace(/sss/g, ms); 
      v = v.replace(/SSS/g, ms); 
        
      //second 
      var secondStr = ("0"+second); 
      v = v.replace(/ss/g, secondStr.substring(secondStr.length-2)); 
      v = v.replace(/SS/g, secondStr.substring(secondStr.length-2)); 
        
      //weekDay 
      v = v.replace(/E/g, weekDayString); 
      return v; 
    } 
  	
  	
  	//强制2位小数
  	function toDecimal2(x) {  
  		if(x==''||x==undefined){
  			return '';
  		}
        var f = parseFloat(x);  
        if(f==0){
        	return '-';
        }
        if (isNaN(f)) {  
            return false;  
        }  
        var f = Math.round(x*100)/100;  
        var s = f.toString();  
        var rs = s.indexOf('.');  
        if (rs < 0) {  
            rs = s.length;  
            s += '.';  
        }  
        while (s.length <= rs + 2) {  
            s += '0';  
        }  
        return s;  
  	}  	
  	
  	
  	function toNumber(x){
  		if(x==''||x==' '||x==undefined||x=='-'){
  			return 0;
  		}
  		return parseFloat(x);
  	}
  	
  	
  	function leaveTime(timeStart,timeEnd){  

  	    var starttime = timeStart;

  	    var endtime = timeEnd;

  	    var datetime1 = new Date(starttime), datetime2 = new Date(endtime);

  	    var date1 = datetime1.setHours(0,0,0);    //开始日期，毫秒表示

  	    var date2 = datetime2.setHours(0,0,0);    //结束日期，毫秒表示

  	    var starttimeleft=starttime.substring(0,10);//获取时间到月份

  	    var endtimeleft=endtime.substring(0,10);

  	    var beginDate = new Date(starttimeleft.replace(/-/g, "/"));

  	    var endDate = new Date(endtimeleft.replace(/-/g, "/"));

  	    var dayNum = parseInt(Math.abs(beginDate  -  endDate)  /  1000  /  60  /  60  /24);

  	    var startWeek = beginDate.getDay(); 

  	    var starHour=Number(starttime.substring(11,13));

  	    var endHour=Number(endtime.substring(11,13)); 
  	    
  	    var starMinute=Number(starttime.substring(14,16));

	    var endMinute=Number(endtime.substring(14,16)); 
	    
	    if(starMinute>=15 && starMinute<=45){
	    	starHour=starHour+0.5;
	    }else if(starMinute<15){
	    	starHour=starHour+1;
	    }
	    
	    if(endMinute>=15 && endMinute<=45){
	    	endHour=endHour+0.5;
	    }else if(endMinute>45){
	    	endHour=endHour+1;
	    }
	    
	    var firstDayHour=0;
	    var lastDayHour=0;
	    
  	    var travelHours = 0;    //保存请假小时数

  	    var travelTimeHours = 0;

  	    var iNow = 0;

  	    var dayinweek= new Date(starttime).getDay();/////开始日期是星期几

  	    var dayendweek = new Date(endtime).getDay();//结束日期是星期几   

  	//////根据上班时间 9:00--18:00 把小时

  	 


  	    if(date1 ==date2 ){//开始结束时间均在一天
  	         if((starHour<=12 && endHour<=12)||(starHour>=13 && endHour>=13)){
  	        	travelHours=endHour-starHour;
  	         }else if(starHour>=13 ){
  	             travelHours =endHour-starHour-1
  	         }
  	    }else{

  	    	
  		    if(starHour<=13 && starHou>=12){
  		    	firstDayHour=4;
  		    }else if( starHour >13 && starHour<=17){
  		    	firstDayHour=17-starHour;
  		    }else if(starHour<12 && starHour>=8){
  		    	firstDayHour=4+12-starHour;
  		    }else if(starHour<8){
		    	firstDayHour=8;
		    }else if(starHour>17){
		    	firstDayHour=0;
		    }
  		    
  		    if(endHour<=13 && endHour>=12){
  		    	lastDayHour=4;
  		    }else if( endHour >13 && endHour<17){
  		    	lastDayHour=15-endHour;
  		    }else if(endHour<12 && endHour>=8){
  		    	lastDayHour=12-endHour;
  		    }else if(endHour<8){
  		    	lastDayHour=0;
  		    }else if(endHour>17){
  		    	lastDayHour=8;
  		    }
  		    
  	        

  	         if(0< dayinweek && dayinweek <6 && dayendweek == 0 ||  0< dayinweek && dayinweek <6 && dayendweek == 6){

  	             travelTimeHours +=(weekendBetween(dayNum) - 1)*8; 

  	         }else if(0< dayendweek && dayendweek <6 && dayinweek == 0 ||  0< dayendweek && dayendweek <6 && dayinweek == 6 ){

  	             travelTimeHours +=(weekendBetween(dayNum) - 1)*8; 

  	         }else if(dayinweek !=6 && dayinweek !=0 && dayendweek !=6 && dayendweek !=0){

  	             travelTimeHours +=(weekendBetween(dayNum) - 2)*8; 

  	         }

  	         
//
//  	        if(dayinweek !=6 && dayinweek !=0){
//
//  	            if(starHour>=9 && starHour<=12){ 
//
//  	                travelTimeHours+=8; 
//
//  	            }
//
//  	            
//
//  	            if(starHour>=13 && starHour<=18){
//
//  	                travelTimeHours+=4; 
//
//  	            }
//
//  	        }  
//
//  	        if(dayendweek !=6 && dayendweek !=0){ 
//
//  	            if(endHour>=9 && endHour<=12){
//
//  	                travelTimeHours+=4; 
//
//  	            } 
//
//  	            if(endHour>=13 && endHour<=18){
//
//  	                travelTimeHours+=8; 
//
//  	            }
//
//  	        } 

  	    }

  	    return travelTimeHours+firstDayHour+lastDayHour;
//  	    var timeDays = travelHours / 8; 
//
//  	    var travelTime = travelTimeHours /8;
//
//  	    var travelTimeDay = timeDays + travelTime;


  	    // 去除周六日  ；

  	    function weekendBetween(dayNum){

  	        var iNow = 0; 

  	        dayNum =dayNum +1;

  	        for (var i=0; i<dayNum; i++) {

  	            var firstDay = startWeek % 7; 

  	            if(firstDay==0) firstDay=7;

  	            if (firstDay < 6) {

  	                iNow++;

  	            }

  	            if (firstDay == 7) {

  	                firstDay = 1;

  	            }

  	            startWeek ++;

  	        }

  	        return iNow;

  	    } 

  	} 	
  	
  	
  	
  	