package com.example.acer.thingstodo;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText email;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            if(currentUser.isEmailVerified()) {
                Intent intent = new Intent(MainActivity.this, Signin.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(MainActivity.this, "Email is not Verified",
                        Toast.LENGTH_SHORT).show();
            }
        }
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    if(user.isEmailVerified())
                    {
                        Intent intent = new Intent(MainActivity.this, Signin.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Email is not Verified",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void SendVerificationEmail(View view)
    {
        SendVerification();
    }
    private void SendVerification(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        currentUser.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Email Sent",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void Register(View view){
        if(email.getText().toString().matches("") || password.getText().toString().matches(""))
        {
            Toast.makeText(MainActivity.this, "Enter both Email and Password",
                    Toast.LENGTH_SHORT).show();
        }
        else
           createAccount(email.getText().toString(),password.getText().toString());
    }
    public void Login(View view){
        if(email.getText().toString().matches("") || password.getText().toString().matches(""))
        {
            Toast.makeText(MainActivity.this, "Enter both Email and Password",
                    Toast.LENGTH_SHORT).show();
        }
        else {

            mAuth.signInWithEmailAndPassword(email.getText().toString(),
                    password.getText().toString()).addOnSuccessListener(this,
                    new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(MainActivity.this, "Authentication succeeded.",
                                        Toast.LENGTH_SHORT).show();
                            }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Failure:"+e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void createAccount(String email, String password) {
        Log.d("MyDebugMsg", "createAccount:" + email);
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(this,
                new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d("MyDebugMsg", "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(MainActivity.this, "Authentication succeeded.",
                        Toast.LENGTH_SHORT).show();
                SendVerification();
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("MyDebugMsg", "Failure:"+e.getMessage());
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(MainActivity.this, "Failure:"+e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void SendPassReset(View view){
        ResetPass();
    }
    private void ResetPass(){
          mAuth.sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Email Sent",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
