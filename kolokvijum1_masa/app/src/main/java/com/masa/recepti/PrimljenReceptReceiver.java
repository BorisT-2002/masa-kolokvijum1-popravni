package com.masa.recepti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Receiver koji belezi svaki dodati recept i prati ukupno vreme kuvanja.
 * Kada ukupno vreme predje 120 minuta, korisniku se prikazuje upozorenje.
 */
public class PrimljenReceptReceiver extends BroadcastReceiver {

    private static final String IME_PREFERENCIJA = "EvidencijaKuvanja";
    private static final String KLJUC_UKUPNO = "ukupno_trajanje_min";
    private static final int GRANICA_MINUTA = 120;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) return;

        int novoTrajanje = intent.getIntExtra(ReceptFragment.EXTRA_TRAJANJE, 0);

        SharedPreferences prefs = context.getSharedPreferences(
                IME_PREFERENCIJA, Context.MODE_PRIVATE);

        int dosadaUkupno = prefs.getInt(KLJUC_UKUPNO, 0);
        int novoUkupno = dosadaUkupno + novoTrajanje;

        prefs.edit().putInt(KLJUC_UKUPNO, novoUkupno).apply();

        if (novoUkupno > GRANICA_MINUTA) {
            Toast.makeText(context, "Predugo kuvanje!", Toast.LENGTH_LONG).show();
        }
    }
}
