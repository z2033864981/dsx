package com.example.dsx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyHolder> {
    private ArrayList<Bean.DataBean>list;
    private Context context;

    public RecAdapter(ArrayList<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        holder.tv.setText(list.get(position).getName());
        final boolean select = list.get(position).isSelect();
        if (select){
            holder.cb.setChecked(true);
        }else {
            holder.cb.setChecked(false);
        }
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checked = holder.cb.isChecked();
                click.clickItem(position,checked);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    interface Click{
        void clickItem(int pos,boolean b);
    }
    Click click;

    public void setClick(Click click) {
        this.click = click;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CheckBox cb;
        TextView tv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cb=itemView.findViewById(R.id.cb);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}
