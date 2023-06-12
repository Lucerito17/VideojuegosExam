package com.example.examen.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.EditarActivity;
import com.example.examen.InicioActivity;
import com.example.examen.PrincipalActivity;
import com.example.examen.R;
import com.example.examen.RetrofitActivity;
import com.example.examen.entities.Users;
import com.example.examen.services.UsersService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersAdapter extends RecyclerView.Adapter {
    private List<Users> items;

    public UsersAdapter(List<Users> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_vista_lista, parent, false);

        UserViewHolder viewHolder = new UserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //declaro variables
        Users users = items.get(position);
        View view = holder.itemView;

        TextView nombre = view.findViewById(R.id.txtUsuario);
        TextView email = view.findViewById(R.id.txtEmail);
        TextView username = view.findViewById(R.id.txtUsername);
        ImageView foto = view.findViewById(R.id.imgPrincipal);

        Button btnEditar = view.findViewById(R.id.btnEditar);
        Button btnEliminar = view.findViewById(R.id.btnEliminar);
        Button btnAtras = view.findViewById(R.id.btnRetroceder);

        //mando los datos a las variable
        nombre.setText(users.nombre);
        email.setText(users.email);
        username.setText(users.username);
        Picasso.get().load(users.foto).into(foto);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PrincipalActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditarActivity.class);
                intent.putExtra("identificador", users.ID);
                v.getContext().startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64781c33362560649a2d370d.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersService servicio = retrofit.create(UsersService.class);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> llamadoLista = servicio.EliminarContactos(users.ID);
                llamadoLista.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent = new Intent(v.getContext(), RetrofitActivity.class);
                        v.getContext().startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        //regreso los datos, alias items
        return items.size();
    }
    public class UserViewHolder extends RecyclerView.ViewHolder{
        public UserViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}