package presenter;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import model.IUser_model;
import model.User_model;
import view.IView;

/**
 * Created by Liang Zihong on 2018/3/2.
 */

public class SignupPresenter implements ISignupPresenter {
    private IView view;
    private IUser_model user;

    public SignupPresenter(IView view){
        this.view=view;
        user=new User_model(view.getContext());
    }


    @Override
    public void startSignup(String name, String password) {
        if(user.isSignupSuccess(name,password)) {
            successSignup();
        }
        else{
            failSignup();
        }
    }

    private void successSignup() {
        view.successSignup();
    }

    private void failSignup() {
        view.failSignup();
    }
}
