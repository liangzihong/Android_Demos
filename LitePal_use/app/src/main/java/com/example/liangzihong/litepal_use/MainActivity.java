package com.example.liangzihong.litepal_use;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

import MODELS.User;

public class MainActivity extends AppCompatActivity{
    private Button createTable;
    private Button addUser;
    private Button deleteUser;
    private Button updateUser;
    private Button queryUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        init();
    }

    private void init(){
        createTable=(Button)findViewById(R.id.createTable);
        addUser=(Button)findViewById(R.id.addUser);
        deleteUser=(Button)findViewById(R.id.deleteUser);
        updateUser=(Button)findViewById(R.id.updateUser);
        queryUser=(Button)findViewById(R.id.queryUser);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createTable:

                //创建数据库，直接  LitePal.getDatabase()
                LitePal.getDatabase();
                break;
            case R.id.addUser:

                //增加数据库的数据
                //先新建一个 model
                //然后  model.save()即可保存
                User b=new User("Ali",35,"female");
                b.save();
                User c=new User("wongtsuiyu",35,"female");
                c.save();
                User d=new User("Maming",40,"male");
                d.save();
                User e=new User("Ben",35,"male");
                e.save();
                break;
            case R.id.updateUser:

                /**
                 * 更新功能，把一个你想更新的属性放在一个新的model上
                 * 然后 model.updateAll("name=?","xxx");
                 * 这几个参数是可变参数，可以有n个条件，这样就充当了  where 的角色。
                 */
                User f=new User();
                f.setName("nobody");
                f.updateAll("name=?","Maming");
                f.save();
                break;

            case R.id.deleteUser:

                /**
                 * 删除功能，DataSupport.deleteAll(Model.class,"name=?","xx")
                 * 第一个参数其实是相当于表名，因为 Model.class对应的就是表名
                 * 然后后面的可变长参数就是 相当于 where
                 */
                DataSupport.deleteAll(User.class,"name=?","nobody");
                break;
            case R.id.queryUser:

                /**
                 * 查询功能，
                 * DataSupport.findAll(Model.class)  搜出全部行，返回 List<\Model\>
                 * DataSupport.select(你想要的列名).find(Model.class)
                 * DataSupport.where(条件,"xx").find(Model.class)
                 * DataSupport.order("xx").find(Model.class)
                 *
                 * 然后可以混合在一起
                 * arr=DataSupport.where("age > ?","30")
                 * .order("age")
                 * .find(User.class);
                 * 反正最后一定要 Model.class表明是哪一个表
                 */

                List<User> arr= DataSupport.findAll(User.class);
                arr=DataSupport.where("age > ?","30")
                        .order("age")
                        .find(User.class);

                for (User user:arr){
                    Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }


}
