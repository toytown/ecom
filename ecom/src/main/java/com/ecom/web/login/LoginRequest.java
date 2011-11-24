package com.ecom.web.login;

import java.io.Serializable;

public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
    private boolean isNewRegistraion = false;
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public boolean isNewRegistraion() {
        return isNewRegistraion;
    }

    public void setNewRegistraion(boolean isNewRegistraion) {
        this.isNewRegistraion = isNewRegistraion;
    }

}
