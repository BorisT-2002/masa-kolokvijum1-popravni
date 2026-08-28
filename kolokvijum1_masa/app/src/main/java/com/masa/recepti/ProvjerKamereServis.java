package com.masa.recepti;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * Servis koji periodicno (jednom u minutu) proverava
 * da li korisnik ima dozvolu za kameru.
 * Ako dozvola postoji, obavestavuje fragment putem LocalBroadcast-a.
 */
public class ProvjerKamereServis extends Service {

    private static final long INTERVAL_PROVERE = 60_000L;

    private Handler periodicniHandler;
    private Runnable zadatakProvere;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        periodicniHandler = new Handler(Looper.getMainLooper());

        zadatakProvere = new Runnable() {
            @Override
            public void run() {
                izvrsiProveru();
                periodicniHandler.postDelayed(this, INTERVAL_PROVERE);
            }
        };

        // Prva provera odmah pri pokretanju
        periodicniHandler.post(zadatakProvere);

        return START_STICKY;
    }

    private void izvrsiProveru() {
        int statusDozvole = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        boolean kameraDozvoljenaJe = (statusDozvole == PackageManager.PERMISSION_GRANTED);

        if (kameraDozvoljenaJe) {
            Intent obavestenje = new Intent(ReceptFragment.AKCIJA_KAMERA_ODOBRENA);
            LocalBroadcastManager.getInstance(this).sendBroadcast(obavestenje);
        }
    }

    @Override
    public void onDestroy() {
        if (periodicniHandler != null) {
            periodicniHandler.removeCallbacks(zadatakProvere);
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
