package com.example.acer.thingstodo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.WeakReference;
import java.net.PasswordAuthentication;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapterWithImage extends
        RecyclerView.Adapter<RecyclerViewAdapterWithImage.ViewHolder> {
    DatabaseReference mRootReference= FirebaseDatabase.getInstance().getReference();
    DatabaseReference postsRef = mRootReference.child("posts");
    static List<Post> data=new ArrayList<Post>();
    Context contex;
    RecyclerView rv;
    private ClickListener listener = null;

    final static class WorkerDownloadImage extends AsyncTask<String, String, Bitmap > {
		private ClickListener listener = null;
        private final WeakReference<Context> parentRef;
        private final WeakReference<ImageView> imageViewRef;
        public  WorkerDownloadImage(final Context parent, final ImageView imageview)
        {
            parentRef=new WeakReference<Context>(parent);
            imageViewRef=new WeakReference<ImageView>(imageview);
        }
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap result= HTTP_METHODS.downloadImageUsingHTTPGetRequest(urls[0]);
            return result;
        }
        @Override
        protected void onPostExecute(final Bitmap result){
            final ImageView iv=imageViewRef.get();
            if(iv!=null)
            {
                iv.setImageBitmap(result);
            }
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView uname;
        public TextView utext;
        public  TextView ptime;
        public Button heartButton;
        public TextView likes;
        public ImageView imv;
        private WeakReference<ClickListener> listenerRef;
        public ViewHolder(View view, ClickListener listener) {
            super(view);
            listenerRef = new WeakReference<>(listener);
            uname=(TextView)view.findViewById(R.id.user_name);
            utext=(TextView)view.findViewById(R.id.user_text);
            ptime=(TextView)view.findViewById(R.id.post_time);
            imv=(ImageView)view.findViewById(R.id.img_view);

            heartButton=(Button) view.findViewById(R.id.heart);
            likes=(TextView)view.findViewById(R.id.likes);

            heartButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("mv", "onClick: onClick");
            if (view.getId() == heartButton.getId()) {
                //Toast.makeText(view.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                //Toast.makeText(view.getContext(), "ITEM PRESSED = " + view.getResources().getResourceName(view.getId()), Toast.LENGTH_SHORT).show();

                int adapterPosition = getAdapterPosition();

                Button theViewButton = (Button) view;
                if (theViewButton.getText()=="NOT LIKED") {
                    theViewButton.setText("LIKED");
                    updateLikes(adapterPosition, true);
                }
                else
                {
                    theViewButton.setText("NOT LIKED");
                    updateLikes(adapterPosition, false);
                }

                listenerRef.get().onButtonClicked(adapterPosition);

            }
        }
    }
    public RecyclerViewAdapterWithImage(Context _context, ClickListener listener){
        contex=_context;
        this.listener = listener;
        rv=(RecyclerView)((AppCompatActivity)contex).findViewById(R.id.recycler_view);
        postsRef.addChildEventListener(new ChildEventListener() {
            //s: The key name of sibling location ordered before the new child.
            //This will be null for the first child node of a location.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                boolean isLiked = false;
                int likesCount = 0;

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String child = ds.getKey();
                    //Log.d("debug", child);
                    Log.d("mvdebug", child);
                    if (child.equals("likes")) {
                        Log.d("mvdebug", "Got Likes Yo!!!!");
                        for (DataSnapshot likesChildren : ds.getChildren() )
                        {
                            Log.d("mvdebug", likesChildren.getKey());
                            String likedUser = likesChildren.getKey();

                            FirebaseAuth mAuth = FirebaseAuth.getInstance().getInstance();
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (likedUser.equals(user.getUid()))
                            {
                                Log.d("mvdebug", "Egads! Got current user");

                                if (likesChildren.getValue().equals("yes")) {
                                    Log.d("mvdebug", "Egads! Current user likes this thing!");
                                    isLiked = true;
                                }
                            }

                            // count
                            if (likesChildren.getValue().equals("yes")) {
                                likesCount++;
                            }
                        }
                    }
                    //Log.d("debug", ds.getValue(String.class));
                }


                Post p=new Post();
                p.id=dataSnapshot.getKey().toString();
                p.un=dataSnapshot.child("author").getValue().toString();
                p.text=dataSnapshot.child("text").getValue().toString();
                SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String time=dataSnapshot.child("timestamp").getValue().toString();
                Long t=Long.parseLong(time);
                p.time=localDateFormat.format(new Date(t));
				
				
                if (isLiked) {
                    p.liked = true;
                }
                else
                {
                    p.liked = false;
                }
                p.likesCount = likesCount;
				
                if(dataSnapshot.hasChild("link"))
                    p.link=dataSnapshot.child("link").getValue().toString();
                else
                    p.link=null;
                data.add(p);
                rv.scrollToPosition(data.size()-1);
                RecyclerViewAdapterWithImage.this.notifyItemInserted(data.size()-1);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    boolean isLiked = false;
                    int likesCount = 0;

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String child = ds.getKey();
                        //Log.d("debug", child);
                        Log.d("mvdebug", child);
                        if (child.equals("likes")) {
                            Log.d("mvdebug", "Got Likes Yo!!!!");
                            for (DataSnapshot likesChildren : ds.getChildren() )
                            {
                                Log.d("mvdebug", likesChildren.getKey());
                                String likedUser = likesChildren.getKey();

                                FirebaseAuth mAuth = FirebaseAuth.getInstance().getInstance();
                                FirebaseUser user = mAuth.getCurrentUser();

                                if (likedUser.equals(user.getUid()))
                                {
                                    Log.d("mvdebug", "Egads! Got current user");

                                    if (likesChildren.getValue().equals("yes")) {
                                        Log.d("mvdebug", "Egads! Current user likes this thing!");
                                        isLiked = true;
                                    }
                                }

                                // count
                                if (likesChildren.getValue().equals("yes")) {
                                    likesCount++;
                                }
                            }
                        }
                        //Log.d("debug", ds.getValue(String.class));
                    }

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
					
					if (isLiked) {
                        p.liked = true;
                    }
                    else
                    {
                        p.liked = false;
                    }
                    p.likesCount = likesCount;
					
                    if(dataSnapshot.hasChild("link"))
                        p.link=dataSnapshot.child("link").getValue().toString();
                    else
                        p.link=null;
                    data.add(position,p);
                    rv.scrollToPosition(position);
                    RecyclerViewAdapterWithImage.this.notifyItemChanged(position);
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
                    RecyclerViewAdapterWithImage.this.notifyItemRemoved(position);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_with_image, parent, false);
        final ViewHolder view_holder = new ViewHolder(v, listener);
        return view_holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.uname.setText(data.get(position).un);
        holder.utext.setText(data.get(position).text);
        holder.ptime.setText(data.get(position).time);
        holder.heartButton.setSelected(data.get(position).liked);
        String linesCountString = Integer.toString(data.get(position).likesCount);
        holder.likes.setText(linesCountString);
        if (data.get(position).liked) {
            holder.heartButton.setText("LIKED");
        }
        else
        {
            holder.heartButton.setText("NOT LIKED");
        }
        if(data.get(position).link!=null)
        new WorkerDownloadImage(contex,holder.imv).execute(data.get(position).link);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    static public void updateLikes(int i, boolean isSelected)
    {
        FirebaseAuth mAuth = FirebaseAuth.getInstance().getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        Post post = data.get(i);

        DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference likesRef = mRootReference.child("posts").child(post.id);

        Map<String, Object> likeNodes = new HashMap<String, Object>();

        if (isSelected) {
            likeNodes.put("likes" + "/" + user.getUid(), "yes");
        }
        else
        {
            likeNodes.put("likes" + "/" + user.getUid(), "no");
        }

        likesRef.updateChildren(likeNodes);
    }
}
