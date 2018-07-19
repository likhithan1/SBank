package com.srstextiles.m.sbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.util.ResourceBundle.getBundle;

public class welcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView mainname1,mainaccno1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View nv = navigationView.getHeaderView(0);

        mainname1=(TextView)nv.findViewById(R.id.mainname);

        mainaccno1=(TextView)nv.findViewById(R.id.mainaccno);

        String msg=getIntent().getExtras().getString("ourresult");
        int i=0;
        char[] details=msg.toCharArray();
        while(details[i]!='\0'){
            if(details[i]=='@')
                break;
            i++;
        }
        String accnum=msg.substring(0,i);
        String name=msg.substring(i+1,msg.length());

        mainname1.setText(name);
        mainaccno1.setText(accnum);

        //Toast.makeText(this, name, Toast.LENGTH_LONG).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_accsum) {
            String method="accsum";
            BackgroundTask backgroundTask=new BackgroundTask((Context)this);
            backgroundTask.execute(method,accnum);
            // Handle the camera action
        } else if (id == R.id.nav_transfer) {

        } else if (id == R.id.nav_ministmt) {

        } else if (id == R.id.nav_accset) {

            Intent accset=new Intent(welcome.this,account_settings.class);
            Bundle bundle=new Bundle();
            bundle.putString("ouraccnum",accnum);
            accset.putExtras(bundle);
            welcome.this.startActivity(accset);

        }
        else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
