package ru.btec.smr.myapplication.rest;


import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.btec.smr.myapplication.model.UserRest;
import ru.btec.smr.myapplication.model.ReposRest;


public interface Endpoints {
    @GET("/users/{user}")
    Observable<UserRest> getUser(@Path("user") String user);

    @GET("/users")
    Flowable<List<UserRest>> getUsers(@Query("since") long since, @Query("per_page") int per_page);

    @GET("/search/users")
    Flowable<List<UserRest>> getSearchUsers(@Query("q") String login, @Query("page") int page);
//    https://api.github.com/search/users?q=tom&page=34

    @GET("/users/{user}/repos")
    Flowable<List<ReposRest>> getRepos(@Path("user") String user);
}
