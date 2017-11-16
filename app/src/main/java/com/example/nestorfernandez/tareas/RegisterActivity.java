package com.example.nestorfernandez.tareas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import DBHELPER.sqlite;


public class RegisterActivity extends AppCompatActivity {

    private EditText nombre, email, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button RegisterButton = (Button) findViewById(R.id.ID_reg_button);
        RegisterButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
            SingIn();
        }
        });

        nombre = (EditText) findViewById(R.id.ID_reg_name);
        email = (EditText) findViewById(R.id.ID_reg_email);
        password = (EditText) findViewById(R.id.ID_reg_pass);
        password2 = (EditText) findViewById(R.id.ID_reg_pass2);

    }

    protected void SingIn(){
        //Añadir a la base de datos
        if (comprobarCampos()) {
            String nom, ema, pass;
            nom=nombre.getText().toString();
            ema=email.getText().toString();
            pass=password.getText().toString();

            sqlite db=new sqlite(this,"usuarios",null,1);
            if(db!=null){
                SQLiteDatabase data=db.getWritableDatabase();
                ContentValues con=new ContentValues();
                con.put("nombre",nom);
                con.put("email",ema);
                con.put("password",pass);
                long insertado=data.insert("usuarios",null,con);

                if(insertado>0){
                    Toast.makeText(this,"Insertado con éxito "+String.valueOf(insertado),Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    email.setText("");
                    password.setText("");

                    Intent intent = new Intent();
                    intent.putExtra("email",ema);
                    setResult(RESULT_OK,intent);
                    finish();

                }else{
                    Toast.makeText(this,"Error al insertar "+String.valueOf(insertado),Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"db es null",Toast.LENGTH_SHORT).show();
            }

        } else {
            //Toast.makeText(this, "Hay campos vacios fuera de funcion", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean comprobarCampos() {
        if (nombre.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || password2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if(password.getText().toString().equals(password2.getText().toString())){
                return true;
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

}
