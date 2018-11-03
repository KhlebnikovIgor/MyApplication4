package ru.btec.smr.myapplication.model;


import java.util.List;

import io.realm.Realm;

public class DbRealm {
    public Realm realm = Realm.getDefaultInstance();;




    public  void addItemAsync( final List<UserRest> itemRests) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User.create(realm, itemRests);
            }
        });
    }


}
