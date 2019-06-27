package com.efsalokyay.sozlukuygulama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetayActivity extends AppCompatActivity {

    private TextView ingilizce_text, turkce_text;

    private Kelimeler kelime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        turkce_text = findViewById(R.id.detay_turkce_text);
        ingilizce_text = findViewById(R.id.detay_ingilizce_text);

        kelime = (Kelimeler) getIntent().getSerializableExtra("nesne");
        turkce_text.setText(kelime.getTurkce());
        ingilizce_text.setText(kelime.getIngilizce());
    }
}
