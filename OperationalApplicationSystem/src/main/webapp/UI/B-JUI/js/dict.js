//出差申请获取出差目的
function getVisitViewVisitPurpose(x){

	if(x=='1'){
		return 'Supplier Visit (供应商拜访)';
	}else if(x=='2'){
		return 'Customer Visit (客户拜访)';
	}else if(x=='3'){
		return 'New Customer Development (新客户开发)';
	}else if(x=='4'){
		return 'New Facility purchase (新设备采购)';
	}else if(x=='5'){
		return 'Training (培训)';
	}else if(x=='6'){
		return 'QRF Related (QRF 相关)';
	}else if(x=='7'){
		return 'New Factory Investment (新厂房投资)';
	}else if(x=='8'){
		return 'Exhibition (展会)';
	}else{
		return '';
	}
	
}


function getPaymentVisitStatus(){
	BJUI.ajax('doajax', {
	    url: 'getPaymentVisitStatus.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_visit_id").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	return json.params
            }else{
            	return [];
            }
	    }
	});	
	
	
	
}