package com.srstextiles.m.sbank;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registerpage extends AppCompatActivity {

    TextView backlogin;
    Button register;
    EditText uname,uemail,upassword,reupassword,accno;
    String name,email,pass,acnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        backlogin=(TextView) findViewById(R.id.backblogin);
        register=(Button)findViewById(R.id.btregister);

        uname=(EditText)findViewById(R.id.username);
        uemail=(EditText)findViewById(R.id.useremail);
        upassword=(EditText)findViewById(R.id.password);
        reupassword=(EditText)findViewById(R.id.repassword);
        accno=(EditText)findViewById(R.id.accnum);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regsuc();
            }
        });
        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registerpage.this, login.class);
                startActivity(intent);
            }
        });
    }
    public void regsuc(){
        name=uname.getText().toString();
        email=uemail.getText().toString();
        pass=upassword.getText().toString();
        acnum=accno.getText().toString();
        if(name.equals("")||email.equals("")||pass.equals("")||acnum.equals("")) {

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
                            Intent register=new Intent(Registerpage.this,Registerpage.class);
                            Registerpage.this.startActivity(register);
                        }
                    })
                    .show();


        }
        else if(!pass.equals(reupassword.getText().toString())){
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    //set icon
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    //set title
                    .setTitle("Notification")
                    //set message
                    .setMessage("Password you entered didn't match....!")
                    //set positive button
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //set what would happen when positive button is clicked
                        }
                    })
                    .show();
        }
        else {
            String method ="register";
            BackgroundTask backgroundTask = new BackgroundTask((Context) this);
            backgroundTask.execute(method, name, email, pass,acnum);
        }
    }
}
