package com.sn.trainxtrain.model;
import com.sn.trainxtrain.Comment;
import com.sn.trainxtrain.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getPosts(
            @QueryMap Map<String, String> parameters
    );

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);

    @GET("api/records/1.0/search")
    Call<TrainResult> getResult(
            @Query("dataset") String dataset,
            @Query("sort") String sort,
            @Query("rows") String rows,
            @Query("refine.od_happy_card") String tgvMaxFriendly,
            @Query("refine.destination") String destination,
            @Query("refine.origine") String origine

    );
}
