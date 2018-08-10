package com.example.marius.proiectocupatii;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Persoana> persoanaList = new ArrayList<>();

    public RecyclerAdapter(List<Persoana> persoanaList) {
        this.persoanaList = persoanaList;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( RecyclerAdapter.ViewHolder holder, int position) {
        holder.numeText.setText("Nume:      "+persoanaList.get(position).getNume());
        holder.adresaText.setText("Adresa:    "+persoanaList.get(position).getAdresa());
        holder.prenumeText.setText("Prenume:   "+persoanaList.get(position).getPrenume());
    }

    @Override
    public int getItemCount() {
        return persoanaList.size();
    }

    public void addInList(Persoana persoana)
    {
        persoanaList.add(persoana);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        public TextView numeText;
        public TextView prenumeText;
        public TextView adresaText;

        public ViewHolder(View itemView){
            super(itemView);
            numeText = (TextView)itemView.findViewById(R.id.nume_id);
            prenumeText = (TextView)itemView.findViewById(R.id.prenume_id);
            adresaText = (TextView)itemView.findViewById(R.id.adresa_id);
        }
    }
}
