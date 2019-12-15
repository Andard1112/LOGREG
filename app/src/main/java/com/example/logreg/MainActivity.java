package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText felhasznalonev,jelszo;
    private Button bejelentkezes,regisztracio;
    AdatbazisSegito db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        db = new AdatbazisSegito(this);
        bejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String felhasznaloNev = felhasznalonev.getText().toString();
                String jelszO = jelszo.getText().toString();
                if (felhasznaloNev.isEmpty() || jelszO.isEmpty()){
                    Toast.makeText(MainActivity.this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Boolean felhnevjelszo = db.felhnevjelszo(felhasznaloNev,jelszO);
                if (felhnevjelszo){
                    Toast.makeText(MainActivity.this, "Sikeres bejelentkezés!", Toast.LENGTH_SHORT).show();
                    Intent logpage = new Intent(MainActivity.this,LoggedInActivity.class);
                    startActivity(logpage);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Hibás felhasználónév vagy email, vagy jelszó", Toast.LENGTH_SHORT).show();
                }


            }
        });
        regisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regre = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(regre);
                finish();
            }
        });

    }


    private void init(){
        felhasznalonev = findViewById(R.id.felhasznalonevMain);
        jelszo = findViewById(R.id.jelszoMain);
        bejelentkezes = findViewById(R.id.bejelentkezesMain);
        regisztracio = findViewById(R.id.regisztracioMain);

    }
}
