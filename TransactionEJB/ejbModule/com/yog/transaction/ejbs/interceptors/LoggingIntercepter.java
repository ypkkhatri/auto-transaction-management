package com.yog.transaction.ejbs.interceptors;

import java.util.Arrays;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.yog.transaction.ejbs.beans.LogDao;
import com.yog.transaction.ejbs.beans.ScheduleManager;
import com.yog.transaction.ejbs.domains.Log;
import com.yog.transaction.utils.DateUtil;

@Interceptor
public class LoggingIntercepter {

    @Inject
    private ScheduleManager sm;

    @EJB
    private LogDao logDao;

    @AroundInvoke
    @Asynchronous
    public Object invoke(final InvocationContext ctx) throws Exception {
	sm.schedule(new Runnable() {
	    @Override
	    public void run() {
		try {
		    String methodName = ctx.getMethod().getName();
		    String className = ctx.getMethod().getDeclaringClass()
			    .getName();
		    Object parameters[] = ctx.getParameters();

		    Log log = new Log();
		    log.setMethod(methodName);
		    log.setMethodClass(className);
		    log.setDateTime(DateUtil.getCurrentDateTime());
		    log.setDateTime(DateUtil.getCurrentDateTime());
		    log.setParameters(Arrays.toString(parameters)
			    .replace("[", "").replace("]", ""));
		    logDao.save(log);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}, 0);

	return ctx.proceed();
    }
}
