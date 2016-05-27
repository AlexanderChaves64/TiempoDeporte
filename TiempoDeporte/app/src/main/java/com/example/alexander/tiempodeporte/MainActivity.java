package com.example.alexander.tiempodeporte;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bISesion = (Button) findViewById(R.id.btnIniciarSesion);
        final Button bRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        final Button bCrono = (Button) findViewById(R.id.bCronometro);

        bISesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iniciarSesion = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(iniciarSesion);

            }
        });

        bRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Registrarse = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(Registrarse);

            }
        });

        bCrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cronometro = new Intent(MainActivity.this, CronometroActivity.class);
                MainActivity.this.startActivity(cronometro);
            }
        });

    }
}
