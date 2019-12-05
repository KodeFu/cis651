package com.example.acer.thingstodo;

import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Signin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email_text;
    EditText name_text;
    Button email_update_button;
    Button name_update_button;
    Button signout;
    TextView greetings;
    EditText pass_text;
    Button set_pass_button;
    FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email_text=(EditText) findViewById(R.id.email_edittext);
        name_text=(EditText) findViewById(R.id.dname_edittext);
        email_update_button=(Button)findViewById(R.id.email_update_button);
        name_update_button=(Button) findViewById(R.id.dname_update_button);
        greetings=(TextView)findViewById(R.id.greeting);
        signout=(Button)findViewById(R.id.logout);
         pass_text=(EditText)findViewById(R.id.password_edittext);
        set_pass_button=(Button)findViewById(R.id.pass_set_button);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        try{
        if(currentUser!=null)
        {
            greetings.setText("Hello "+currentUser.getDisplayName()+"!");
        }}
        catch (Exception ex)
        {
            Log.d("Exeption",ex.getMessage().toString());
        }
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                finish();
            }
        });
        email_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.updateEmail(email_text.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Signin.this,"Success",Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Signin.this,e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        name_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null) {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name_text.getText().toString())
                            .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Signin.this,"Success",Toast.LENGTH_SHORT).show();
                                        greetings.setText("Hello "+currentUser.getDisplayName()+"!");
                                    }
                                }
                            });
                }
            }
        });
        set_pass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String newPassword = pass_text.getText().toString();
                user.updatePassword(newPassword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Signin.this,"Password Updated",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    public void DBDemo(View view){
        Intent intent=new Intent(this, FirebaseDBActivity.class);
        startActivity(intent);
    }
}
