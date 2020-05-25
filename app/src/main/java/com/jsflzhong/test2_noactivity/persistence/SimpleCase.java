package com.jsflzhong.test2_noactivity.persistence;

import android.content.Context;

import com.jsflzhong.test2_noactivity.BasicActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SimpleCase extends BasicActivity {

    /**
     * 最基本的写文件.
     * 需要类继承activity,才能调用openFileOutput()方法.
     * <p>
     * 才能调用openFileOutput():
     * 第一个参数是文件名，在文件创建的时候使用的就是这个名称，注意
     * 这里指定的文件名不可以包含路径，因为所有的文件都是默认存储到/data/data/<package
     * name>/files/目录下的。第二个参数是文件的操作模式，主要有两种模式可选，MODE_PRIVATE
     * 和MODE_APPEND。其中MODE_PRIVATE是默认的操作模式，表示当指定同样文件名的时候，
     * 所写入的内容将会覆盖原文件中的内容，而MODE_APPEND则表示如果该文件已存在，就往文
     * 件里面追加内容，不存在就创建新文件.
     */
    public void save() {
        String data = "Data to save";
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 最基本的读文件.
     *
     * @return 文件内容字符串.
     */
    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    @Override
    public void setContentView() {

    }
}
