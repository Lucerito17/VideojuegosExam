package com.example.examen.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.Clases.Anime;
import com.example.examen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter  {

    List<Anime> item;

    public AnimeAdapter (List<Anime> item){
        this.item = item;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_contacto_anime, parent, false);

        AnimeAdapter.AnimeViewHolder viewHolder = new AnimeAdapter.AnimeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //declaro variables
        Anime anime = item.get(position);
        View view = holder.itemView;

        TextView nombre = view.findViewById(R.id.txtUsuario);
        TextView descripcion = view.findViewById(R.id.txtEmail);
        ImageView foto = view.findViewById(R.id.imgPrincipal);
        ImageView estrella = view.findViewById(R.id.btnFavorito);

        //mando los datos a las variable
        nombre.setText(anime.getNombre());
        descripcion.setText(anime.getNumero());
        Picasso.get().load(anime.getFoto()).into(foto);

        if(anime.getEstrella()){
            Picasso.get().load("https://static.vecteezy.com/system/resources/previews/001/189/165/original/star-png.png").into(estrella);
        }
        else {
            Picasso.get().load("https://cdn-icons-png.flaticon.com/512/16/16666.png").into(estrella);
        }

        estrella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(anime.getEstrella()){
                    anime.setEstrella(false);
                    Picasso.get().load("https://static.vecteezy.com/system/resources/previews/001/189/165/original/star-png.png").into(estrella);
                }
                else {
                    anime.setEstrella(true);
                    Picasso.get().load("https://cdn-icons-png.flaticon.com/512/16/16666.png").into(estrella);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        //regreso los datos, alias items
        return item.size();
    }


    public class AnimeViewHolder extends RecyclerView.ViewHolder{
        public AnimeViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
