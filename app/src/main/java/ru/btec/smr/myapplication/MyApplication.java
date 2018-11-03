package ru.btec.smr.myapplication;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.btec.smr.myapplication.model.UserParent;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.createObject(UserParent.class);
                    }})
                .build();
        Realm.deleteRealm(realmConfig); // Delete Realm between app restarts.
        Realm.setDefaultConfiguration(realmConfig);
    }
}
