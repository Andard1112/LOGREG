package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private AdatbazisSegito myDb;
    private EditText emailReg,felhasznalonevReg,jelszoReg,teljesnevReg;
    private Button regisztracioReg,visszaReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        regisztracioReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailReg.getText().toString();
                String felhnev = felhasznalonevReg.getText().toString();
                String jelszo = jelszoReg.getText().toString();
                String teljesNev = teljesnevReg.getText().toString();
                if (email.isEmpty() || felhnev.isEmpty() || jelszo.isEmpty() || teljesNev.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Minden mezőt ki kell tőlteni!", Toast.LENGTH_SHORT).show();
                    return;
                }
                regisztracio(email,felhnev,jelszo,teljesNev);

            }
        });
        visszaReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visszaMain = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(visszaMain);
                finish();
            }
        });
    }
    private void regisztracio(String email,String felhnev,String jelszo,String teljesNev){
        AdatbazisSegito dbHelper = new AdatbazisSegito(RegisterActivity.this);
        if (dbHelper.regisztracio(email,felhnev,jelszo,teljesNev)){
            Boolean checkmail = dbHelper.checkemail(email);
            if (checkmail){
                Toast.makeText(this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
                Intent logpage = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(logpage);
                finish();
            }else{
                Toast.makeText(this, "Email már foglalt!", Toast.LENGTH_SHORT).show();
                emailReg.setText("");
            }
        }else{
            Toast.makeText(this, "Sikertelen regisztráció!", Toast.LENGTH_SHORT).show();
            emailReg.setText("");
            felhasznalonevReg.setText("");
            jelszoReg.setText("");
            teljesnevReg.setText("");
        }
    }

    private void init(){
        emailReg = findViewById(R.id.emailReg);
        felhasznalonevReg = findViewById(R.id.felhasznalonevReg);
        jelszoReg = findViewById(R.id.jelszoReg);
        teljesnevReg = findViewById(R.id.teljesnevReg);
        regisztracioReg = findViewById(R.id.regisztracioReg);
        visszaReg = findViewById(R.id.visszaReg);
    }
}
