package com.example.practicalab4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.practicalab4.databinding.FragmentInicioBinding;

/**
 * Fragment que muestra la pantalla inicial de la aplicación.
 */
public class InicioFragment extends Fragment {

    // Permite acceder a los elementos de fragment_inicio.xml
    private FragmentInicioBinding binding;

    /**
     * Se ejecuta cuando Android necesita crear
     * la interfaz visual del Fragment.
     */
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Convierte fragment_inicio.xml en objetos Java
        binding = FragmentInicioBinding.inflate(
                inflater,
                container,
                false
        );

        // Devuelve la vista principal del Fragment
        return binding.getRoot();
    }

    /**
     * Se ejecuta después de que la vista del Fragment
     * fue creada completamente.
     */
    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        // Aquí colocaríamos eventos o configuraciones adicionales.
    }

    /**
     * Se ejecuta cuando la interfaz del Fragment
     * deja de existir.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        /*
         * Libera el binding para evitar conservar
         * referencias a una vista que ya fue destruida.
         */
        binding = null;
    }
}