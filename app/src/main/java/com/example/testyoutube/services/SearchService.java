package com.example.testyoutube.services;

import com.example.testyoutube.model.YoutubeVideoRS;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("search")
    Call<SearchListResponse> search(@Query("part") String part, @Query("q") String q,@Query("type") String type, @Query("maxResults") String maxResults, @Query("key") String key);

}
