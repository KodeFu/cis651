package com.example.acer.thingstodo;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    DatabaseReference mRootReference= FirebaseDatabase.getInstance().getReference();
    DatabaseReference postsRef = mRootReference.child("posts");
    static List<Post> data=new ArrayList<Post>();
    Context contex;
    RecyclerView rv;
        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView uname;
        public TextView utext;
        public  TextView ptime;
        public Button heartButton;
        public ViewHolder(View view) {
            super(view);
            uname=(TextView)view.findViewById(R.id.user_name);
            utext=(TextView)view.findViewById(R.id.user_text);
            ptime=(TextView)view.findViewById(R.id.post_time);
            heartButton=(Button)view.findViewById(R.id.heart);

            uname.setOnClickListener(this);
            heartButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("mv", "onClick: onClick");
            if (view.getId() == heartButton.getId()) {
                //Toast.makeText(view.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                Toast.makeText(view.getContext(), "ITEM PRESSED = " + view.getResources().getResourceName(view.getId()), Toast.LENGTH_SHORT).show();

                int adapterPosition = getAdapterPosition();
                assfunc(adapterPosition);

            } else {
                Toast.makeText(view.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            //listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
    public RecyclerViewAdapter(Context _context){
        contex=_context;
        rv=(RecyclerView)((AppCompatActivity)contex).findViewById(R.id.recycler_view);
        postsRef.addChildEventListener(new ChildEventListener() {
            //s: The key name of sibling location ordered before the new child.
            //This will be null for the first child node of a location.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Post p=new Post();

                p.id=dataSnapshot.getKey().toString();
                p.un=dataSnapshot.child("author").getValue().toString();
                p.text=dataSnapshot.child("text").getValue().toString();
                SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String time=dataSnapshot.child("timestamp").getValue().toString();
                Long t=Long.parseLong(time);
                p.time=localDateFormat.format(new Date(t));
                data.add(p);
                rv.scrollToPosition(data.size()-1);
                RecyclerViewAdapter.this.notifyItemInserted(data.size()-1);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String id=dataSnapshot.getKey().toString();
                int position=-1;
                for (int i=0; i<data.size(); i++)
                {
                    if(data.get(i).id.equals(id)){
                        position=i;
                        break;
                    }
                }
                if(position!=-1)
                {
                    data.remove(position);
                    Post p=new Post();
                    p.id=dataSnapshot.getKey().toString();
                    p.un=dataSnapshot.child("author").getValue().toString();
                    p.text=dataSnapshot.child("text").getValue().toString();
                    SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String time=dataSnapshot.child("timestamp").getValue().toString();
                    Long t=Long.parseLong(time);
                    p.time=localDateFormat.format(new Date(t));
                    data.add(position,p);
                    rv.scrollToPosition(position);
                    RecyclerViewAdapter.this.notifyItemChanged(position);
                }
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String id=dataSnapshot.getKey().toString();
                int position=-1;
                for (int i=0; i<data.size(); i++)
                {
                    if(data.get(i).id.equals(id)){
                        position=i;
                        break;
                    }
                }
                if(position!=-1)
                {
                    data.remove(position);
                    rv.scrollToPosition(position);
                    RecyclerViewAdapter.this.notifyItemRemoved(position);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);
        return view_holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.uname.setText(data.get(position).un);
        holder.utext.setText(data.get(position).text);
        holder.ptime.setText(data.get(position).time);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    static public void assfunc(int i)
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance().getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Post post = data.get(i);

        DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference postsRef = mRootReference.child("posts");
        DatabaseReference newPostRef = postsRef.push();
        Map<String, Object> nodes = new HashMap<String, Object>();
        nodes.put(post.id + "/" + user.getUid().toString(), post.id);
        postsRef.updateChildren(nodes);

        //DatabaseReference likesRef = newPostRef.child("likes");
        //DatabaseReference likesRef = mRootReference.child("posts").child(post.id).child("likes");
        DatabaseReference likesRef = mRootReference.child("posts").child(post.id);
        //DatabaseReference newLikeRef = likesRef.push();
        Map<String, Object> likeNodes = new HashMap<String, Object>();
        //likeNodes.put(user.getUid(), "I like it!");

        likeNodes.put("likes" + "/extra", "extra info");

        likesRef.updateChildren(likeNodes);

        /*DatabaseReference likesRef = mRootReference.child("posts").child(user.getUid().toString()).child("likes");
        Map<String, Object> likesNodes = new HashMap<String, Object>();
        likesNodes.put(user.getUid().toString(), "i like it!");
        postsRef.updateChildren(likesNodes);*/
        /*if (i==0)
        {
            nodes.put("-LvG0sGTsIcYeRzWfXFA" + "/stuff", "zero");
        }
        else
        {
            nodes.put("-LvIPUQkVxicwZIY12qG" + "/stuff", "oneyo");
        }*/

    }

}
