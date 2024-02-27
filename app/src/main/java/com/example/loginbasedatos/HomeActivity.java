package com.example.loginbasedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    EditText genero, edad,cancion;
    Button enviarEncuesta;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        genero=findViewById(R.id.genero);
        edad=findViewById(R.id.edad);
        cancion=findViewById(R.id.cancion);
        enviarEncuesta=findViewById(R.id.enviarEncuesta);
        DB= new DBHelper(this);

        enviarEncuesta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String gene = genero.getText().toString();
                String edd = edad.getText().toString();
                String can = cancion.getText().toString();

                if (TextUtils.isEmpty(gene) || TextUtils.isEmpty(edd) || TextUtils.isEmpty(can)) {
                    Toast.makeText(HomeActivity.this, "Todos los campos están requeridos", Toast.LENGTH_SHORT).show();
                } else {
                    boolean inserted = DB.insertarInfoEncuesta(gene,edd,can);
                    if (inserted) {
                        Toast.makeText(HomeActivity.this, "Encuesta enviada correctamente", Toast.LENGTH_SHORT).show();
                    String nombreCancion=DB.consultarCancionExistente(can);
                        if(nombreCancion.equals("Halloween")){
                            Intent i= new Intent(getApplicationContext(),cancionHalloween.class);
                            startActivity(i);
                        } else if (nombreCancion.equals("Navidad")) {
                            Intent i= new Intent(getApplicationContext(),Navidad.class);
                            startActivity(i);
                        } else if (nombreCancion.equals("Amor y Amistad")) {
                            Intent i= new Intent(getApplicationContext(),cancionAmorYAmistad.class);
                            startActivity(i);
                        } else if (nombreCancion.equals("Padre")) {
                            Intent i= new Intent(getApplicationContext(),cancionBlackFriday.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(HomeActivity.this, "Esa canción no existe en la base datos", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(HomeActivity.this, "Error al enviar la encuesta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });}}



