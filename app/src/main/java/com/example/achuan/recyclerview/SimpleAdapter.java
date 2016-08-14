package com.example.achuan.recyclerview;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
/**
 * Created by achuan on 16-2-25.
 * 功能：自定义adapter适配器
 */
/*1.3创建适配器类*/
public class SimpleAdapter  extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder>
{
    private LayoutInflater mInflater;//创建布局装载对象来获取相关控件（类似于findViewById()）
    protected List<String> mDatas;//存储数据集合
    private Context mContext;//显示框面
    /***********3.1自定义item的点击事件接口*********/
    public interface OnItemClickListener
    {
        void onItemClick(View view,int postion);
        void onItemLongClick(View view,int postion);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mOnItemClickListener=listener;
    }
    /*1.5创建构造方法*/
    public SimpleAdapter(Context context,List<String> datas) {
        this.mContext=context;
        this.mDatas=datas;
        //通过获取context来初始化mInflater对象
        mInflater=LayoutInflater.from(context);
    }
    //适配器中数据集中的个数
    public int getItemCount() {
        return mDatas.size();
    }
    //先创建ViewHolder
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view=mInflater.inflate(R.layout.item_single_tv,parent,false);//载入item布局
        MyViewHolder viewHolder=new MyViewHolder(view);//创建一个item的viewHoler实例
        return viewHolder;
    }
    //后绑定ViewHolder
    public void onBindViewHolder(final MyViewHolder holder, final int postion) {
        holder.tv.setText(mDatas.get(postion));//为tv控件设置文本值
        /*******3.2为itemView设置接口监听******/
        setItemEvent(holder);
    }
    /*3.5将监听回调事件写成方法*/
    protected void setItemEvent(final MyViewHolder holder)
    {
        if(mOnItemClickListener!=null)
        {
            //监听点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int layoutPostion=holder.getLayoutPosition();//更新item的位置
                    mOnItemClickListener.onItemClick(v,layoutPostion);
                }
            });
            //监听长按事件
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPostion=holder.getLayoutPosition();//更新item的位置
                    mOnItemClickListener.onItemLongClick(v,layoutPostion);
                    return false;
                }
            });
        }
    }
    /*1.4创建自定义的ViewHolder类*/
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        //根据item布局来设置控件
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            //加载tv控件
            tv= (TextView) itemView.findViewById(R.id.id_item_tv);
        }
    }
    /********3.0item的添加和删除方法*******/
    public void addData(int pos)
    {
        mDatas.add(pos,"Insert One");//别忘了添加pos,否则添加的数据会跑到尾部去
        notifyItemInserted(pos);
    }
    public void deleteData(int pos)
    {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }
}

