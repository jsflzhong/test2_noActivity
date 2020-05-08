package com.jsflzhong.test2_noactivity.layout.selfDefineLayout;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.R;

/**
 * 自定义控件.
 * 在second_layout.xml中引用.
 * <p>
 * 数据流:
 *  1.自定义一个layout的xml文件.
 *  2.自定义一个类,LinearLayout或其他相关父类.
 *  3.在java类中加载layout的xml文件,并绑定相关事件和逻辑.
 *  4.在要使用自定义控件的layout的xml中,用"<"符号直接引用这个自定义控件类的全限定名, 在该类中,引用了真正的layout xml文件.例如本例中的title.xml
 */
public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadLayoutFile(context);
        loadButtons();
    }

    /**
     * 其他布局在引入本自定义组件时,这些在这里写好的事件,也会被一并引入过去, 而不用在那边再写.
     */
    public void loadButtons() {
        View titleBack = findViewById(R.id.title_back);
        View titleEdit = findViewById(R.id.title_edit);
        //回退键,就是"销毁"当前的活动.
        titleBack.setOnClickListener(v -> ((Activity) getContext()).finish());
        titleEdit.setOnClickListener(v ->
                Toast.makeText(getContext(),"you clicked Edit buttion",Toast.LENGTH_SHORT).show());
    }

    /**
     * 加载目标layout xml文件.
     * 然后在构造函数中需要对标题栏布局进行动态加载，这就要借助LayoutInflater来实现了.
     * @param context context
     */
    private void loadLayoutFile(Context context) {
        // 通过LayoutInflater的from() 方法可以构建出一个LayoutInflater 对象，
        // 然后调用inflate() 方法就可以动态加载一个布局文件，inflate() 方法接收两个参数，
        // 第一个参数是要加载的布局文件的id，这里我们传入R.layout.title，
        // 第二个参数是给加载好的布局再添加一个父布局，这里我们想要指定为TitleLayout，于是直接传入this.
        LayoutInflater.from(context).inflate(R.layout.title, this);
    }
}
