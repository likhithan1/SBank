package com.srstextiles.m.sbank;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AlertDialogLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private EditText uemail;
    private EditText password;
    private Button loginb;
    private TextView signupb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        int k=checkConnectivity();
        if(k==1) {
            setViews();
            signupb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    regpage();
                }
            });
            loginb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = uemail.getText().toString();
                    String pass = password.getText().toString();

                    valid(email, pass);
                }
            });
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    //set icon
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle("Notification")
                    //set message
                    .setMessage("Please Connect to internet(or Check your Internet Connection)....!")
                    //set positive button
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //set what would happen when positive button is clicked
                        }
                    })
                    .show();
        }
    }
    public int checkConnectivity(){
        ConnectivityManager cm=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        int i;
        if(networkInfo!=null && networkInfo.isConnected()){
                i=1;
        }
        else{
            i=0;
        }
        return i;
    }
    public void regpage(){
        Intent intent = new Intent(this, Registerpage.class);
        this.startActivity(intent);
    }
    private void setViews()
    {
        uemail = (EditText)findViewById(R.id.uemail);
        password = (EditText)findViewById(R.id.et_pass);
        loginb = (Button)findViewById(R.id.bt_login);
        signupb = (TextView)findViewById(R.id.tv_signup);
    }
    public void valid(String email, String password)
    {
        if(email.equals("")||password.equals("")) {

            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    //set icon
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle("Notification")
                    //set message
                    .setMessage("Please Fill The Details Well....!")
                    //set positive button
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //set what would happen when positive button is clicked
                            finish();
                            Intent register=new Intent(login.this,login.class);
                            login.this.startActivity(register);
                        }
                    })
                    .show();


        }
        else {
            String method = "login";
            BackgroundTask backgroundTask = new BackgroundTask((Context) this);
            backgroundTask.execute(method, email, password);
        }

    }


}
