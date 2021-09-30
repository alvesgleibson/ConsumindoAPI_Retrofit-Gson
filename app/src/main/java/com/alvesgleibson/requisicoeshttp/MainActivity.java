package com.alvesgleibson.requisicoeshttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alvesgleibson.requisicoeshttp.model.Endereco;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView txtResultado;
    private TextInputEditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.btRecuperar);
        txtResultado = findViewById(R.id.txtResultado);
        editText = findViewById(R.id.imputText);


        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyTask task = new MyTask();
                String cep = editText.getText().toString();
                //String urlApi = "https://blockchain.info/ticker";
                String urlCEP = "https://viacep.com.br/ws/"+cep+"/json/";
                task.execute(urlCEP);

            }
        });

    }

    class MyTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream =  null;
            InputStreamReader streamReader = null;
            StringBuffer buffer = null;
            try {
                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                // Recupera os dados em Bytes
               inputStream =  conexao.getInputStream();

               streamReader = new InputStreamReader(inputStream);
               BufferedReader reader = new BufferedReader(streamReader);
               buffer = new StringBuffer();
               String linha = "";
               while ((linha = reader.readLine()) != null){
                   buffer.append(linha);
               }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            //String valorRealBit =  null;
            //String sifraoReal = null;
            Endereco endereco = null;

            try {

                /*
                String valorObjeto = null;

                JSONObject jsonObject = new JSONObject(s);
                valorObjeto = jsonObject.getString("BRL");

                JSONObject jsonObjectReal = new JSONObject(valorObjeto);
                valorRealBit = jsonObjectReal.getString("last");
                sifraoReal = jsonObjectReal.getString("symbol") + " "+ valorRealBit;
                */


                JSONObject jsonObject = new JSONObject(s);
                String rua, cep, bairro,uf, cidade;
                rua = jsonObject.getString("logradouro");
                cep = jsonObject.getString("cep");
                bairro = jsonObject.getString("bairro");
                uf = jsonObject.getString("uf");
                cidade = jsonObject.getString("localidade");
                endereco = new Endereco(rua,cep,bairro,uf, cidade);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            txtResultado.setText(endereco.toString());
        }
    }
}