package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.examen.Adapters.ContactAdapter;
import com.example.examen.Clases.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ListaContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contacto);

        ContactAdapter adapter = new ContactAdapter(data());

        //envio mi adaptador a la vista principal
        RecyclerView rvListContactos = findViewById(R.id.rvListaResgistro);
        rvListContactos.setLayoutManager(new LinearLayoutManager(this));
        rvListContactos.setAdapter(adapter);
    }

    private List<Contacto> data(){
        List<Contacto> listaContacto = new ArrayList<>();

        //registro datos
        Contacto uno = new Contacto("KOE NO KATACHI", "945621205", "https://m.media-amazon.com/images/I/61g6vrwMyuL._AC_UF1000,1000_QL80_.jpg");
        Contacto dos = new Contacto("ELFEN LIEF", "852426510", "https://honeysanime.com/wp-content/uploads/2017/08/Elfen-Lied-dvd-1.jpg");
        Contacto tres = new Contacto("SHIGATSU WA KIMINO USO", "985654145", "https://w0.peakpx.com/wallpaper/737/940/HD-wallpaper-triste-lluvia-anime-sad.jpg");
        Contacto cuatro = new Contacto("NARUTO", "989520021", "https://i.pinimg.com/originals/23/a6/53/23a6532ec0ef5a48332bcb3884db84e2.jpg");
        Contacto cinco = new Contacto("DGB Z", "902145741", "https://www.crunchyroll.com/imgsrv/display/thumbnail/480x720/catalog/crunchyroll/35e4ac6339f5fdcc164160a5755790cd.jpe");

        //agrego datos a la lista
        listaContacto.add(uno);
        listaContacto.add(dos);
        listaContacto.add(tres);
        listaContacto.add(cuatro);
        listaContacto.add(cinco);

        //regreso contactos
        return listaContacto;
    }
}