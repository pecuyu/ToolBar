package com.yu.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Method;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){  // 隐藏title
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        //设置我们的ToolBar
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        //设置我们的SearchView
        //设置展开后图标的样式,这里只有两种,一种图标在搜索框外,一种在搜索框内
        searchView.setIconifiedByDefault(true);
        // 写上此句后searchView初始是可以点击输入的状态，如果不写，那么就需要点击下放大镜，
        // 才能出现输入框,也就是设置为ToolBar的ActionView，默认展开
        searchView.onActionViewExpanded();
        searchView.requestFocus();//输入焦点
        //添加提交按钮，监听在OnQueryTextListener的onQueryTextSubmit响应
        searchView.setSubmitButtonEnabled(true);
        //将控件设置成可获取焦点状态,默认是无法获取焦点的,只有设置成true,才能获取控件的点击事件
        searchView.setFocusable(true);
        searchView.setIconified(false);//输入框内icon不显示
        searchView.requestFocusFromTouch();//模拟焦点点击事件

        searchView.setFocusable(false);//禁止弹出输入法，在某些情况下有需要
        searchView.clearFocus();//禁止弹出输入法，在某些情况下有需要

        //事件监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //搜索提交时
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();

                return false;
            }

            // 文字改变时
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.menu_tool_bar, menu); // 解析目录的布局
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 相应目录条目的点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {  // 根据条目id来判断
            case R.id.id_menu_add:
                Toast.makeText(this, "what would you like to add?", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_menu_music:
                Toast.makeText(this, "what would you like to listen?", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_menu_location:
                Toast.makeText(this, "what would you like to know current location?", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_menu_screenshoot:
                Toast.makeText(this, "what would you like to screenshoot?", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        setIconEnable(menu, true);
        return super.onMenuOpened(featureId, menu);


    }


    /**
     * 设置条目图标显示
     * @param menu
     * @param enable
     */
    protected void setIconEnable(Menu menu, boolean enable) {
        try {
            Class<?> clazz = Class.forName("android.support.v7.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            // MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


