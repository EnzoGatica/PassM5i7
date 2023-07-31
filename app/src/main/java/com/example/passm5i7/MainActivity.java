package com.example.passm5i7;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.passm5i7.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IViewPresenter {
    private ActivityMainBinding binding;
    private Presentador presentador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presentador = new Presentador(this);
        binding.txtTitulo.setText("¿Qué tan segura es tu contraseña?");

        //Para que se evalue mientras se escribe.
        binding.editPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presentador.evaluarClave(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void showWeak() {
        binding.txtColors.setBackgroundColor(Color.RED);
        binding.txtColors.setText("Débil");
        binding.btnEnter.setOnClickListener(v -> {
            Toast.makeText(this, "Su contraseña es insegura", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void showMedium() {
        binding.txtColors.setBackgroundColor(Color.YELLOW);
        binding.txtColors.setText("Medio");
        binding.btnEnter.setOnClickListener(v -> {
            Toast.makeText(this, "Su contraseña es medianamente segura", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void showStrong() {
        binding.txtColors.setBackgroundColor(Color.GREEN);
        binding.txtColors.setText("Fuerte");
        binding.btnEnter.setOnClickListener(v -> {
            Toast.makeText(this, "Su contraseña es segura", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void showError() {
        binding.txtColors.setBackgroundColor(Color.RED);
        binding.txtColors.setText("Error");
    }
}