package com.example.joker.retrofitrxjavaapi.model.api;

import com.example.joker.retrofitrxjavaapi.model.pojo.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PostAPI {
    @GET("posts")
    Observable<List<Post>> getPosts();
}
