package com.example.achuan.recyclerview;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by achuan on 16-2-26.
 * 功能：2.5创建瀑布流的activity
 */
public class StaggeredGridLayoutActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;//主控件
    private List<String> mDatas;//数据集
    private StaggeredAdapter mAdapter;//自定义的适配器类

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initViews();
        mAdapter=new StaggeredAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager
                (3, StaggeredGridLayoutManager.VERTICAL));

        /********3.5实现瀑布流的item点击监听事件************/
        mAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

            }
            @Override
            public void onItemLongClick(View view, int postion) {
                  mAdapter.deleteData(postion);
            }
        });
    }
    private void initDatas() {
        mDatas=new ArrayList<String>();
        for (int i = 'A'; i <'z' ; i++)
        {
            mDatas.add(""+(char)i);
        }
    }
    private void initViews() {
        mRecyclerView= (RecyclerView) findViewById(R.id.id_recyclerView);
    }
}
