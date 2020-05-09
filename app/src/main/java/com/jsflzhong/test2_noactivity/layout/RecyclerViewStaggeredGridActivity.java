package com.jsflzhong.test2_noactivity.layout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;
import com.jsflzhong.test2_noactivity.entity.Fruit;
import com.jsflzhong.test2_noactivity.layout.adapter.FruitAdapter3;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerViewStaggeredGridActivity extends BasicActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.recycler_view_layout);
        Toast.makeText(this, "RecyclerViewStaggeredGridActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadView() {
        //初始化要给子布局的数据
        ArrayList<Fruit> fruitList = initFruits();
        //拿到主布局中的控件: RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //瀑布流布局管理器
        //第一个参数用于指定布局的列数,传入3表示会把布局分为3列；第二个参数用于指定布局的排列方向
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //传入已经绑定了布局的Adapter,通过构造函数传过去数据.
        recyclerView.setAdapter(new FruitAdapter3(fruitList));
    }

    private ArrayList<Fruit> initFruits() {
        ArrayList<Fruit> fruitList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(
                    getRandomLengthName("Apple"), R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit(
                    getRandomLengthName("Banana"), R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit(
                    getRandomLengthName("Orange"), R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(
                    getRandomLengthName("Watermelon"), R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(
                    getRandomLengthName("Pear"), R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit(
                    getRandomLengthName("Grape"), R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(
                    getRandomLengthName("Pineapple"), R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(
                    getRandomLengthName("Strawberry"), R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(
                    getRandomLengthName("Cherry"), R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit(
                    getRandomLengthName("Mango"), R.drawable.mango_pic);
            fruitList.add(mango);
        }
        return fruitList;
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
