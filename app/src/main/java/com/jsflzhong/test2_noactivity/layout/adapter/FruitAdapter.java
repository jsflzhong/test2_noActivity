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
     * 性能问题1:
     *      getView() 方法中，每次都将布局重新加载了一遍，当ListView快速滚动的时候，这就会成为性能的瓶颈。
     *      方案: 所以可以利用convertView这个参数, 相当于缓存.
     *
     * 性能问题2:
     *      会调用View 的findViewById() 方法来获取一次控件的实例。
     *      方案: 我们可以借助一个ViewHolder(一个自定义的类)作为与上面的convertView同理的缓存,来对这部分性能进行优化，
     *
     * @param position
     * @param convertView 这个参数用于将之前加载好的布局进行缓存，以便之后可以进行重用
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //获取当前项的Fruit实例
        Fruit fruit = getItem(position);
        View view;
        //用自定义的内部类来强化性能,也相当于缓存. 避免每次进来都调用view.findViewById.
        //Q: 只是个方法内的局部变量? A: 用了全局view的setTag给存储到全局了.
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            //为这个子项活动绑定我们传入的(子)布局(fruit_item.xml)
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = view.findViewById(R.id.fruit_name);
            //将ViewHolder存储在View中
            view.setTag(viewHolder);
        } else {
            //从缓存先拿
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
    }
}
