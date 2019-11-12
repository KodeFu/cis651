package com.example.lab6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MyRecyclerAdapter
    extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<Contact> mContactList;
    private Context mContext;
    private RecyclerView myRecyclerView;
    public MyRecyclerAdapter(List<Contact> myDataset, Context context, RecyclerView recyclerView)
    {
        mContactList = myDataset;
        mContext = context;
        myRecyclerView = recyclerView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name_v;
        public TextView lastname_v;
        public TextView phone_v;
        public ViewHolder(View view) {
            super(view);
            name_v = (TextView)view.findViewById(R.id.name_view);
            lastname_v = (TextView) view.findViewById(R.id.lastname_view);
            phone_v = (TextView) view.findViewById(R.id.phone_number);
        }
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);
        return view_holder;
    }

    public void updateList(List<Contact> myDataset) {
        mContactList = myDataset;
        notifyDataSetChanged();
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Contact c = mContactList.get(position);
        holder.name_v.setText("Name: " + c.getName());
        holder.lastname_v.setText("Last Name: " + c.getLastname());
        holder.phone_v.setText("Phone: " + c.getPhone_number());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }


}
