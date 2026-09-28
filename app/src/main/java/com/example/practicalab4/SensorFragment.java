package com.example.practicalab4;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.practicalab4.databinding.FragmentSensorBinding;

import java.util.Locale;

/**
 * Fragment que lee y procesa los datos del acelerómetro.
 */
public class SensorFragment extends Fragment
        implements SensorEventListener {

    // Binding generado desde fragment_sensor.xml
    private FragmentSensorBinding binding;

    // Administrador de sensores del dispositivo
    private SensorManager sensorManager;

    // Representa específicamente al acelerómetro
    private Sensor acelerometro;

    /**
     * Crea la interfaz del Fragment.
     */
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSensorBinding.inflate(
                inflater,
                container,
                false
        );

        return binding.getRoot();
    }

    /**
     * Se ejecuta cuando la vista ya fue creada.
     */
    @Override
    public void onViewCreated(
            @NonNull View view,
            Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        // Obtiene el servicio del sistema encargado de los sensores
        sensorManager = (SensorManager) requireContext()
                .getSystemService(Context.SENSOR_SERVICE);

        // Busca el acelerómetro del dispositivo
        acelerometro = sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER
        );

        // Verifica si el dispositivo cuenta con acelerómetro
        if (acelerometro == null) {
            binding.textViewEstadoSensor.setText(
                    "Este dispositivo no tiene acelerómetro"
            );
        }
    }

    /**
     * Se ejecuta cuando el Fragment vuelve a estar visible.
     */
    @Override
    public void onResume() {
        super.onResume();

        /*
         * Registra el listener para comenzar a recibir
         * valores del acelerómetro.
         */
        if (acelerometro != null) {
            sensorManager.registerListener(
                    this,
                    acelerometro,
                    SensorManager.SENSOR_DELAY_NORMAL
            );
        }
    }

    /**
     * Se ejecuta cuando el Fragment deja de estar activo.
     */
    @Override
    public void onPause() {
        super.onPause();

        /*
         * Deja de escuchar el sensor para evitar consumir
         * batería cuando la pantalla no está siendo utilizada.
         */
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    /**
     * Se ejecuta cada vez que el acelerómetro entrega
     * nuevos valores.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {

        // Evita trabajar si la vista ya fue destruida
        if (binding == null) {
            return;
        }

        // Verifica que el evento pertenezca al acelerómetro
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            // Obtiene la aceleración en los tres ejes
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            /*
             * Calcula la magnitud total mediante:
             * raíz cuadrada de x² + y² + z².
             */
            double magnitud = Math.sqrt(
                    x * x + y * y + z * z
            );

            // Muestra los valores con dos decimales
            binding.textViewX.setText(
                    String.format(
                            Locale.getDefault(),
                            "Eje X: %.2f m/s²",
                            x
                    )
            );

            binding.textViewY.setText(
                    String.format(
                            Locale.getDefault(),
                            "Eje Y: %.2f m/s²",
                            y
                    )
            );

            binding.textViewZ.setText(
                    String.format(
                            Locale.getDefault(),
                            "Eje Z: %.2f m/s²",
                            z
                    )
            );

            binding.textViewMagnitud.setText(
                    String.format(
                            Locale.getDefault(),
                            "Magnitud: %.2f m/s²",
                            magnitud
                    )
            );

            /*
             * Cuando el teléfono está quieto, la magnitud
             * es aproximadamente igual a la gravedad: 9.81.
             *
             * Si la diferencia supera 2.0, consideramos
             * que existe movimiento.
             */
            double diferenciaGravedad = Math.abs(
                    magnitud - SensorManager.GRAVITY_EARTH
            );

            if (diferenciaGravedad > 2.0) {
                binding.textViewEstadoSensor.setText(
                        "Movimiento detectado"
                );
            } else {
                binding.textViewEstadoSensor.setText(
                        "Dispositivo estable"
                );
            }
        }
    }

    /**
     * Android ejecuta este método si cambia
     * la precisión del sensor.
     */
    @Override
    public void onAccuracyChanged(
            Sensor sensor,
            int accuracy
    ) {
        // No necesitamos realizar ninguna acción.
    }

    /**
     * Libera el binding cuando se destruye la vista.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}