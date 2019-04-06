package com.example.fordeveloper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ARegisterActivity extends AppCompatActivity {

    private EditText et_phone;
    private EditText et_email;
    private EditText et_password;
    private Button btn_reg;
    private TextView tv_have_acc;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_phone = (EditText) findViewById(R.id.et_phone);
        et_email = (EditText) findViewById(R.id.et_email_main);
        et_password = (EditText) findViewById(R.id.et_password_main);
        btn_reg = (Button) findViewById(R.id.btn_reg_main);
        tv_have_acc = (TextView) findViewById(R.id.tv_have_acc_main);

        mAuth = FirebaseAuth.getInstance();

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration(et_email.getText().toString(), et_password.getText().toString(), et_phone.getText().toString());
            }
        });

        tv_have_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ARegisterActivity.this, BLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void registration(final String email, String password, final String phone){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ARegisterActivity.this,"Registered successfully. Please check your email for verification",Toast.LENGTH_SHORT).show();

                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).setValue(
                                        new UserInfo(email, phone, user.getUid()));

                                Intent intent = new Intent(ARegisterActivity.this, BLoginActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(ARegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(ARegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
