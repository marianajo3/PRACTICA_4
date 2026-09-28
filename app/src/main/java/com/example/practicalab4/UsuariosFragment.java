package com.example.practicalab4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.practicalab4.databinding.FragmentUsuariosBinding;
import com.example.practicalab4.model.Usuario;
import com.example.practicalab4.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosFragment extends Fragment {

    private FragmentUsuariosBinding binding;
    private UsuarioAdapter usuarioAdapter;
    private Call<List<Usuario>> llamadaUsuarios;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentUsuariosBinding.inflate(
                inflater,
                container,
                false
        );

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        configurarRecyclerView();
        consultarUsuarios();
    }

    private void configurarRecyclerView() {

        usuarioAdapter = new UsuarioAdapter(usuario ->
                mostrarDetalleUsuario(usuario)
        );

        binding.recyclerViewUsuarios.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );

        binding.recyclerViewUsuarios.setAdapter(usuarioAdapter);
    }

    private void consultarUsuarios() {

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.textViewEstado.setText("Consultando usuarios...");

        llamadaUsuarios = RetrofitClient
                .getService()
                .obtenerUsuarios();

        llamadaUsuarios.enqueue(new Callback<List<Usuario>>() {

            @Override
            public void onResponse(
                    @NonNull Call<List<Usuario>> call,
                    @NonNull Response<List<Usuario>> response
            ) {
                if (binding == null) {
                    return;
                }

                binding.progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {

                    List<Usuario> usuarios = response.body();

                    usuarioAdapter.actualizarLista(usuarios);

                    binding.textViewEstado.setText(
                            usuarios.size() + " usuarios encontrados"
                    );

                } else {
                    binding.textViewEstado.setText(
                            "Error del servidor: " + response.code()
                    );
                }
            }

            @Override
            public void onFailure(
                    @NonNull Call<List<Usuario>> call,
                    @NonNull Throwable throwable
            ) {
                if (binding == null || call.isCanceled()) {
                    return;
                }

                binding.progressBar.setVisibility(View.GONE);
                binding.textViewEstado.setText(
                        "No se pudo conectar a Internet"
                );
            }
        });
    }

    private void mostrarDetalleUsuario(Usuario usuario) {

        new AlertDialog.Builder(requireContext())
                .setTitle(usuario.getName())
                .setMessage(
                        "ID: " + usuario.getId()
                                + "\nUsuario: " + usuario.getUsername()
                                + "\nCorreo: " + usuario.getEmail()
                )
                .setPositiveButton("Cerrar", null)
                .show();
    }

    @Override
    public void onDestroyView() {

        if (llamadaUsuarios != null) {
            llamadaUsuarios.cancel();
        }

        binding.recyclerViewUsuarios.setAdapter(null);
        binding = null;

        super.onDestroyView();
    }
}
