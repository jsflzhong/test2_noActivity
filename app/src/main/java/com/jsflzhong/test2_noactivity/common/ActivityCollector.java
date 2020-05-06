package com.jsflzhong.test2_noactivity.common;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

    private static final String TAG = "ActivityCollector";
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 不管你想在什么地方退出程序，只需要调用ActivityCollector.finishAll()方法就可以了.
     * 例如绑在某个活动中的一个按钮上,或回退键上,等.
     */
    public static void finishAll() {
        Log.d(TAG,"@@@[finishAll]Begin to finish all...");
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();

        //也可以在销毁所有活动的代码后面再加上杀掉当前进程的代码，以保证程序完全退出.
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
