package com.alvesgleibson.requisicoeshttp.api;

import com.alvesgleibson.requisicoeshttp.model.ListaFoto;
import com.alvesgleibson.requisicoeshttp.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

   @GET("/photos")
   Call<List<ListaFoto>>recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>>recuperarPostagem();

    @POST("/posts")
    Call<Postagem>salvarPostagem(@Body Postagem postagem);

}
