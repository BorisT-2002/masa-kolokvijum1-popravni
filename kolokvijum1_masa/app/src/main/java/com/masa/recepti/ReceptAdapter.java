package com.masa.recepti;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReceptAdapter extends RecyclerView.Adapter<ReceptAdapter.ReceptHolder> {

    private final List<Recept> listaRecepata = new ArrayList<>();

    public void dodajRecept(Recept noviRecept) {
        listaRecepata.add(noviRecept);
        notifyItemInserted(listaRecepata.size() - 1);
    }

    @NonNull
    @Override
    public ReceptHolder onCreateViewHolder(@NonNull ViewGroup roditelj, int tipPogleda) {
        View prikaz = LayoutInflater.from(roditelj.getContext())
                .inflate(R.layout.stavka_recept, roditelj, false);
        return new ReceptHolder(prikaz);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceptHolder holder, int pozicija) {
        Recept tekuci = listaRecepata.get(pozicija);
        holder.tvNaziv.setText(tekuci.getNazivRecepta());
        holder.tvTrajanje.setText(tekuci.getTrajanjePripreme() + " min");
        holder.tvOmiljeno.setVisibility(tekuci.isOmiljeno() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return listaRecepata.size();
    }

    static class ReceptHolder extends RecyclerView.ViewHolder {
        TextView tvNaziv;
        TextView tvTrajanje;
        TextView tvOmiljeno;

        ReceptHolder(@NonNull View itemView) {
            super(itemView);
            tvNaziv = itemView.findViewById(R.id.tv_naziv_recepta);
            tvTrajanje = itemView.findViewById(R.id.tv_trajanje);
            tvOmiljeno = itemView.findViewById(R.id.tv_omiljeno_znak);
        }
    }
}
