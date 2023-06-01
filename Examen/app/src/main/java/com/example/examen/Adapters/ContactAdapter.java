package com.example.examen.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.Clases.Contacto;
import com.example.examen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter {

    List<Contacto> item;

    public ContactAdapter (List<Contacto> item){
        this.item = item;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_contacto, parent, false);

        ContactViewHolder viewHolder = new ContactViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        //declaro variables
        Contacto contacto = item.get(position);
        View view = holder.itemView;

        TextView nombre = view.findViewById(R.id.txtUsuario);
        TextView numero = view.findViewById(R.id.txtEmail);
        ImageView foto = view.findViewById(R.id.imgPrincipal);
        Button llamada = view.findViewById(R.id.btnEditar);

        //mando los datos a las variable
        nombre.setText(contacto.getNombre());
        numero.setText(contacto.getNumero());
        Picasso.get().load(contacto.getFoto()).into(foto);

        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + contacto.getNumero()));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //regreso los datos, alias items
        return item.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public ContactViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
