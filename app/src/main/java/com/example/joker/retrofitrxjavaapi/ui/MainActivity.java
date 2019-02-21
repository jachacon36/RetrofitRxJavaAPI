package com.example.joker.retrofitrxjavaapi.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joker.retrofitrxjavaapi.R;
import com.example.joker.retrofitrxjavaapi.model.adapter.PostAdapter;
import com.example.joker.retrofitrxjavaapi.model.api.PostAPI;
import com.example.joker.retrofitrxjavaapi.model.api.PostAPIRetrofit;
import com.example.joker.retrofitrxjavaapi.model.pojo.Post;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private PostAPI postAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init api
        Retrofit retrofit = PostAPIRetrofit.getInstance();
        postAPI = retrofit.create(PostAPI.class);
        fetchData();

    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    private void onCreateRecyclerView(List<Post> posts){

        PostAdapter adapter = new PostAdapter(this,posts);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void fetchData() {
        compositeDisposable.add(postAPI.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        onCreateRecyclerView(posts);
                    }
                }));

    }

}
