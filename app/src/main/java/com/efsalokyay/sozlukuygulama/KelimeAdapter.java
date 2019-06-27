package com.efsalokyay.sozlukuygulama;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class KelimeAdapter extends RecyclerView.Adapter<KelimeAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Kelimeler> kelimelerListe;

    public KelimeAdapter(Context mContext, List<Kelimeler> kelimelerListe) {
        this.mContext = mContext;
        this.kelimelerListe = kelimelerListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_sozluk, viewGroup, false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu cardTasarimTutucu, int i) {

        final Kelimeler kelime = kelimelerListe.get(i);

        cardTasarimTutucu.ingilizce_text.setText(kelime.getIngilizce());
        cardTasarimTutucu.turkce_text.setText(kelime.getTurkce());

        cardTasarimTutucu.kelime_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetayActivity.class);
                intent.putExtra("nesne", kelime);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return kelimelerListe.size();
    }


    public class CardTasarimTutucu extends RecyclerView.ViewHolder {

        private TextView ingilizce_text, turkce_text;
        private CardView kelime_card;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            kelime_card = itemView.findViewById(R.id.kelime_card);
            ingilizce_text = itemView.findViewById(R.id.ingilizce_text);
            turkce_text = itemView.findViewById(R.id.turkce_text);
        }
    }
}
