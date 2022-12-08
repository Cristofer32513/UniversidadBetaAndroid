package com.example.colectau_beta;

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

public class MenuActivity  extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView mensajeBienvenida, mensajeCorreo;
    private CircleImageView imagen;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        extras= getIntent().getExtras();

        setToolBar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) setupDrawerContent(navigationView);
        if (savedInstanceState == null) {
            // Enviar título como arguemento del fragmento
            Bundle args = new Bundle();
            args.putString(PlaceholderFragmentHome.ARG_SECTION_TITLE, "Colecta Universidad Beta");
            Fragment fragment = PlaceholderFragmentHome.newInstance("Colecta Universidad Beta");
            selectItem("Colecta Universidad Beta", fragment, args);
        }
        View headerLayout = navigationView.getHeaderView(0);
        mensajeBienvenida = headerLayout.findViewById(R.id.msj_bienvenida);
        mensajeCorreo = headerLayout.findViewById(R.id.msj_email);
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

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    String title = menuItem.getTitle().toString();
                    // Enviar título como arguemento del fragmento
                    Bundle args = new Bundle();
                    Fragment fragment;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
                            args.putString(PlaceholderFragmentHome.ARG_SECTION_TITLE, title);
                            fragment = PlaceholderFragmentHome.newInstance(title);
                            selectItem("Colecta Universidad Beta", fragment, args);
                            return true;
                        case R.id.nav_donativos:
                            Toast.makeText(getApplicationContext(), "Donativos", Toast.LENGTH_LONG).show();
                            args.putString(PlaceholderFragment.ARG_SECTION_TITLE, title);
                            fragment = PlaceholderFragment.newInstance(title);
                            selectItem(title, fragment, args);
                            return true;
                        case R.id.nav_usuarios:
                            Toast.makeText(getApplicationContext(), "Usuarios", Toast.LENGTH_LONG).show();
                            args.putString(PlaceholderFragment.ARG_SECTION_TITLE, title);
                            fragment = PlaceholderFragment.newInstance(title);
                            selectItem(title, fragment, args);
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
            }
        );
    }

    private void selectItem(String title, Fragment fragment, Bundle args) {
        fragment.setArguments(args);
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
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
