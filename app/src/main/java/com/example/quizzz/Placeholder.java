package com.example.quizzz;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Placeholder {
    @GET("pytania")
    public Call<List<Pytanie>> getPytania();
}
