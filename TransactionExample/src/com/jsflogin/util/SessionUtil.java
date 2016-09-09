package com.jsflogin.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yog.transaction.ejbs.domains.User;

public class SessionUtil {
    public static HttpSession getSession() {
	return (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
	return (HttpServletRequest) FacesContext.getCurrentInstance()
		.getExternalContext().getRequest();
    }

    public static void setAttribute(String key, Object value) {
	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
		.getExternalContext().getRequest();
	request.getSession().setAttribute(key, value);
    }

    public static String getUserName() {
	return ((User) getSession().getAttribute("user")).getUsername();
    }

    public static User getUser() {
	return ((User) getSession().getAttribute("user"));
    }

    public static void clearSession() {
	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) context
		.getExternalContext().getRequest();
	request.getSession().invalidate();
    }
}
