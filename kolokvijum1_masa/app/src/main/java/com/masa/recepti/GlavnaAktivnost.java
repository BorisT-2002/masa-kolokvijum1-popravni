package com.masa.recepti;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

public class GlavnaAktivnost extends AppCompatActivity {

    private static final int KOD_DOZVOLE_KAMERA = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivnost_glavna);

        Toolbar toolbar = findViewById(R.id.alatna_traka);
        setSupportActionBar(toolbar);

        zatraziteDozvoluKamere();

        startService(new Intent(this, ProvjerKamereServis.class));
    }

    private void zatraziteDozvoluKamere() {
        boolean nijeDozvoljenoJos = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;

        if (nijeDozvoljenoJos) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    KOD_DOZVOLE_KAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Servis automatski detektuje promenu dozvole
    }

    @Override
    public boolean onCreateOptionsMenu(Menu meni) {
        getMenuInflater().inflate(R.menu.meni_navigacija, meni);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem stavka) {
        if (stavka.getItemId() == R.id.stavka_recepti) {
            prikaziFragmentRecepata();
            return true;
        }
        return super.onOptionsItemSelected(stavka);
    }

    private void prikaziFragmentRecepata() {
        ReceptFragment fragment = new ReceptFragment();
        FragmentTransaction transakcija = getSupportFragmentManager().beginTransaction();
        transakcija.replace(R.id.okvir_za_fragment, fragment);
        transakcija.addToBackStack(null);
        transakcija.commit();
    }
}
