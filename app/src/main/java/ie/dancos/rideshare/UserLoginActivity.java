package ie.dancos.rideshare;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserLoginActivity extends AppCompatActivity {

    private EditText Email, Password;
    private Button Login, Registration;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //get user
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(UserLoginActivity.this,RegistrationDoneActivity.class);
                    startActivity(intent);
                    //finish();
                    return;

                }
            }
        };






        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        Login = findViewById(R.id.login);
        Registration = findViewById(R.id.registration);


        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get email and password
                final String email = Email.getText().toString();
                final String password = Password.getText().toString();
                // creating the Auth user using email and password
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(UserLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(UserLoginActivity.this, "A sign up error has occurred",Toast.LENGTH_LONG);
                        }
                        else {
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("cabs").child("customers").child(user_id);
                            current_user_db.setValue(true);
                        }
                    }
                });


            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get email and password
                final String email = Email.getText().toString();
                final String password = Password.getText().toString();
                // creating the Auth user using email and password
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(UserLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(UserLoginActivity.this, "A sign in error has occurred",Toast.LENGTH_LONG);
                        }
                    }
                });


            }
        });




    }

 // start the listener
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }


    @Override
    protected void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

}
