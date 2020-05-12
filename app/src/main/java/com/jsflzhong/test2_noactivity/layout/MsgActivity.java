package com.jsflzhong.test2_noactivity.layout;


import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jsflzhong.test2_noactivity.BasicActivity;
import com.jsflzhong.test2_noactivity.R;
import com.jsflzhong.test2_noactivity.entity.Msg;
import com.jsflzhong.test2_noactivity.layout.adapter.MsgAdapter;

import java.util.ArrayList;
import java.util.List;

public class MsgActivity extends BasicActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button sendButton;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    /**
     * 加载主布局.
     */
    @Override
    public void setContentView() {
        setContentView(R.layout.nine_patc_layout_main);
        Toast.makeText(this, "MsgActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadView() {
        //加载主布局
        setContentView(R.layout.nine_patc_layout_main);
        // 初始化消息数据
        initMsgs();
        inputText = (EditText) findViewById(R.id.input_text);
        sendButton = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        sendButton.setOnClickListener(v -> {
            String content = inputText.getText().toString();
            if (!"".equals(content)) {
                Msg msg = new Msg(content, Msg.TYPE_SENT);
                msgList.add(msg);
                //适配器的notifyItemInserted() 方法，用于通知列表有新的数据插入，这样新增的一条消息才能够在RecyclerView中显
                // 当有新消息时，刷新RecyclerView中的显示
                adapter.notifyItemInserted(msgList.size() - 1);
                //RecyclerView的scrollToPosition() 方法将显示的数据定位到最后一行，以保证一定可以看得到最后发出的一条消息.
                msgRecyclerView.scrollToPosition(msgList.size() - 1);
                // 调用EditText的setText() 方法将输入的内容清空。
                inputText.setText("");
            } else {
                Toast.makeText(this, "Input msg firstly!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
