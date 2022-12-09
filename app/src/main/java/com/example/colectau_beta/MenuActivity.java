package com.example.colectau_beta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.navigation.NavigationView;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import donacion.FragmentDonaciones;
import usuario.FragmentUsuarios;

public class MenuActivity  extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private CircleImageView imagen;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle extras = getIntent().getExtras();

        setToolBar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) setupDrawerContent(navigationView);
        if (savedInstanceState == null) {
            // Enviar título como arguemento del fragmento
            Fragment fragment = new PlaceholderFragmentHome();
            selectItem("Colecta Universidad Beta", fragment);
        }
        assert navigationView != null;
        View headerLayout = navigationView.getHeaderView(0);
        TextView mensajeBienvenida = headerLayout.findViewById(R.id.msj_bienvenida);
        TextView mensajeCorreo = headerLayout.findViewById(R.id.msj_email);
        imagen = headerLayout.findViewById(R.id.imagen_circular);
        mensajeBienvenida.setText("Bienvenido " + extras.getString("nombre_usuario"));
        mensajeCorreo.setText("un_correo_recibido@gmail.com");
        seleccionarImagenAleatoria();
    }

    private void seleccionarImagenAleatoria() {
        switch (new Random().nextInt(5)) {
            case 0:
                imagen.setImageResource(R.drawable.perfil1);
                break;
            case 1:
                imagen.setImageResource(R.drawable.perfil2);
                break;
            case 2:
                imagen.setImageResource(R.drawable.perfil3);
                break;
            case 3:
                imagen.setImageResource(R.drawable.perfil4);
                break;
            default:
                imagen.setImageResource(R.drawable.perfil5);
                break;
        }
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_home);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    @SuppressLint("NonConstantResourceId")
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            String title = menuItem.getTitle().toString();
            // Enviar título como arguemento del fragmento
            Fragment fragment;

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    fragment = new PlaceholderFragmentHome();
                    selectItem("Colecta Universidad Beta", fragment);
                    return true;
                case R.id.nav_donativos:
                    fragment = new FragmentDonaciones();
                    selectItem(title, fragment);
                    return true;
                case R.id.nav_usuarios:
                    fragment = new FragmentUsuarios();
                    selectItem(title, fragment);
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
        );
    }

    private void selectItem(String title, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        drawerLayout.closeDrawers(); // Cerrar drawer
        setTitle(title); // Setear título actual
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
