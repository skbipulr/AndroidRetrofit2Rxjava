package com.example.bipul.androidretrofit2rxjava.Retrofit;



import com.example.bipul.androidretrofit2rxjava.Model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


 public interface IMyAPI {

    @GET("posts")
    Observable<List<Post>> getPosts();

}
