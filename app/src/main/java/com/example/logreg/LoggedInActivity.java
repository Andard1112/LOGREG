package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {
    private TextView felhasznalonev;
    private Button kijelentkezes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();
        kijelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visszamain = new Intent(LoggedInActivity.this,MainActivity.class);
                startActivity(visszamain);
                finish();
            }
        });
        //AdatbazisSegito db = new AdatbazisSegito(this);
        //felhasznalonev.setText(db.adatLekerdezes().toString());

    }
    private void init(){
        felhasznalonev = findViewById(R.id.felhasznalonevLog);
        kijelentkezes = findViewById(R.id.kijelentkezesLog);
    }
}
