package com.jsflogin.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.jsflogin.util.SessionUtil;
import com.yog.transaction.ejbs.daos.UserDao;
import com.yog.transaction.ejbs.domains.User;

@RequestScoped
@ManagedBean
public class LoginController extends AbstractController {
    
    @EJB
    private UserDao userDao;

    private String usernameOrEmail;
    private String password;
    private String email;
    private String role;

    public String getUsernameOrEmail() {
	return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
	this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    private User isValidLogin(String loginOrEmail, String password) {
	User user;

	if (isEmail(loginOrEmail)) {
	    user = userDao.findByEmail(loginOrEmail);
	} else {
	    user = userDao.findByUsername(loginOrEmail);
	}

	if (user == null || !password.equals(user.getPassword())) {
	    return null;
	}
	return user;
    }

    private boolean isEmail(String value) {
	return value.contains("@");
    }

    public String doLogin() {
	User user = isValidLogin(usernameOrEmail, password);

	if (user != null) {
	    SessionUtil.setAttribute("user", user);
	    return "home.xhtml?faces-redirect=true";
	}
	displayErrorMessage("Invalid username or password");
	return null;
    }

    public String register() {
	User user = new User();
	user.setUsername(usernameOrEmail);
	user.setEmail(email);
	user.setPassword(password);

	try {
	    userDao.save(user);
	    displayInfoMessage("User registered successfully");
	} catch (Exception ex) {
	    displayErrorMessage(ex.getMessage());
	}
	return null;
    }

    public String doLogout() {
	SessionUtil.clearSession();
	return "index.xhtml";
    }

    public String getUserName() {
	return SessionUtil.getUserName();
    }

}
