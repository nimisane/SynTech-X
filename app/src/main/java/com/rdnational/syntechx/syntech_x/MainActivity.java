package com.rdnational.syntechx.syntech_x;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private DocumentReference policyref = database.document("PrivacyPolicy/privacy");
    String Key_What="policy";
    String pp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fagment_layout,new EventsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        policyref.addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(documentSnapshot.exists()){
                    pp = documentSnapshot.getString(Key_What);
                   // policy.setText(privacy_policy);
                }
            }
        });

    }
    android.support.v4.app.Fragment selectedFragment = null;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();

                    if (id == R.id.home) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fagment_layout,new EventsFragment()).commit();
                        navigationView.setCheckedItem(R.id.nav_home);
                    } else if (id == R.id.events) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fagment_layout,new GalleryFragment()).commit();
                        navigationView.setCheckedItem(R.id.nav_events);
                    } else if (id == R.id.committees) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fagment_layout,new CommitteesFragment()).commit();
                        navigationView.setCheckedItem(R.id.nav_committees);
                    } else if (id == R.id.about) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fagment_layout, new AboutFragment()).commit();
                        navigationView.setCheckedItem(R.id.nav_About);
                    }

                    return true;
                }
            };

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
        getMenuInflater().inflate(R.menu.privacy_policy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.privacy) {
            view_policy();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void view_policy(){
        AlertDialog.Builder alertadd = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
        final View view = factory.inflate(R.layout.privacy_policy, null);
        final TextView policy =  view.findViewById(R.id.policy);
        policy.setText(pp);
     //   Glide.with(this).load(eventHeadImg).into(image);
        alertadd.setView(view);
        alertadd.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
           getSupportFragmentManager().beginTransaction().replace(R.id.fagment_layout,new GalleryFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.home);
        } else if (id == R.id.nav_events) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fagment_layout,new EventsFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.events);
        } else if (id == R.id.nav_committees) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fagment_layout,new CommitteesFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.committees);
        } else if (id == R.id.nav_About) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fagment_layout,new AboutFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.about);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
