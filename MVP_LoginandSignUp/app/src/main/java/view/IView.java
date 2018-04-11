package view;

import android.content.Context;

/**
 * Created by Liang Zihong on 2018/3/2.
 */

public interface IView {
    public void successLogin();
    public void failLogin();

    public void successSignup();
    public void failSignup();

    public Context getContext();
}
