package com.masa.recepti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReceptFragment extends Fragment {

    public static final String AKCIJA_NOVI_RECEPT = "com.masa.recepti.NOVI_RECEPT";
    public static final String AKCIJA_KAMERA_ODOBRENA = "com.masa.recepti.KAMERA_ODOBRENA";
    public static final String EXTRA_TRAJANJE = "EXTRA_TRAJANJE_PRIPREME";

    private ReceptAdapter receptAdapter;
    private ImageView slikaHrane;

    private final BroadcastReceiver kameraReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            prikaziSliku();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View koren = inflater.inflate(R.layout.fragment_recept, container, false);

        RecyclerView recyclerLista = koren.findViewById(R.id.lista_recepata);
        slikaHrane = koren.findViewById(R.id.slika_hrane);
        Button dugmeDodaj = koren.findViewById(R.id.dugme_dodaj);

        receptAdapter = new ReceptAdapter();
        recyclerLista.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerLista.setAdapter(receptAdapter);

        dugmeDodaj.setOnClickListener(v -> otvoriFormuZaDodavanje());

        return koren;
    }

    private void prikaziSliku() {
        if (slikaHrane != null) {
            slikaHrane.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(AKCIJA_KAMERA_ODOBRENA);
        LocalBroadcastManager.getInstance(requireContext())
                .registerReceiver(kameraReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(requireContext())
                .unregisterReceiver(kameraReceiver);
    }

    private void otvoriFormuZaDodavanje() {
        View formaView = LayoutInflater.from(requireContext())
                .inflate(R.layout.forma_dodavanje, null);

        AlertDialog dijalog = new AlertDialog.Builder(requireContext())
                .setView(formaView)
                .create();

        EditText unosNaziv = formaView.findViewById(R.id.unos_naziv);
        EditText unosTrajanje = formaView.findViewById(R.id.unos_trajanje);
        CheckBox cbOmiljeno = formaView.findViewById(R.id.cb_omiljeno);
        Button btnPotvrdi = formaView.findViewById(R.id.btn_potvrdi);
        Button btnOdustani = formaView.findViewById(R.id.btn_odustani);

        
        btnOdustani.setOnClickListener(view -> dijalog.dismiss());

        btnPotvrdi.setOnClickListener(view -> {
            String naziv = unosNaziv.getText().toString().trim();
            String trajanjeStr = unosTrajanje.getText().toString().trim();

           
            if (TextUtils.isEmpty(naziv) || TextUtils.isEmpty(trajanjeStr)) {
                Toast.makeText(requireContext(),
                        "Sva polja su obavezna!", Toast.LENGTH_SHORT).show();
                return;
            }

            int trajanje = Integer.parseInt(trajanjeStr);
            boolean jeOmiljeno = cbOmiljeno.isChecked();

            
            Recept noviRecept = new Recept(naziv, trajanje, jeOmiljeno);
            receptAdapter.dodajRecept(noviRecept);

            
            Intent broadcastIntent = new Intent(AKCIJA_NOVI_RECEPT);
            broadcastIntent.setPackage(requireContext().getPackageName());
            broadcastIntent.putExtra(EXTRA_TRAJANJE, trajanje);
            requireContext().sendBroadcast(broadcastIntent);

            dijalog.dismiss();
        });

        dijalog.show();
    }
}
