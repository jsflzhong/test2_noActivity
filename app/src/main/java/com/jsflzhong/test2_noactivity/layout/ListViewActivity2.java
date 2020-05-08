package com.jsflzhong.test2_noactivity.layout;

import android.widget.ListView;
import android.widget.Toast;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;
import com.jsflzhong.test2_noactivity.entity.Fruit;
import com.jsflzhong.test2_noactivity.layout.adapter.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 第二个listView.
 *
 * 1.把该活动的布局,作为主布局,
 * 2.内部初始化自定义的Adapter,同时传过去指定的子布局id,让那边完成Adapter与子布局的绑定关系.
 * 3.把Adapter传入父布局中,完成.
 *
 * 自定义子项布局(fruit_item.xml)和数据, 传入父布局(listview2_layout).
 */
public class ListViewActivity2 extends BasicActivity {

    @Override
    public void loadView() {
        List<Fruit> fruitList = initFruits();
        //创建要传给父布局的数据.
        //传入上下文, 子项布局(fruit_item.xml),和数据集.
        //子项布局这次与ListViewActivity中的不同,这次使用自定义的布局fruit_item.xml, 通过构造函数传给自定义的Adapter.
        FruitAdapter fruitAdapter = new FruitAdapter(this, R.layout.fruit_item, fruitList);
        //拿父布局中的listView.
        ListView listView = findViewById(R.id.list_view2);
        //给父布局放入Adapter数据.
        listView.setAdapter(fruitAdapter);
        //给listView注册点击事件.
        listView.setOnItemClickListener((parent,view,position,id) -> {
            //可以通过position 参数判断出用户点击的是哪一个子项，然后获取到相应的水果.
            Fruit fruit = fruitList.get(position);
            Toast.makeText(this, fruit.getName(),Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.listview2_layout);
    }

    private List<Fruit> initFruits() {
        List<Fruit> fruitList = new ArrayList<>();
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
