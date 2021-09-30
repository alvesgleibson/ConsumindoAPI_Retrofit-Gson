package com.alvesgleibson.requisicoeshttp.api;

import com.alvesgleibson.requisicoeshttp.model.Endereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{cep}/json/")
    Call<Endereco> recuperarCEP(@Path("cep") String cep);


}
