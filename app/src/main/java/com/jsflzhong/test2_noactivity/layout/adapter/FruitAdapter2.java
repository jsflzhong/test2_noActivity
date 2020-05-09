package com.jsflzhong.test2_noactivity.layout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
public class FruitAdapter2 extends RecyclerView.Adapter<FruitAdapter2.ViewHolder> {

    private List<Fruit> mFruitList;

    /**
     * 内部类.
     * 作为一个要作用于RecyclerView的viewHolder,需要继承RecyclerView.ViewHolder.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;

        /**
         * 参数一般会传入子项布局.
         *
         * @param view 子项布局
         */
        public ViewHolder(View view) {
            super(view);
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
        }
    }

    /**
     * 构造函数.
     * 把要展示的数据源通过这里传入.
     *
     * @param objects 要展示的数据源通过这里传入.
     */
    public FruitAdapter2(List<Fruit> objects) {
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
                .inflate(R.layout.fruit_item_vertical, parent, false);
        //把子项布局传入自定义的ViewHolder.
        return new ViewHolder(view);
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
