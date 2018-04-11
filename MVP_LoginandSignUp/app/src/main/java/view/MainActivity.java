package view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liangzihong.mvp_loginandsignup.R;

import presenter.ILoginPresenter;
import presenter.ISignupPresenter;
import presenter.LoginPresenter;
import presenter.SignupPresenter;

public class MainActivity extends AppCompatActivity implements IView,View.OnClickListener{

    private ILoginPresenter loginPresenter;
    private ISignupPresenter signupPresenter;

    private EditText inputname;
    private EditText inputpassword;
    private Button login_button;
    private Button signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        init();
    }

    private void init(){
        loginPresenter=new LoginPresenter(this);
        signupPresenter=new SignupPresenter(this);
        inputname=(EditText)findViewById(R.id.input_name);
        inputpassword=(EditText)findViewById(R.id.input_password);
        login_button=(Button)findViewById(R.id.login_button);
        signup_button=(Button)findViewById(R.id.signup_button);

        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name=inputname.getText()+"";
        String password=inputpassword.getText()+"";

        switch(v.getId()){
            case R.id.login_button:
                loginPresenter.startLogin(name,password);
                break;
            case R.id.signup_button:
                signupPresenter.startSignup(name,password);
                break;
        }
    }

    //下面的方法 就是 实现 IView的方法，是给 IPresenter的实例运用的
    //所以上面的方法是真实 的 Activity的代码，所以Activity是简洁的
    //而如果要再加功能， Activity的代码其实也增加的不多，只是增加 Presenter的代码而已。


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void successLogin() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void failLogin() {
        Toast.makeText(this, "登录失败，请核对账号密码", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void successSignup() {
        Toast.makeText(this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
        inputname.setText(null);
        inputpassword.setText(null);
    }

    @Override
    public void failSignup(){
        Toast.makeText(this, "注册失败，账号已注册或账号密码为空", Toast.LENGTH_SHORT).show();
    }


}
