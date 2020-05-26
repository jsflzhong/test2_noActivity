package com.jsflzhong.test2_noactivity.persistence.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * 要使用SQLite相关的API,需要继承于SQLiteOpenHelper.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "MyDatabaseHelper";
    private Context mContext;

    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    /**
     * 构造函数
     *
     * @param context context
     * @param name    db name  数据库的名字是在这里定义的.
     * @param factory 允许我们在查询数据的时候返回一个自定义的Cursor，一般都是传入null 。
     * @param version 当前数据库的版本号，可用于对数据库进行升级操作。
     */
    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    /**
     * 执行时机:
     * 调用SQLiteOpenHelper的getReadableDatabase() 或getWritableDatabase() 方法后会自动执行?
     * <p>
     * 创建SQLite DB
     * 数据库文件会存放在/data/data/<package name>/databases/目录下。
     *
     * @param db db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
        Toast.makeText(mContext, "@@@[onCreate]Create succeed", Toast.LENGTH_SHORT).show();
    }

    /**
     * 创建DB表.
     * <p>
     * 注意,如果创建了一次DB, 之后再想直接动数据表结构或新建表的话, 是不行的.
     * 因为此时BookStore.db数据库已经存在了，之后不管我们怎
     * 样点击Create database按钮，MyDatabaseHelper中的onCreate() 方法都不会再次执行，
     * 因此新添加的表也就无法得到创建了
     * <p>
     * 解决这个问题的办法也相当简单，只需要先将程序卸载掉，然后重新运行，这时BookStore.db数
     * 据库已经不存在了，如果再点击Create database按钮，MyDatabaseHelper中的onCreate() 方法
     * 就会执行，这时Category表就可以创建成功了。
     * 不过，通过卸载程序的方式来新增一张表毫无疑问是很极端的做法，其实我们只需要巧妙地运
     * 用SQLiteOpenHelper的升级功能就可以很轻松地解决这个问题.
     * <p>
     * 方案: 在onUpgrade中drop表,然后再调用本方法.
     *
     * @param db
     */
    private void createTables(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }

    /**
     * 执行时机:
     * 记得SQLiteOpenHelper的构造方法里接收的第四个参数吗？它表示当前数据库的版本号，
     * 之前我们传入的是1，现在只要传入一个比1大的数，就可以让onUpgrade() 方法得到执行了。
     *
     * 例如本类构造函数的调用方: com.jsflzhong.test2_noactivity.layout.LoginActivity#createDB()
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        createTables(db);
    }
}
