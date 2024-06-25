package com.example.project1.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        if(user==null){
            Intent myInt=new Intent(MainActivity.this, user_login.class);
            startActivity(myInt);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new cse()).commit();
            navigationView.setCheckedItem((R.id.nav_cse));

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.nav_cse) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new cse()).commit();
        } else if (itemId == R.id.nav_aiml) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new aiml()).commit();
        } else if (itemId == R.id.nav_ise) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new dse_ise()).commit();
        }
        else if (itemId == R.id.nav_mech) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new mech()).commit();
        }
        else if (itemId == R.id.nav_ece) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ece()).commit();
        }else if (itemId == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Logged out!!", Toast.LENGTH_SHORT).show();
            Intent myInt=new Intent(MainActivity.this, user_login.class);
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

