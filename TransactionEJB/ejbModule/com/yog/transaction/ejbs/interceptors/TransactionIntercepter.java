package com.yog.transaction.ejbs.interceptors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

import com.yog.transaction.ejbs.annotations.JwdTransaction;

@Interceptor
public class TransactionIntercepter {

    @Resource
    private UserTransaction userTransaction;

    @AroundInvoke
    public Object invoke(InvocationContext ctx) {
	JwdTransaction jwdTransaction = getJwdTransactionAnnotation(ctx
		.getMethod());

	try {
	    if (jwdTransaction != null && jwdTransaction.isNew()) {
		userTransaction.begin();
	    }

	    return ctx.proceed();
	} catch (Exception ex) {
	    ex.printStackTrace();
	    if (jwdTransaction != null && jwdTransaction.isNew()) {
		try {
		    userTransaction.rollback();
		} catch (Exception ex2) {
		    ex2.printStackTrace();
		}
	    }
	    return null;
	} finally {
	    if (jwdTransaction != null && jwdTransaction.isNew()) {
		try {
		    userTransaction.commit();
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    }
	}
    }

    private JwdTransaction getJwdTransactionAnnotation(Method m) {
	for (Annotation a : m.getAnnotations()) {
	    if (a instanceof JwdTransaction) {
		return (JwdTransaction) a;
	    }
	}

	return null;
    }
}
