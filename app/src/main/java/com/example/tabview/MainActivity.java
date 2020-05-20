package com.example.tabview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tabcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabcount=(TextView)findViewById(R.id.tabcount);

        final TabHost tabHost=(TabHost)findViewById(R.id.tab_host);//获取TabHost控件
        tabHost.setup();//初始化，如果类继承TabActivity，则初始化不能使用这个指令
        //设置标签名称，标签名称和图片不能同时设置，如果不使用自定义格式，可通过以下方法设置标签名或图标
        //tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("本地音乐",null).setContent(R.id.tab1));
        //tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("网络音乐",null).setContent(R.id.tab2));
        //tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("在线音乐",null).setContent(R.id.tab3));

        //采用自定义样式设定标签
        /*添加tab*/
        for (int i = 0; i < 3; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.tabview, null, false);
            TextView textView = (TextView) view.findViewById(R.id.tabveiw_text);
            ImageView imageView = (ImageView) view.findViewById(R.id.tabview_icon);
            TextView textView1=(TextView)view.findViewById(R.id.icontext);

            switch (i) {
                case 0:
                    textView.setText("寄存器");
                    textView1.setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.reg1);
                    tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(view).setContent(R.id.tab1));
                    break;
                case 1:
                    textView.setText("报表");
                    textView1.setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.report1);
                    tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(view).setContent(R.id.tab2));
                    break;
                case 2:
                    textView.setText("报警");
                    imageView.setImageResource(R.drawable.alarm1);
                    tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator(view).setContent(R.id.tab3));
                    break;

            }

        }

        //设置事件监听
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //以下为动态改变文本颜色，可以使用选择器来实现
                //全部恢复初始色

                for(int i=0;i<3;i++){
                    ImageView imageView=(ImageView)tabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tabview_icon);
                    switch (i){
                        case 0: imageView.setImageResource(R.drawable.reg1);
                        break;
                        case 1: imageView.setImageResource(R.drawable.report1);
                        break;
                        case 2: imageView.setImageResource(R.drawable.alarm1);
                        break;

                    }

                }
//                if (tabHost.getCurrentTabTag()==tabId){//被选中改变颜色
//                    ((TextView)tabHost.getCurrentTabView().findViewById(R.id.tabveiw_text))
//                            .setTextColor(getResources().getColor(R.color.clolorTab));
//                }
                switch (tabId){

                    case "tab1":
                        ((ImageView)tabHost.getCurrentTabView().findViewById(R.id.tabview_icon)).setImageResource(R.drawable.reg);
                        tabcount.setText("本地音乐");
                        break;
                    case "tab2":
                        ((ImageView)tabHost.getCurrentTabView().findViewById(R.id.tabview_icon)).setImageResource(R.drawable.report);
                        tabcount.setText("网络音乐");
                        break;
                    case "tab3":
                        ((ImageView)tabHost.getCurrentTabView().findViewById(R.id.tabview_icon)).setImageResource(R.drawable.alarm);
                        tabcount.setText("在线音乐");
                        break;

                }
            }
        });

        //初次进入第一个TAB颜色
        for(int i=0;i<3;i++){
            ImageView imageView=(ImageView)tabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tabview_icon);
            switch (i){
                case 0: imageView.setImageResource(R.drawable.reg);
                    break;
                case 1: imageView.setImageResource(R.drawable.report1);
                    break;
                case 2: imageView.setImageResource(R.drawable.alarm1);
                    break;

            }

        }



    }
}
