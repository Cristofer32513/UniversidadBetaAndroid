package com.example.colectau_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MenuActivity  extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView mensajeBienvenida;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        extras= getIntent().getExtras();

        setToolBar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        mensajeBienvenida = headerLayout.findViewById(R.id.msj_bienvenida);
        mensajeBienvenida.setText("Bienvenido " + extras.getString("nombre_usuario"));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_donativos:
                        Toast.makeText(getApplicationContext(), "Donativos", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.nav_usuarios:
                        Toast.makeText(getApplicationContext(), "Usuarios", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.nav_salir:
                        Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                    default:
                        return false;
                }
            }
        });



    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
