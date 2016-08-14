package com.example.achuan.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*1.0创建相关实例*/
    private RecyclerView mRecyclerView;//主控件
    private List<String> mDatas;//数据集
    private SimpleAdapter mAdapter;//自定义的适配器类

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initViews();
        /*2.0实例化Adapter,并设置适配器*/
        mAdapter=new SimpleAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);
        /*2.1设置相关布局管理*/
        LinearLayoutManager linearManager=new LinearLayoutManager
                (this,LinearLayoutManager.VERTICAL,false);//设置布局方式为线性居中布局
        mRecyclerView.setLayoutManager(linearManager);//应用到RecyclerView中去
        /*2.2设置item之间的分隔线*/
        /*mRecyclerView.addItemDecoration(new DividerItemDecoration
                (this,DividerItemDecoration.VERTICAL_LIST));//引用了开源的布局文件方法*/
        //3.2.1设置动画效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        /*****3.3初始化添加监听*****/
        mAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(MainActivity.this,"Click :"+postion,
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onItemLongClick(View view, int postion) {
                 mAdapter.deleteData(postion);
            }
        });

    }
    /*1.1初始化数据*/
    private void initDatas() {
        mDatas=new ArrayList<String>();
        for (int i = 'A'; i <'z' ; i++)
        {
            mDatas.add(""+(char)i);
        }
    }
    /*1.2初始化布局*/
    private void initViews() {
        mRecyclerView= (RecyclerView) findViewById(R.id.id_recyclerView);
    }
    /*添加menu菜单项的两个方法*/
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_add:
                mAdapter.addData(1);
                break;
            case R.id.action_delete:
                mAdapter.deleteData(1);
                break;
            case R.id.action_listview://设置为ListView
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_gridview://设置为垂直的GridView
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
                //设置为一行有3列，但分隔线会绘制3次，颜色会加深
                break;
            case R.id.action_hor_gridwiew://设置为水平的GridView
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                        5, StaggeredGridLayoutManager.HORIZONTAL));//设置1列5行的水平布局
                break;
            case R.id.action_staggered://设置为GirdView瀑布流
                Intent intent=new Intent(this,StaggeredGridLayoutActivity.class);//设置跳转
                startActivity(intent);//启动跳转
                break;
            case R.id.action_settings:
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
