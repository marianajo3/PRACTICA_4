package com.example.practicalab4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.practicalab4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Muestra InicioFragment solamente al abrir la aplicación.
        if (savedInstanceState == null) {
            mostrarFragment(new InicioFragment());
        }

        // Detecta qué opción del menú inferior presiona el usuario.
        binding.bottomNavigation.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.menuInicio) {

                mostrarFragment(new InicioFragment());
                return true;

            } else if (itemId == R.id.menuSensor) {

                mostrarFragment(new SensorFragment());
                return true;

            } else if (itemId == R.id.menuUsuarios) {

                mostrarFragment(new UsuariosFragment());
                return true;
            }

            return false;
        });
    }

    /**
     * Reemplaza el fragmento que aparece dentro del contenedor principal.
     */
    private void mostrarFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}