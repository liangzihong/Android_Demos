package Views;

import android.content.Context;

import Adapters.MeizhiAdapter;

/**
 * Created by Liang Zihong on 2018/4/4.
 */

public interface IUpdateGanMeizhiView {

    public MeizhiAdapter getMeizhiAdapter();
    public void startUpdateMeizhi();
    public void addMoreMeizhi();

}
