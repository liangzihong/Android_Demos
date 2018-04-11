package MODELS;

import org.litepal.crud.DataSupport;

/**
 * Created by Liang Zihong on 2018/3/9.
 */

public class User extends DataSupport{
    private int age;
    private String name;
    private String sex;

    public User(String name, int age, String sex){
        this.age=age;
        this.name=name;
        this.sex=sex;
    }

    public User(){}
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString(){
        return name+" "+age+" "+sex;
    }
}
