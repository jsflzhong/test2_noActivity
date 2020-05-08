package com.jsflzhong.test2_noactivity.layout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jsflzhong.test2_noactivity.R;
import com.jsflzhong.test2_noactivity.entity.Fruit;

import java.util.List;

/**
 * 自定义Adapter
 */
public class FruitAdapter extends ArrayAdapter<Fruit> {

    //子项布局的id,会由调用方传入.
    private int resourceId;

    /**
     * 重写了父类的一组构造函数，用于将上下文、ListView子项布局的id和数据都传递进来.
     *
     * @param context context
     * @param textViewResourceId 子项布局的id,会由调用方传入.
     * @param objects 传入的数据,用于传给父布局.
     */
    public FruitAdapter(Context context, int textViewResourceId,
                        List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    /**
     * 这个方法在每个子项被滚动到"屏幕内"的时候会被调用.
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //获取当前项的Fruit实例
        Fruit fruit = getItem(position);
        //为这个子项活动绑定我们传入的(子)布局(fruit_item.xml)
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView fruitImage = view.findViewById(R.id.fruit_image);
        TextView fruitName = view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getImageId());
        fruitName.setText(fruit.getName());
        return view;
    }
}
