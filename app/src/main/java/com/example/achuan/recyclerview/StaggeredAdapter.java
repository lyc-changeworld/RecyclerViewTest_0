package com.example.achuan.recyclerview;
import android.app.ActionBar;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by achuan on 16-2-26.
 * 功能：2.4自定义瀑布流adapter适配器,为item添加随机高度值
 */
/*创建适配器类*/
public class StaggeredAdapter  extends SimpleAdapter
{
    private List<Integer> mHeights;//瀑布流中的item的高度集

    public StaggeredAdapter(Context context,List<String> datas) {
        /*******3.4继承后直接调用父类的方法,重复的方法可以全部删除*******/
        super(context, datas);//
        //为item添加随机的高度
        mHeights=new ArrayList<Integer>();
        for (int i = 0; i <mDatas.size() ; i++) {
            mHeights.add((int)(100+ Math.random()*300));
        }
    }
    //绑定ViewHolder
    public void onBindViewHolder(final MyViewHolder holder, int postion) {
        holder.tv.setText(mDatas.get(postion));//为tv控件设置文本值
        //将随机高度值传给itemView
        ViewGroup.LayoutParams lp=  holder.itemView.getLayoutParams();
        lp.height=mHeights.get(postion);
        holder.itemView.setLayoutParams(lp);

        setItemEvent(holder);
    }
}


