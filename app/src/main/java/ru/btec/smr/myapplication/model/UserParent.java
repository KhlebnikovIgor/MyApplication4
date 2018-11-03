package ru.btec.smr.myapplication.model;

import io.realm.RealmList;
import io.realm.RealmObject;


public class UserParent extends RealmObject {
    @SuppressWarnings("unused")
    private RealmList<User> itemList;

    public RealmList<User> getItemList() {
        return itemList;
    }
}
