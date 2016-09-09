package com.yog.transaction.ejbs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@InterceptorBinding
public @interface JwdTransaction {
	public boolean isNew() default false;
}
