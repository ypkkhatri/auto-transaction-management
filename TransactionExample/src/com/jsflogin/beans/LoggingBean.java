package com.jsflogin.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.interceptor.Interceptors;

import com.yog.transaction.ejbs.annotations.JwdTransaction;
import com.yog.transaction.ejbs.interceptors.TransactionIntercepter;

@ManagedBean
@SessionScoped
@Interceptors({TransactionIntercepter.class})
public class LoggingBean {
	
	@JwdTransaction
	public void save() {
		
	}
	
	
}
