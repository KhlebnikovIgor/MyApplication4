package ru.btec.smr.myapplication.presenters;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import ru.btec.smr.myapplication.model.ReposRest;
import ru.btec.smr.myapplication.rest.NetApiClient;


@InjectViewState
public class ReposPresenter extends MvpPresenter<ReposView> implements Subscriber<List<ReposRest>> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void attachView(ReposView view) {
        super.attachView(view);
//        getViewState().startLoad();
//        loadData();
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(List<ReposRest> data) {
        getViewState().setReposList(data);
        Log.d("Dto", "size = " + data.size());
    }

    @Override
    public void onComplete() {
        getViewState().finishLoad();
    }

    @Override
    public void onError(Throwable t) {
        getViewState().showError(t);
        getViewState().finishLoad();
    }

    public void getReposList(String user) {
        getViewState().startLoad();

        NetApiClient.getInstance().getRepos(user).subscribe(this);
    }
}
