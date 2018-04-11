package model;

/**
 * Created by Liang Zihong on 2018/3/2.
 */

public interface IUser_model {

    public boolean isSignupSuccess(String name,String password);

    public boolean isLoginSuccess(String name,String password);

}
