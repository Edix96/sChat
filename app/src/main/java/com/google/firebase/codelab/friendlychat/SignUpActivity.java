package com.google.firebase.codelab.friendlychat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.NoSuchAlgorithmException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailUser;
    private EditText nameUser;
    private EditText passwordEditText;
    //private Button signUpButton;
    private ProgressBar loadingProgressBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameUser = findViewById(R.id.name);
        emailUser = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loadingProgressBar = findViewById(R.id.loading);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signupbutton).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null);
        //por mais pra o login (handle the loigin user)
    }

    private void registerUser() {
        final String name = nameUser.getText().toString().trim();
        final String email = emailUser.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();


        if (name.isEmpty()){
            nameUser.setError("ERRo");
            nameUser.requestFocus();
            return;
        }

        loadingProgressBar.setVisibility(View.VISIBLE);



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loadingProgressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            final User user = new User(
                              name,
                              email
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    try {
                                        Thread.sleep(8000);
                                    if (task.isSuccessful()) {

                                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                User user1 = dataSnapshot.getValue(User.class);
                                                System.out.println(user1);

                                                if (!(user1.nonce == null && user1.nextnonce == null)) {
                                                    String s1 = password + user1.getNouce();
                                                    String s2 = password + user1.getNextnonce();

                                                    System.out.println(s1);
                                                    System.out.println(s2);

                                                    try {
                                                        String response = Sha256Base64.SHA256(s1);
                                                        String cnonce = Sha256Base64.SHA256(Sha256Base64.SHA256(s2));
                                                        System.out.print(response);
                                                        System.out.print(cnonce);

                                                        reference.child("response").setValue(response);
                                                        reference.child("cnonce").setValue(cnonce);

                                                    } catch (NoSuchAlgorithmException e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    return;
                                                }

                                                loadingProgressBar.setVisibility(View.GONE);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                // Mais funcoes...
                                            }
                                        });

                                        loadingProgressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, getString(R.string.signUp_Sucess), Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(SignUpActivity.this, getString(R.string.signUp_fail), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                } catch(InterruptedException e) {
                                    // Process exception
                                }
                                }
                                });
                        }else{
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupbutton:
                registerUser();
                break;
        }
    }
}

