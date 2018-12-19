package com.example.dummy.anand;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    Button btnLogout;
    String readerName,readerCity,readerPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView date=(TextView)findViewById(R.id.date);

        Intent getData=getIntent();
        readerName=getData.getStringExtra("Name");
        readerCity=getData.getStringExtra("City");
        readerPhone=getData.getStringExtra("Phone");

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        SimpleDateFormat sdf=new SimpleDateFormat("EEEE, d MMM yyyy");
        String currentDate=sdf.format(new Date());
        date.setText(""+currentDate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_paper) {
            Intent i=new Intent(MainActivity.this,Paper.class);
            startActivity(i);
        } else if (id == R.id.nav_magazine) {
            Intent i=new Intent(MainActivity.this,Magaz.class);
            startActivity(i);
        } else if (id == R.id.nav_galary) {
            Intent i=new Intent(MainActivity.this,Galary.class);
            startActivity(i);
        } else if (id == R.id.nav_login) {
            session.logoutUser();
        } else if (id == R.id.nav_feeds) {
            Intent intent=new Intent(MainActivity.this,Feedback.class);
            intent.putExtra("Name",readerName);
            intent.putExtra("City",readerCity);
            intent.putExtra("Phone",readerPhone);
            startActivity(intent);
        }
        else if (id == R.id.nav_contact) {
            Intent i=new Intent(MainActivity.this,Contact.class);
            startActivity(i);
        }else if (id == R.id.nav_share) {
            Intent shareIntent=new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");

            startActivity(Intent.createChooser(shareIntent,"Share Us Via"));
        }else if (id == R.id.nav_about) {
            Intent i=new Intent(MainActivity.this,About.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
