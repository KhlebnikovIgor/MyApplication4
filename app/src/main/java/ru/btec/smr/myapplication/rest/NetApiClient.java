package ru.btec.smr.myapplication.rest;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.btec.smr.myapplication.model.UserRest;
import ru.btec.smr.myapplication.model.ReposRest;


public class NetApiClient {

    private static final NetApiClient ourInstance = new NetApiClient();

    public static NetApiClient getInstance() {
        return ourInstance;
    }

    private Endpoints netApi = new ServiceGenerator().createService(Endpoints.class);

    private NetApiClient() {
    }

    public io.reactivex.Observable<UserRest> getUser(String user) {
        return netApi.getUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<UserRest>> getUsers(long since, int per_page) {
        return netApi.getUsers(since, per_page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<ReposRest>> getRepos(String user) {
        return netApi.getRepos(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
