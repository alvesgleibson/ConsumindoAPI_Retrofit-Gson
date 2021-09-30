package com.alvesgleibson.requisicoeshttp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.alvesgleibson.requisicoeshttp.api.CEPService;
import com.alvesgleibson.requisicoeshttp.model.Endereco;
import com.google.android.material.textfield.TextInputEditText;


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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.btRecuperar);
        txtResultado = findViewById(R.id.txtResultado);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recuperarCEPRetrofit();

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



}