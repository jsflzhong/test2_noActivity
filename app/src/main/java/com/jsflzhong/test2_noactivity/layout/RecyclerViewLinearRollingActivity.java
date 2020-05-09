package com.jsflzhong.test2_noactivity.layout;


import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;
import com.jsflzhong.test2_noactivity.entity.Fruit;
import com.jsflzhong.test2_noactivity.layout.adapter.FruitAdapter2;

import java.util.ArrayList;

/**
 * 父布局.
 * 子布局: fruit_item_vertical.xml
 * Adapter:FruitAdapter2.java
 */
public class RecyclerViewLinearRollingActivity extends BasicActivity {

    private static final String TAG = "RecyclerViewActivity";

    @Override
    public void setContentView() {
        setContentView(R.layout.recycler_view_layout);
        Toast.makeText(this, "RecyclerViewLinearRollingActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadView() {
        //初始化要给子布局的数据
        ArrayList<Fruit> fruitList = initFruits();
        //拿到主布局中的控件: RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //LayoutManager用于指定RecyclerView的布局方式，这里使用的LinearLayoutManager是线性布局的意思，可以实现和ListView类似的效果.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置布局的排列方向，默认是纵向排列的，我们传入LinearLayoutManager.
        //## HORIZONTAL 表示让布局横行排列，这样RecyclerView就可以横向滚动了.##
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //传入已经绑定了布局的Adapter,通过构造函数传过去数据.
        recyclerView.setAdapter(new FruitAdapter2(fruitList));
    }

    private ArrayList<Fruit> initFruits() {
        ArrayList<Fruit> fruitList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            fruitList.add(mango);
        }
        return fruitList;
    }
}
