package com.example.loginbasedatos;

import giandroidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        EditText usuario,contraseña,confirmacion;
        Button registrarse,iniciarSesion;
        DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario=findViewById(R.id.usuario);
        contraseña=findViewById(R.id.contraseña);
        confirmacion=findViewById(R.id.confirmacion);
        registrarse=findViewById(R.id.registrarse);
       iniciarSesion=findViewById(R.id.iniciarSesion);
        DB= new DBHelper(this);


        registrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String usu= usuario.getText().toString();
                String contra= contraseña.getText().toString();
                String confi= confirmacion.getText().toString();


                if(usu.isEmpty()  || contra.isEmpty()  || confi.isEmpty())
                    Toast.makeText(MainActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                else{
                    if (contra.equals(confi)){
                        Boolean checkuser = DB.checkUsername(usu);
                        if (checkuser==false){
                            Boolean insert = DB.insertarInfo(usu,contra);
                            if (insert==true){
                                Toast.makeText(MainActivity.this, "Registered Succesfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User already Exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        iniciarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });



    }
}