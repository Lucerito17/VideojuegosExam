package com.example.examen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.examen.entities.Users;
import com.example.examen.services.UsersService;

import java.io.ByteArrayOutputStream;
import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InicioActivity extends AppCompatActivity {
    TextView  txtURL;
    double longitud;
    double latitud;
    public LocationManager locationManager;
    private static final int OPEN_CAMERA_REQUEST = 1001;
    private static final int OPEN_GALLERY_REQUEST = 1002;
    private static final String urlFotoApi= "https://demo-upn.bit2bittest.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        EditText etNombre = findViewById(R.id.etNombreInicio);
        //EditText etEmail = findViewById(R.id.etEmailInicio);
        //EditText etUsername = findViewById(R.id.etUsernameInicio);
        txtURL = findViewById(R.id.txtURL);

        Button btnActualizar = findViewById(R.id.btnCrear);
        Button btnVerLista = findViewById(R.id.btnVerLista);
        Button btnOpenCamera = findViewById(R.id.btnCamara);
        Button btnGaleria = findViewById(R.id.btnGaleria);
        Button btnMapa = findViewById(R.id.btnMapa);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64781c33362560649a2d370d.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersService servicio = retrofit.create(UsersService.class);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordenadas();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users user = new Users();
                //if (etEmail.length() == 3)
                //{
                    //nombre pokemon
                    user.nombre = etNombre.getText().toString();
                    user.longitud = longitud;
                    user.latitud = latitud;
                    //numero pokemon
                    //user.email = etEmail.getText().toString();
                    //tipo pokemon
                    //user.username = etUsername.getText().toString();
                    //imagen pokemon
                    /*String baseUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/";
                    String imageUrl = baseUrl + user.email + ".png";
                    user.foto = imageUrl;*/
                    if(!txtURL.equals(""))
                        user.camaraFoto = txtURL.getText().toString();

                    Call<Void> actualizar = servicio.CrearContactos(user);
                    actualizar.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()){
                                Log.i("MAIN_APP", "Se  creó");
                                Intent intent = new Intent(v.getContext(), RetrofitActivity.class);
                                v.getContext().startActivity(intent);
                            };
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.i("MAIN_APP", "No sirve");
                        }
                    });
                    Intent intent = new Intent(v.getContext(), RetrofitActivity.class);
                    v.getContext().startActivity(intent);
                //}
                /*else{
                    Log.i("MAIN_APP", "No tiene datos completos");
                    Intent intent = new Intent(v.getContext(), InicioActivity.class);
                    v.getContext().startActivity(intent);
                }*/
            }
        });

        btnVerLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RetrofitActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOpenCamera();
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
                else {
                    String[] permissions = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, 2000);
                }
            }
        });
    }

    void coordenadas(){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    double latitud2 = location.getLatitude();
                    double longitud2 = location.getLongitude();
                    latitud = latitud2;
                    longitud = longitud2;
                    Log.i("MAIN_APP", "Latitud" + latitud);
                    Log.i("MAIN_APP", "Longitud" + longitud);
                    locationManager.removeUpdates(this);
                }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, locationListener);
        }
        else{
            String[] permissions = new String[] {Manifest.permission.ACCESS_FINE_LOCATION};
            Log.i("MAIN_APP", "No hay permisos pa esta webada");
            requestPermissions(permissions, 1000);
        }
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_GALLERY_REQUEST);
    }

    private void handleOpenCamera() {
        if(checkSelfPermission(Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED)
        {
            // abrir camara
            Log.i("MAIN_APP", "Tiene permisos para abrir la camara");
            abrirCamara();
        } else {
            // solicitar el permiso
            Log.i("MAIN_APP", "No tiene permisos para abrir la camara, solicitando");
            String[] permissions = new String[] {Manifest.permission.CAMERA};
            requestPermissions(permissions, 1000);
        }
    }
    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.i("MAIN_APP", "Abriendo cámara");
        startActivityForResult(intent, OPEN_CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == OPEN_CAMERA_REQUEST && resultCode == RESULT_OK){
            Bitmap imagenBitmap = (Bitmap) data.getExtras().get("data");

            if (imagenBitmap != null){
                String imagenBase64 = convertirBitmapB64(imagenBitmap);
                UsersService.ImageToSave imageToSave = new UsersService.ImageToSave(imagenBase64);

                enviarImageApi(imageToSave);
            }
        }
        if(requestCode==OPEN_GALLERY_REQUEST & resultCode==RESULT_OK){
            Uri image = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(image, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close(); // close cursor

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);

            //Convertir a base64
            String imagenBase64 = convertirBitmapB64(bitmap);
            UsersService.ImageToSave imageToSave = new UsersService.ImageToSave(imagenBase64);
            enviarImageApi(imageToSave);
        }
    }

    String convertirBitmapB64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    void enviarImageApi(UsersService.ImageToSave imageToSave){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlFotoApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersService service = retrofit.create(UsersService.class);

        Call<UsersService.ImagenResponse> call = service.SubirImagen(imageToSave);
        call.enqueue(new Callback<UsersService.ImagenResponse>() {
            @Override
            public void onResponse(Call<UsersService.ImagenResponse> call, Response<UsersService.ImagenResponse> response) {
                if(response.isSuccessful()){
                    txtURL.setText(urlFotoApi + response.body().getUrl());
                    Log.i("MAIN_APP", urlFotoApi+response.body().getUrl());
                }else
                    Log.i("MAIN_APP", "No se subió");
            }

            @Override
            public void onFailure(Call<UsersService.ImagenResponse> call, Throwable t) {
                Log.i("MAIN_APP", "La petición falló");
            }
        });
    }
}