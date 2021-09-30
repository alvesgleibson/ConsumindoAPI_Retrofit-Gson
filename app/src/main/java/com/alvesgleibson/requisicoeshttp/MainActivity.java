package com.alvesgleibson.requisicoeshttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.alvesgleibson.requisicoeshttp.api.CEPService;
import com.alvesgleibson.requisicoeshttp.api.DataService;
import com.alvesgleibson.requisicoeshttp.model.Endereco;
import com.alvesgleibson.requisicoeshttp.model.ListaFoto;
import com.alvesgleibson.requisicoeshttp.model.Postagem;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView txtResultado;
    private TextInputEditText editText;
    private Retrofit retrofit;
    private List<ListaFoto> listaFotos = new ArrayList<>();
    private List<Postagem> listaPostagens = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.btRecuperar);
        txtResultado = findViewById(R.id.txtResultado);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recuperarPostagemRetrofit();

            }
        });

    }

    public void recuperarCEPRetrofit(){

        editText = findViewById(R.id.imputText);
        String CEP = editText.getText().toString();


        CEPService service = retrofit.create(CEPService.class);
        Call<Endereco> call = service.recuperarCEP(CEP);

        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {

                txtResultado.setText(response.body().toString());

            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {

            }
        });

    }

    public void recuperarFotosRetrofit(){

        DataService service = retrofit.create(DataService.class);
        Call<List<ListaFoto>> call = service.recuperarFotos();

        call.enqueue(new Callback<List<ListaFoto>>() {

            @Override
            public void onResponse(Call<List<ListaFoto>> call, Response<List<ListaFoto>> response) {

                //Lista já setada(Muito pratico)
                listaFotos = response.body();

                for (int i =0; i < listaFotos.size(); i++){
                    Log.d("FotosAPI", "FotosRetrofit: "+listaFotos.get( i ));
                }

            }

            @Override
            public void onFailure(Call<List<ListaFoto>> call, Throwable t) {

            }
        });

    }

    public void recuperarPostagemRetrofit(){
        DataService service = retrofit.create(DataService.class);
        Call<List<Postagem>> call = service.recuperarPostagem();

        call.enqueue(new Callback<List<Postagem>>() {
            @Override
            public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                listaPostagens = response.body();

                for (int i =0; i< listaPostagens.size(); i++){

                    Log.d(" Postagem ", "ListaPostagens: "+listaPostagens.get( i ));

                }


            }

            @Override
            public void onFailure(Call<List<Postagem>> call, Throwable t) {

            }
        });


    }



}