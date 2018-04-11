package model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import presenter.ILoginPresenter;
import presenter.ISignupPresenter;

/**
 * Created by Liang Zihong on 2018/3/2.
 */

public class User_model implements IUser_model {



    private SharedPreferences pref;
    private Editor editor;

    public User_model(Context context){
        pref=context.getSharedPreferences("StoreUser",Context.MODE_PRIVATE);
        editor=pref.edit();
    }


    /**
     * 注册成功返回true，不成功返回false
     * @param name
     * @param password
     * @return 注册是否成功
     */
    private boolean startSignup(String name, String password) {
        if(name==null || password==null){
            return false;
        }
        else if(pref.getString(name,null)!=null){
            return false;
        }
        else{
            editor.putString(name,password);
            editor.commit();
            return true;
        }
    }

    /**
     * 登录成功返回true，不成功 返回false
     * @param name
     * @param password
     * @return
     */
    private boolean startLogin(String name, String password) {
        if(pref.getString(name,"").equals(password)){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public boolean isSignupSuccess(String name,String password) {
        return startSignup(name,password);
    }


    @Override
    public boolean isLoginSuccess(String name,String password) {
        return startLogin(name,password);
    }
}
