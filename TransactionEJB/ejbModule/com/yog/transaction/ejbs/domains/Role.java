package com.yog.transaction.ejbs.domains;

public enum Role {
    ADMIN, USER;

    public static Role getRole(String role) {
	if (role.equals("ADMIN")) {
	    return ADMIN;
	} else
	    return USER;
    }
}
