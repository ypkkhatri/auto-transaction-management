package com.jsflogin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yog.transaction.ejbs.domains.User;

@WebFilter(urlPatterns = "/*", servletNames = "{Faces Servlet}")
public class AuthFilter extends AbstractFilter implements Filter {

    public void init(FilterConfig fc) throws ServletException {

    }

    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc)
	    throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest) sr;
	// HttpServletResponse response = (HttpServletResponse) sr1;

	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("user");
	if (session.isNew() || user == null) {
	    if (request.getRequestURI().indexOf("register.xhtml") >= 0
		    || request.getRequestURI().indexOf("javax.faces.resource") >= 0)
		fc.doFilter(sr, sr1);
	    else
		doLogin(sr, sr1, request);
	} else {
	    fc.doFilter(sr, sr1);
	}
    }

    public void destroy() {
	// throw new UnsupportedOperationException("Not supported yet.");
    }

}
