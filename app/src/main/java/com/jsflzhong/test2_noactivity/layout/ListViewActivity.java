package com.jsflzhong.test2_noactivity.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;

public class ListViewActivity extends BasicActivity {

    //为listView准备的数据.
    private String[] data = {"Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
            "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    public void setContentView() {
        setContentView(R.layout.list_view_layout);
    }

    @Override
    public void loadView() {
        loadListView();
    }

    /**
     * 加载listView,并提供数据给它.
     * <p>
     * 数组中的数据是无法直接传递给ListView的，我们还需要借助适配器来完成。
     * Android中提供了很多适配器的实现类，最好用的就是ArrayAdapter.
     * 在ArrayAdapter的构造函数中依次传入当前上下文、"ListView子项布局的id"，以及要适配的数据.
     * <p>
     * 关于"ListView子项布局的id":
     * 注意，我们使用了android.R.layout.simple_list_item_1 作为ListView子项布局的id，这是一个Android
     * 内置的布局文件，里面只有一个TextView，可用于简单地显示一段文本。这样适配器对象就构建好了。
     */
    public void loadListView() {
        ListView listView = findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }
}
