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

public class account_settings extends AppCompatActivity {
    Button btupdatepass;
    EditText epass,reepass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        btupdatepass=(Button)findViewById(R.id.bt_update);
        epass=(EditText)findViewById(R.id.epass);
        reepass=(EditText)findViewById(R.id.reepass);
        btupdatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }
    public void update(){
        String password=epass.getText().toString();
        String repassword=reepass.getText().toString();
        if(password.equals("")||repassword.equals("")||(!password.equals(repassword))) {

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
                        }
                    })
                    .show();


        }
        else {
            String method = "updatepass";
            BackgroundTask backgroundTask = new BackgroundTask((Context) this);
            backgroundTask.execute(method, password, repassword);
        }
    }
}
