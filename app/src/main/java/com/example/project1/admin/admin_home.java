package com.example.project1.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.admin.delete_notices.ad_delete_notice;
import com.example.project1.User.home;
import com.google.android.material.navigation.NavigationView;

public class admin_home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout1);
        NavigationView navigationView = findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ad_upload_notices()).commit();
            navigationView.setCheckedItem((R.id.nav_cse));

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.up_notice) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ad_upload_notices()).commit();
        } else if (itemId == R.id.del_notice) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ad_delete_notice()).commit();
        }else if (itemId == R.id.nav_logout) {
            Toast.makeText(this, "Logged out!!", Toast.LENGTH_SHORT).show();
            Intent myInt=new Intent(admin_home.this, home.class);
            startActivity(myInt);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return  true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

}