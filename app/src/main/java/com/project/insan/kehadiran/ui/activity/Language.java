package com.project.insan.kehadiran.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;

import com.project.insan.kehadiran.R;

import java.util.Locale;

public class Language extends AppCompatActivity {
    RadioButton rb_english,rb_spains, rb_bahasa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rb_english = findViewById(R.id.rb_english);
        rb_spains = findViewById(R.id.rb_spains);
        rb_bahasa = findViewById(R.id.rb_bahasa);

        rb_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale("en-rUS");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getResources().updateConfiguration(config,getResources().getDisplayMetrics());
                getResources().updateConfiguration(config,getResources().getDisplayMetrics());
                Intent intent = new Intent(Language.this, MainActivity.class);
                startActivity(intent);
            }
        });

        rb_spains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale locale = new Locale("es");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;

                getResources().updateConfiguration(config,getResources().getDisplayMetrics());
                Intent intent = new Intent(Language.this, MainActivity.class);
                startActivity(intent);
            }
        });

        rb_bahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale locale = new Locale("in");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;

                getResources().updateConfiguration(config,getResources().getDisplayMetrics());
                Intent intent = new Intent(Language.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
