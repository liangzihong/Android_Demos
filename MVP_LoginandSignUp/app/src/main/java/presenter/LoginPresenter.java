package presenter;

import model.IUser_model;
import model.User_model;
import view.IView;

/**
 * Created by Liang Zihong on 2018/3/2.
 */

public class LoginPresenter implements ILoginPresenter {
    private IView view;
    private IUser_model user;


    //view的操作都交给了 Presenter来调用 。

    public LoginPresenter(IView view) {
        this.view=view;
        user=new User_model(view.getContext());
    }

    @Override
    public void startLogin(String name, String password) {
        if(user.isLoginSuccess(name,password))
            successLogin();
        else
            failLogin();
    }

    private void successLogin() {
        view.successLogin();
    }

    private void failLogin() {
        view.failLogin();
    }
}
