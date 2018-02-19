package com.example.kiran.superhero;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by kiran on 2/5/18.
 */

public class bloggerAPI {

    private  static final String key = "AIzaSyC_qRXU16m0jl5y8XJuugx2rnT1eZCX1ks";//for blooger api key ..making it constant

    private  static final String url = "https://www.googleapis.com/blogger/v3/blogs/5576973892366703665/posts/";
    //make sure we take url of json form only up to post not key too
    //it is our base url



    public static Postservice postservice = null;//to make sure that retrofit object is created only once

    public static Postservice getService() {


        if(postservice == null){

            //retrifut object is build
            Retrofit  retrofit = new Retrofit.Builder()
                    .baseUrl(url)//choode baseurl of string as we have string save  our url
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postservice = retrofit.create(Postservice.class);//creating our interface postservice maybe


        }
        return postservice;
    }



    public interface Postservice{
        //retrofit has the interface ...and implementation of those interface


        @GET("?key=" +key)//get method and ..it works like take the base url and then pass key to it to get all the post
        Call<Postlist> getpostlist();



    }


}
