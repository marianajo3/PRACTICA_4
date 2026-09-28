package com.example.practicalab4;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicalab4.databinding.ItemUsuarioBinding;
import com.example.practicalab4.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter que conecta los objetos Usuario
 * con las tarjetas item_usuario.xml.
 */
public class UsuarioAdapter
        extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    // Lista utilizada por el RecyclerView
    private final List<Usuario> listaUsuarios;

    // Listener para detectar qué usuario fue pulsado
    private final OnUsuarioClickListener listener;

    /**
     * Constructor del Adapter.
     */
    public UsuarioAdapter(OnUsuarioClickListener listener) {
        listaUsuarios = new ArrayList<>();
        this.listener = listener;
    }

    /**
     * Reemplaza los datos anteriores por una nueva lista.
     */
    public void actualizarLista(List<Usuario> nuevosUsuarios) {
        listaUsuarios.clear();
        listaUsuarios.addAll(nuevosUsuarios);

        // Informa al RecyclerView que debe actualizarse
        notifyDataSetChanged();
    }

    /**
     * Crea una nueva tarjeta desde item_usuario.xml.
     */
    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        ItemUsuarioBinding binding = ItemUsuarioBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new UsuarioViewHolder(binding);
    }

    /**
     * Coloca los datos de un usuario en una tarjeta.
     */
    @Override
    public void onBindViewHolder(
            @NonNull UsuarioViewHolder holder,
            int position
    ) {
        Usuario usuario = listaUsuarios.get(position);
        holder.enlazar(usuario);
    }

    /**
     * Devuelve la cantidad de usuarios.
     */
    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    /**
     * Representa una tarjeta individual del RecyclerView.
     */
    class UsuarioViewHolder extends RecyclerView.ViewHolder {

        private final ItemUsuarioBinding binding;

        public UsuarioViewHolder(ItemUsuarioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Muestra los datos del usuario dentro de la tarjeta.
         */
        public void enlazar(Usuario usuario) {

            binding.textViewNombre.setText(
                    usuario.getName()
            );

            binding.textViewUsername.setText(
                    "Usuario: " + usuario.getUsername()
            );

            binding.textViewCorreo.setText(
                    usuario.getEmail()
            );

            // Envía el usuario seleccionado al Fragment
            binding.getRoot().setOnClickListener(view ->
                    listener.onUsuarioClick(usuario)
            );
        }
    }

    /**
     * Interfaz para comunicar el clic al Fragment.
     */
    public interface OnUsuarioClickListener {
        void onUsuarioClick(Usuario usuario);
    }
}