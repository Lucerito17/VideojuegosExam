package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.examen.Adapters.AnimeAdapter;
import com.example.examen.Adapters.ContactAdapter;
import com.example.examen.Clases.Anime;
import com.example.examen.Clases.Contacto;

import java.util.ArrayList;
import java.util.List;

public class RegistroActivity extends AppCompatActivity {

    List<Anime> listaAnimes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        AnimeAdapter adapter = new AnimeAdapter(data());

        //envio mi adaptador a la vista principal
        RecyclerView rvListDatos = findViewById(R.id.rvListaDatos);
        rvListDatos.setLayoutManager(new LinearLayoutManager(this));
        rvListDatos.setAdapter(adapter);
    }

    private List<Anime> data(){
        List<Anime> listaAnimes = new ArrayList<>();

        //registro datos
        Anime uno = new Anime("KOE NO KATACHI", "Anime romantico", "https://m.media-amazon.com/images/I/61g6vrwMyuL._AC_UF1000,1000_QL80_.jpg",false);
        Anime dos = new Anime("ELFEN LIEF", "Anime sangriento", "https://honeysanime.com/wp-content/uploads/2017/08/Elfen-Lied-dvd-1.jpg",false);
        Anime tres = new Anime("SHIGATSU WA KIMINO USO", "Anime triste", "https://w0.peakpx.com/wallpaper/737/940/HD-wallpaper-triste-lluvia-anime-sad.jpg",false);
        Anime cuatro = new Anime("NARUTO", "Anime para niños", "https://i.pinimg.com/originals/23/a6/53/23a6532ec0ef5a48332bcb3884db84e2.jpg",false);
        Anime cinco = new Anime("DGB Z", "Anime para publico en general", "https://www.crunchyroll.com/imgsrv/display/thumbnail/480x720/catalog/crunchyroll/35e4ac6339f5fdcc164160a5755790cd.jpe",false);

        //agrego datos a la lista
        listaAnimes.add(uno);
        listaAnimes.add(dos);
        listaAnimes.add(tres);
        listaAnimes.add(cuatro);
        listaAnimes.add(cinco);

        //regreso contactos
        return listaAnimes;
    }
}