package com.kime.intercept;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.kime.biz.UserBIZ;
import com.kime.infoenum.Message;
import com.kime.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor  {

	@Autowired
	UserBIZ userBIZ;
	
	
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext(); 
		Map session = ctx.getSession();  
		User user=(User)session.get("user");
		String actionname=invocation.getInvocationContext().getName();
		Map<String, String[]> param=invocation.getInvocationContext().getParameters().toMap();
		
		if (user==null) {
			try {
				if (actionname.equals("login")) {
					String uid=param.get("uid")[0];
					String password=param.get("password")[0];
					if (uid.equals("")||password.equals("")) {
						return Action.LOGIN;
					}
					if (!uid.equals("")&&!password.equals("")) {
						return invocation.invoke(); 
					}
				}else{
					return Action.ERROR; 
				}

				
			} catch (Exception e) {
				return Action.LOGIN;
			}
		}

		

		
		if (user!=null||actionname.equals("forgetPassword")) {
			return invocation.invoke(); 
		}else 
		{
			//session.put("login_message",Message.LOGIN_MESSAGE_UNLOGIN);
			return Action.ERROR; 
		}
	}

	
}
