package ru.btec.smr.myapplication.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.realm.RealmList;
import ru.btec.smr.myapplication.model.DbRealm;
import ru.btec.smr.myapplication.model.User;
import ru.btec.smr.myapplication.model.UserRest;
import ru.btec.smr.myapplication.model.UserParent;
import ru.btec.smr.myapplication.rest.NetApiClient;

@InjectViewState
public class UsersPresenter extends MvpPresenter<UsersView> implements Subscriber<List<UserRest>> {
    private static final String TAG = "UsersPresenter";
    private long since = 0;
    public DbRealm dbRealm = new DbRealm();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void attachView(UsersView view) {
        super.attachView(view);
    }

    public RealmList<User> getItemList() {
        return dbRealm.realm.where(UserParent.class).findFirst().getItemList();
    }

    public void getData(long since, int per_page) {
        NetApiClient.getInstance().getUsers(since, per_page).subscribe(this);
    }

    public void getData(int per_page) {
        getData(this.since, per_page);
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
        Log.e(TAG, " onSubscribe");
    }

    @Override
    public void onNext(List<UserRest> itemRests) {
        dbRealm.addItemAsync(itemRests);
        this.since = itemRests.get(itemRests.size() - 1).getId();
        Log.e(TAG, " onNext size =" + itemRests.size());
    }

    @Override
    public void onError(Throwable t) {
        Log.e(TAG, " onError");
    }

    @Override
    public void onComplete() {
        Log.e(TAG, " onComplete");
    }
}
