package com.sign.action;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kime.base.ActionBase;
import com.kime.infoenum.Message;
import com.sign.biz.PaymentVisitBusinessTripBIZ;
import com.sign.model.Beneficiary;
import com.sign.model.paymentVisit.PaymentVisitBusinessTrip;


@Controller
@Scope("prototype")
@ParentPackage("Struts 2")
public class paymentVisitBusinessTripAction extends ActionBase{

	@Autowired
	PaymentVisitBusinessTripBIZ paymentVisitBusinessTripBIZ;
	
	@Action(value="savepaymentVisitView",results={@org.apache.struts2.convention.annotation.Result(type="stream",
			params={
					"inputName", "reslutJson"
			})})
	public String savepaymentVisitView() throws UnsupportedEncodingException {
		
		List<PaymentVisitBusinessTrip> list= new Gson().fromJson(json, new TypeToken<ArrayList<PaymentVisitBusinessTrip>>() {}.getType());
		Map<String, Boolean> params=new HashMap<>();
		try {
			
			
//			for (PaymentVisitBusinessTrip paymentVisitBusinessTrip : list) {
//				paymentVisitBusinessTripBIZ.save(paymentVisitBusinessTrip);
//			}
			paymentVisitBusinessTripBIZ.save(list);
			result.setMessage(Message.SUCCESS);
			result.setStatusCode("200");
			params.put("save_flag", true);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setStatusCode("300");
			params.put("save_flag", false);
		}
		result.setParams(params);
		reslutJson=new ByteArrayInputStream(new Gson().toJson(result).getBytes("UTF-8")); 	
		return SUCCESS;
	}	
}
