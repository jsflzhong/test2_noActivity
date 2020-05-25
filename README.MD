1.活动状态
    每个活动在其生命周期中最多可能会有4种状态。
    01. 运行状态
        当一个活动位于返回栈的栈顶时，这时活动就处于运行状态。系统最不愿意回收的就是处
        于运行状态的活动，因为这会带来非常差的用户体验。
    02. 暂停状态
        当一个活动不再处于栈顶位置，但仍然可见时，这时活动就进入了暂停状态。你可能会觉
        得既然活动已经不在栈顶了，还怎么会可见呢？这是因为并不是每一个活动都会占满整个
        屏幕的，比如对话框形式的活动只会占用屏幕中间的部分区域，你很快就会在后面看到这
        种活动。处于暂停状态的活动仍然是完全存活着的，系统也不愿意去回收这种活动（因为
        它还是可见的，回收可见的东西都会在用户体验方面有不好的影响），只有在内存极低的
        情况下，系统才会去考虑回收这种活动。
    03. 停止状态
        当一个活动不再处于栈顶位置，并且完全不可见的时候，就进入了停止状态。系统仍然会
        为这种活动保存相应的状态和成员变量，但是这并不是完全可靠的，当其他地方需要内存
        时，处于停止状态的活动有可能会被系统回收。
    04. 销毁状态
        当一个活动从返回栈中移除后就变成了销毁状态。系统会最倾向于回收处于这种状态的活
        动，从而保证手机的内存充足。

2.活动的生存期
      Activity类中定义了7个回调方法，覆盖了活动生命周期的每一个环节，下面就来一一介绍这7个
      方法。
      onCreate() 。
          这个方法你已经看到过很多次了，每个活动中我们都重写了这个方法，它
          会在活动第一次被创建的时候调用。你应该在这个方法中完成活动的初始化操作，比如说
          加载布局、绑定事件等。
      onStart() 。
          这个方法在活动由不可见变为可见的时候调用。
      onResume() 。
          这个方法在活动准备好和用户进行交互的时候调用。此时的活动一定位于
          返回栈的栈顶，并且处于运行状态。
      onPause() 。
          这个方法在系统准备去启动或者恢复另一个活动的时候调用。我们通常会在
          这个方法中将一些消耗CPU的资源释放掉，以及保存一些关键数据，但这个方法的执行速
          度一定要快，不然会影响到新的栈顶活动的使用。
      onStop() 。
          这个方法在活动完全不可见的时候调用。它和onPause() 方法的主要区别在
          于，如果启动的新活动是一个对话框式的活动，那么onPause() 方法会得到执行，
          而onStop() 方法并不会执行。
      onDestroy() 。
          这个方法在活动被销毁之前调用，之后活动的状态将变为销毁状态。
      onRestart() 。
          这个方法在活动由停止状态变为运行状态之前调用，也就是活动被重新启动了。

      以上7个方法中除了onRestart() 方法，其他都是两两相对的，从而又可以将活动分为3种生存期。

      完整生存期 。
          活动在onCreate() 方法和onDestroy() 方法之间所经历的，就是完整生
          存期。一般情况下，一个活动会在onCreate() 方法中完成各种初始化操作，而
          在onDestroy() 方法中完成释放内存的操作。
      可见生存期 。
          活动在onStart() 方法和onStop() 方法之间所经历的，就是可见生存
          期。在可见生存期内，活动对于用户总是可见的，即便有可能无法和用户进行交互。我们
          可以通过这两个方法，合理地管理那些对用户可见的资源。比如在onStart() 方法中对资
          源进行加载，而在onStop() 方法中对资源进行释放，从而保证处于停止状态的活动不会
          占用过多内存。
      前台生存期 。
          活动在onResume() 方法和onPause() 方法之间所经历的就是前台生存
          期。在前台生存期内，活动总是处于运行状态的，此时的活动是可以和用户进行交互的，
          我们平时看到和接触最多的也就是这个状态下的活动。

3.活动的启动模式
    活动的启动模式对你来说应该是个全新的概念，在实际项目中我们应该根据特定的需求为每个
    活动指定恰当的启动模式。
    启动模式一共有4种，分别是standard(默认)、singleTop、singleTask和singleInstance，
    可以在AndroidManifest.xml中通过给<activity> 标签指定android:launchMode 属性来选择启动模式.

    3.1.standard 模式
        对于使用standard模式的活动，系统不会在乎这个活动是否已经在返回栈中存在，每次启动都会创建该活动的一个新的实例.
        缺点:可能在有些情况下，你会觉得standard模式不太合理。活动明明已经在栈顶了，为什么再次启动的时候还要创建一个新的活动实例呢？

    3.2.singleTop 模式
        当活动的启动模式指定为singleTop，在启动活动时如果发现返回栈的"栈顶"已经是该活动(只针对栈顶!!),
        则认为可以直接使用它，不会再创建新的活动实例。
        缺点:如果该活动并没有处于栈顶的位置，还是可能会创建多个活动实例的

    3.3.singleTask 模式
        有没有什么办法可以让某个活动在整个应用程序的上下文中只存在一个实例呢？
        这就要借助singleTask模式来实现了。
        当活动的启动模式指定为singleTask，每次启动该活动时系统首先会在返回栈中检查是否存
        在该活动的实例，如果发现已经存在则直接使用该实例，并把在这个活动之上的所有活动统统
        出栈，如果没有发现就会创建一个新的活动实例。

    3.4.singleInstance模式
        不同于以上3种启动模式，指定为singleInstance模式的活动会启用一个新的返回栈
        来管理这个活动（其实如果singleTask模式指定了不同的taskAffinity，也会启动一个新的返回
        栈）。那么这样做有什么意义呢？想象以下场景，假设我们的程序中有一个活动是允许其他程
        序调用的，如果我们想实现其他程序和我们的程序可以共享这个活动的实例，应该如何实现
        呢？使用前面3种启动模式肯定是做不到的，因为每个应用程序都会有自己的返回栈，同一个活
        动在不同的返回栈中入栈时必然是创建了新的实例。而使用singleInstance模式就可以解决这个问
        题，在这种模式下会有一个单独的返回栈来管理这个活动，不管是哪个应用程序来访问这个活
        动，都共用的同一个返回栈，也就解决了共享活动实例的问题。

4.4种基本布局
    4.1.线性布局
        LinearLayout又称作线性布局，是一种非常常用的布局。正如它的名字所描述的一样，这个布局
        会将它所包含的控件在线性方向上依次排列.
    4.2.相对布局
        RelativeLayout又称作相对布局，也是一种非常常用的布局。和LinearLayout的排列规则不同，
        RelativeLayout显得更加随意一些，它可以通过相对定位的方式让控件出现在布局的任何位置
    4.3.帧布局
        FrameLayout又称作帧布局，它相比于前面两种布局就简单太多了，因此它的应用场景也少了很
        多。这种布局没有方便的定位方式，所有的控件都会默认摆放在布局的左上角
    4.4.百分比布局
        在这种布局中，我们可以不再使用wrap_content 、match_parent 等方式来指定控件的大小，
        而是允许直接指定控件在布局中所占的百分比，
        这样的话就可以轻松实现平分布局甚至是任意比例分割布局的效果了。
        打开app/build.gradle文件，在dependencies 闭包中添加如下内容：
            dependencies {
            implementation 'androidx.percentlayout:percentlayout:1.0.0'
            }

5.在布局中引用其他布局.
    有两种方式:
    5.1.简化方式:
        使用include即可.注意要在LinearLayout节点层级的下层.
        例子见: title.xml和first_layout.xml
    5.2.完全方式:
        使用java代码,初始化一个Adapter,然后拿到当前布局中的listView元素, 最后调用istView.setAdapter(XXAdapter);即可完成.
        其中的Adapter,可以用内置的,也可以是自定义的,自定义的可以绑定一个自定义的布局.
        例子见: ListViewActivity2(父活动), listview2_layout.xml(父布局), FruitAdapter(自定义的Adapter), fruit_item.xml(子布局)


6.自定义控件.
    TitleLayout.java

7.自定义Adapter.
    FruitAdapter.java

8.ListView
    滚动下滑数据菜单.
    优点: 经典.
    缺点: 如果我们不使用一些技巧来提升它的运行效率，那么ListView的性能就会非常差。
    还有，ListView的扩展性也不够好，它只能实现数据纵向滚动的效果，如果我们想实现横向滚动的话，ListView是做不到的。

    例子见: ListViewActivity2(父活动), listview2_layout.xml(父布局), FruitAdapter(自定义的Adapter), fruit_item.xml(子布局)
    注意:
        关键是自定义的Adapter. 与下面的RecyclerView的Adapter不同.

9.RecyclerView
    它可以说是一个增强版的
    ListView，不仅可以轻松实现和ListView同样的效果，还优化了ListView中存在的各种不足之处.
    目前Android官方更加推荐使用RecyclerView，未来也会有更多的程序逐渐从ListView转向RecyclerView，

    9.1.纵向滚动.
        父活动:RecyclerViewActivity
        父布局:recycler_view_layout.xml
        Adapter:FruitAdapter2
        子布局:fruit_item_vertical.xml

    9.2.横向滚动:
        父活动:RecyclerViewLinearRollingActivity
        父布局:recycler_view_layout.xml
        Adapter:FruitAdapter2
        子布局:fruit_item_vertical

    为什么ListView很难或者根本无法实现的效果在RecyclerView上这么轻松就能实现了呢？这主要
    得益于RecyclerView出色的设计。ListView的布局排列是由自身去管理的，而RecyclerView则将
    这个工作交给了"LayoutManager"，LayoutManager中制定了一套可扩展的布局排列接口，子类只要
    按照接口的规范来实现，就能定制出各种不同排列方式的布局了。

    除了LinearLayoutManager之外，RecyclerView还给我们提供了GridLayoutManager和
    StaggeredGridLayoutManager这两种内置的布局排列方式。GridLayoutManager可以用于实现网格
    布局，StaggeredGridLayoutManager可以用于实现瀑布流布局.

    9.3.瀑布流布局:
        Activity: RecyclerViewStaggeredGridActivity

    9.4.瀑布流布局RecyclerView的点击事件.
        RecyclerView并没有提供类似于setOnItemClickListener()
        这样的注册监听器方法，而是需要我们自己给子项具体的View去注册点击事件，相比于ListView
        来说，实现起来要复杂一些。

10.制作Nine-Patc
    右键图片-->Create 9-patch file.
    我们可以在图片的四个边框绘制一个个的小黑点，在上边框和左边框绘制的部分表示当图片需
    要拉伸时就拉伸黑点标记的区域，在下边框和右边框绘制的部分表示内容会被放置的区域。使
    用鼠标在图片的边缘拖动就可以进行绘制了，按住Shift键拖动可以进行擦除
    注意:
        保存了.9文件后,不能把.9的文件名改成没有.9的模式,否则xml中无法引入. 直接把不带.9的原图删掉,xml即可自动引入.

    1.MSG功能:
        1>.主活动:MsgActivity (ing...)
        2>.主布局:nine_patc_layout_main.xml
        3>.Adapter: MsgAdapter
        4>.子布局:msg_item.xml (这里面直接用LinearLayout又写了2个子项布局, 在代码中通过抓他们的ID,来修改它们的状态.)


11.广播
    发送广播的方法其实之前稍微提到过，如果你记性好的话可能还会有印
    象，就是借助我们第2章学过的Intent。而接收广播的方法则需要引入一个新的概念——广播接
    收器（Broadcast Receiver）

    广播是一种可以跨进程的通信方式，这一点从前面接收系统广播的时候就可以看出来了。因此
    在我们应用程序内发出的广播，其他的应用程序应该也是可以收到的.

    1.类型. Android中的广播主要可以分为两种类型：标准广播和有序广播。

        1.1.标准广播 （Normal broadcasts）是一种完全异步执行的广播，在广播发出之后，所有的广播
        接收器几乎都会在同一时刻接收到这条广播消息，因此它们之间没有任何先后顺序可言。
        这种广播的效率会比较高，但同时也意味着它是无法被截断的

        1.2.有序广播 （Ordered broadcasts）则是一种同步执行的广播，在广播发出之后，同一时刻只
          会有一个广播接收器能够收到这条广播消息，当这个广播接收器中的逻辑执行完毕后，广
          播才会继续传递。所以此时的广播接收器是有先后顺序的，优先级高的广播接收器就可以
          先收到广播消息，并且前面的广播接收器还可以截断正在传递的广播，这样后面的广播接
          收器就无法收到广播消息了

    2.接收系统广播(标准广播)(全局)
        Android内置了很多系统级别的广播，我们可以在应用程序中通过监听这些广播来得到各种系统
        的状态信息。比如手机开机完成后会发出一条广播，电池的电量发生变化会发出一条广播，时
        间或时区发生改变也会发出一条广播，等等。如果想要接收到这些广播，就需要使用广播接收器.

        注册广播的方式一般有两种，在代码中注册和在AndroidManifest.xml中注册，
        其中前者也被称为动态注册，后者也被称为静态注册。

        如何创建一个广播接收器呢？只需要新建一个类，让它继承自BroadcastReceiver ，
        并重写父类的onReceive() 方法就行了。这样当有广播到来时，onReceive() 方法就会得到执行，具体的逻辑就可以在这个方法中处理。

        网络变化接收器(动态):BroadNetWorkActivity

        使用IDE自动新建广播接收器:
            右击包→New→Other→Broadcast Receiver，
            Exported 属性表示是否允许这个广播接收器接收本程序以外的广播，
            Enabled 属性表示是否启用这个广播接收器。勾选这两个属性，点击Finish完成创建。

        需要注意的是:
        不要在onReceive() 方法中添加过多的逻辑或者进行任何的耗时操作，因为在广播接收器中
        是不允许开启线程的，当onReceive() 方法运行了较长时间而没有结束时，程序就会报错。
        因此广播接收器更多的是扮演一种打开程序其他组件的角色，比如创建一条状态栏通知，或者
        启动一个服务等.

        随开机启动广播接收器(静态,需要在manifest中配置):BootCompleteReceiver

    3.发送和接收自定义广播(标准广播)(全局):
        receiver:MyStandardReceiver, 静态,需要在manifest里面配置.
        sender:MyStandardSenderActivity

    4.有序广播(全局)
        1>.在sender的activity中,修改一行代码即可:
            //第二个参数是一个与权限相关的字符串，这里传入null 就行了
            sendOrderedBroadcast(intent, null);
        2>.如何定义广播接收器的顺序?
            在manifest中定义优先级即可.例如:
            <intent-filter android:priority="100">
                <action android:name="com.example.broadcasttest.MY_BROADCAST" />
            </intent-filter>
            优先级比较高的广播接收器就可以先收到广播.100优先级的肯定可以接收到广播.
        3>.如何在一个广播接收器中截断一条广播?
            在接收器的onReceive()方法中,调用abortBroadcast() 方法即可.

    5.本地广播(局部)
        前面我们发送和接收的广播全部属于系统全局广播，即发出的广播可以被其他任何应用程序接
        收到，并且我们也可以接收来自于其他任何应用程序的广播。这样就很容易引起安全性的问
        题，比如说我们发送的一些携带关键性数据的广播有可能被其他的应用程序截获，或者其他的
        程序不停地向我们的广播接收器里发送各种垃圾广播。

        Android引入了一套本地广播机制，使用这个机制发出
        的广播只能够在应用程序的内部进行传递，并且广播接收器也只能接收来自本应用程序发出的
        广播，这样所有的安全性问题就都不存在了。(即不能跨进程了)

        本地广播的用法并不复杂，主要就是使用了一个LocalBroadcastManager来对广播进行管理，并
        提供了发送广播和注册广播接收器的方法.

        另外还有一点需要说明，本地广播是无法通过静态注册的方式来接收的。其实这也完全可以理
        解，因为静态注册主要就是为了让程序在未启动的情况下也能收到广播，而发送本地广播时，
        我们的程序肯定是已经启动了，因此也完全不需要使用静态注册的功

        本地广播的优点:
            1.可以明确地知道正在发送的广播不会离开我们的程序，因此不必担心机密数据泄漏。
            2.其他的程序无法将广播发送到我们程序的内部，因此不需要担心会有安全漏洞的隐患。
            3.发送本地广播比发送系统全局广播将会更加高效。

    6.利用广播实现强制下线的功能.
       比如你的QQ号在别处登录了，就会将你强制挤下线.
       强制下线功能需要先关闭掉所有的活动，然后回到登录界面.
       1>.登录的activity: LoginActivity
       2>.广播发送器: 登录后跳转的页面的activity: LoginMainActivity.
            在该活动中, 发送了强制下线的广播.
       3>.广播接收器: 所有的activity.
            为了让所有页面都能接收到这个广播, 就把接收器ForceOfflineReceiver放到了父类中,
            该接收器的注册和注销,是在onResume()和onPause()中做的, 理由见方法上的注释.
       4>.Manifest.xml
            1.由于在ForceOfflineReceiver中弹出了一个系统级别的对话框，所以必须在AndroidManifest中声明如下权限：
              <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
            2.需要把登录页面设置为Main主页面.让所有用户必须经过登录才能进入程序.
                <activity android:name=".layout.LoginActivity" >
                    <!--把程序的主页面变成登录页面.-->
                    <intent-filter>
                        <action android:name="android.intent.action.MAIN" />
                        <category android:name="android.intent.category.LAUNCHER" />
                    </intent-filter>
                </activity>

12.持久化技术
    Android系统中主要提供了3种方式用于简单地实现数据持久化功能，
    即: 1.文件存储; 2.SharedPreferences存储; 3.数据库存储

    1>.第一种: 文件存储
        1).定义:
            文件存储是Android中最基本的一种数据存储方式，它不对存储的内容进行任何的格式化处理，
            所有数据都是原封不动地保存到文件当中的，因而它比较适合用于存储一些简单的文本数据或
            二进制数据。如果你想使用文件存储的方式来保存一些较为复杂的文本数据，就需要定义一套
            自己的格式规范，这样可以方便之后将数据从文件中重新解析出来。

        2).API:
            1>.写文件:
                Context 类中提供了一个openFileOutput() 方法，可以用于将数据存储到指定的文件中。
                这个方法接收两个参数，第一个参数是文件名，在文件创建的时候使用的就是这个名称，注意
                这里指定的文件名不可以包含路径，因为所有的文件都是默认存储到/data/data/<package
                name>/files/目录下的。第二个参数是文件的操作模式，主要有两种模式可选，MODE_PRIVATE
                和MODE_APPEND。其中MODE_PRIVATE是默认的操作模式，表示当指定同样文件名的时候，
                所写入的内容将会覆盖原文件中的内容，而MODE_APPEND则表示如果该文件已存在，就往文
                件里面追加内容，不存在就创建新文件。

            2>.读文件:
                Context 类中还提供了一个openFileInput() 方法，用于从文
                件中读取数据。这个方法要比openFileOutput() 简单一些，它只接收一个参数，即要读取
                的文件名，然后系统会自动到/data/data/<package name>/files/目录下去加载这个文件，并返回一
                个FileInputStream 对象，得到了这个对象之后再通过Java流的方式就可以将数据读取出来了。

        3).实践
            1>.PersisFileActivity
                在onDestroy()里写文件, 在onCreate()里读文件.
            2>.从IDE里看手机文件的方法:
                书中的方式已经不推荐了, 推荐: View-->Tool Windows-->Device file explorer.
            3>.生成的文件

    2>.第二种: SharedPreferences存储
        1).定义
            不同于文件的存储方式，SharedPreferences是使用键值对的方式来存储数据的。也就是说，当保
            存一条数据的时候，需要给这条数据提供一个对应的键，这样在读取数据的时候就可以通过这
            个键把相应的值取出来。而且SharedPreferences还支持多种不同的数据类型存储，如果存储的数
            据类型是整型，那么读取出来的数据也是整型的；如果存储的数据是一个字符串，那么读取出
            来的数据仍然是字符串。

        2).API
            要想使用SharedPreferences来存储数据，首先需要获取到SharedPreferences 对象。Android
            中主要提供了3种方法用于得到SharedPreferences 对象。

            01. Context 类中的getSharedPreferences() 方法
                此方法接收两个参数，
                第一个参数用于指定SharedPreferences文件的名称，如果指定的文件
                不存在则会创建一个，SharedPreferences文件都是存放在/data/data/<package
                name>/shared_prefs/目录下的。
                第二个参数用于指定操作模式，目前只有MODE_PRIVATE
                这一种模式可选，它是默认的操作模式，和直接传入0效果是相同的，表示只有当前的应用
                程序才可以对这个SharedPreferences文件进行读写。其他几种操作模式均已被废弃，
                MODE_WORLD_READABLE和MODE_WORLD_WRITEABLE这两种模式是在Android 4.2
                版本中被废弃的，MODE_MULTI_PROCESS模式是在Android 6.0版本中被废弃的。
            02. Activity 类中的getPreferences() 方法
                这个方法和Context中的getSharedPreferences() 方法很相似，不过它只接收一个操作
                模式参数，因为使用这个方法时会自动将当前活动的类名作为SharedPreferences的文件名。
            03. PreferenceManager 类中的getDefaultSharedPreferences() 方法
                这是一个静态方法，它接收一个Context 参数，并自动使用当前应用程序的包名作为前缀
                来命名SharedPreferences文件。得到了SharedPreferences 对象之后，就可以开始向
                SharedPreferences文件中存储数据了，主要可以分为3步实现。
                (1) 调用SharedPreferences 对象的edit() 方法来获取一个SharedPreferences.Editor 对象。
                (2) 向SharedPreferences.Editor 对象中添加数据，比如添加一个布尔型数据就使
                用putBoolean() 方法，添加一个字符串则使用putString() 方法，以此类推。
                (3) 调用apply() 方法将添加的数据提交，从而完成数据存储操作。

        3).实践
            PersisSharedPreferencesActivity.
            生成的文件:
                /data/data/com.example.sharedpreferencestest/shared_prefs/目录下，可以看到生成了一个data.xml

        4).记住密码功能
            用SharedPreferences作为持久化.
            不推荐, 这里只作为示例, 因为不安全!
                com.jsflzhong.test2_noactivity.layout.LoginActivity.doLoginButtonWithRememberPswdInSharedPreferences









N.Trouble shooting.

    1.R.id...找不到.
        File-->sync with file system.
        还不行就重启IDE即可.
