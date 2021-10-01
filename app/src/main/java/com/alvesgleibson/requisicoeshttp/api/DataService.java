package com.alvesgleibson.requisicoeshttp.api;

import com.alvesgleibson.requisicoeshttp.model.ListaFoto;
import com.alvesgleibson.requisicoeshttp.model.Postagem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {

   @GET("/photos")
   Call<List<ListaFoto>>recuperarFotos();

    @GET("/posts")
    Call<List<Postagem>>recuperarPostagem();

    //Salvar Postagem usando JSON
    @POST("/posts")
    Call<Postagem>salvarPostagem(@Body Postagem postagem);

    //Salvar Postagem usando XML
    @FormUrlEncoded
    @POST("/posts")
    Call<Postagem>salvarPostagem(
            @Field("userId") Integer userId,
            @Field("title") String title,
            @Field("body") String body
    );

    //Atualizar Postagem
    @PUT("/posts/{id}")
    Call<Postagem> atualizarPostagem(@Path("id") int id, @Body Postagem postagem);

}
