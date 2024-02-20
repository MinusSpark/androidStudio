package com.example.loginbasedatos;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usuario1, contraseña1;
    Button iniciarSesion1;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario1=findViewById(R.id.usuario1);
        contraseña1=findViewById(R.id.contraseña1);
        iniciarSesion1=findViewById(R.id.iniciarSesion1);
        DB = new DBHelper(this);
        iniciarSesion1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String usu=usuario1.getText().toString();
                String contra=contraseña1.getText().toString();

                if(TextUtils.isEmpty(usu) || TextUtils.isEmpty(contra)){
                    Toast.makeText(LoginActivity.this, "Todos los campos estan requeridop", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuserpass=DB.checkUsernamePassword(usu,contra);
                    if (checkuserpass){
                        Toast.makeText(LoginActivity.this,"Inicio de Sesion correctamente", Toast.LENGTH_SHORT).show();
                        Intent i= new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(LoginActivity.this,"Inicio de Sesion Fallido",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}