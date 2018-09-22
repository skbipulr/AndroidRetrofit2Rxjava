package com.example.bipul.androidretrofit2rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bipul.androidretrofit2rxjava.Adapter.PostAdapter;
import com.example.bipul.androidretrofit2rxjava.Model.Post;
import com.example.bipul.androidretrofit2rxjava.Retrofit.IMyAPI;
import com.example.bipul.androidretrofit2rxjava.Retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    IMyAPI myAPIl;
    RecyclerView recycler_posts;
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init API
        Retrofit retrofit=RetrofitClient.getInstance();
        myAPIl=retrofit.create(IMyAPI.class);

        //View
        recycler_posts=findViewById(R.id.recycler_posts);
        recycler_posts.setHasFixedSize(true);
        recycler_posts.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {

        compositeDisposable.add(myAPIl.getPosts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Post>>() {
            @Override
            public void accept(List<Post> posts) throws Exception {
                    displayData(posts);
            }
        }));
    }


    //All Method
    private void displayData(List<Post> posts) {
        PostAdapter adapter=new PostAdapter(this,posts);
        recycler_posts.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
