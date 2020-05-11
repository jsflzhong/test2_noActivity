package com.jsflzhong.test2_noactivity.layout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.jsflzhong.test2_noactivity.R;
import com.jsflzhong.test2_noactivity.entity.Fruit;

import java.util.List;

/**
 * 自定义Adapter
 * For "recyclerView".
 * <p>
 * 泛型是个在本类中自定义的ViewHolder.
 */
public class FruitAdapter3 extends RecyclerView.Adapter<FruitAdapter3.ViewHolder> {

    private List<Fruit> mFruitList;

    /**
     * 内部类.
     * 作为一个要作用于RecyclerView的viewHolder,需要继承RecyclerView.ViewHolder.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        //用来保存子项最外层布局的实例.
        View fruitView;

        /**
         * 参数一般会传入子项布局.
         *
         * @param view 子项布局
         */
        public ViewHolder(View view) {
            super(view);
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
            fruitView = view;
        }
    }

    /**
     * 构造函数.
     * 把要展示的数据源通过这里传入.
     *
     * @param objects 要展示的数据源通过这里传入.
     */
    public FruitAdapter3(List<Fruit> objects) {
        mFruitList = objects;
    }

    /**
     * 绑定子项布局: fruit_item.xml
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //拿到子项布局
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item_staggered_grid, parent, false);
        //把子项布局传入自定义的ViewHolder.
        ViewHolder viewHolder = new ViewHolder(view);
        //为图片注册点击事件. 当点击图片时触发.
        viewHolder.fruitImage.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Toast.makeText(v.getContext(), "you clicked image" + fruit.getName(), Toast.LENGTH_SHORT).show();
        });
        //为子项布局注册点击事件
        //点击菠萝的文字部分，由于TextView并没有注册点击事件，因此点击文字这个事件会被子项的最外层布局捕获到.
        viewHolder.fruitView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Toast.makeText(v.getContext(), "you clicked view" + fruit.getName(), Toast.LENGTH_SHORT).show();
        });
        return viewHolder;
    }

    /**
     * 用于对RecyclerView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候执
     *
     * @param holder   ViewHolder
     * @param position 可通过position 参数得到当前项的Fruit 实例，
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

}
