package com.example.caneraydin.androidwithlogin.domains;

/**
 * Created by Chico on 6/2/2016.
 */
public class Login {

    private String loginName;
    private String loginPassword;
    private int loginRemember;//1 remember 0 not

    public Login() {
    }

    public Login(String loginName, String loginPassword, int loginRemember) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.loginRemember = loginRemember;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public int getLoginRemember() {
        return loginRemember;
    }

    public void setLoginRemember(int loginRemember) {
        this.loginRemember = loginRemember;
    }

    @Override
    public String toString() {
        return "Login{" +
                "loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", loginRemember=" + loginRemember +
                '}';
    }
}

