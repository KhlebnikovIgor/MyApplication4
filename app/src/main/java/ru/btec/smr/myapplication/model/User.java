package ru.btec.smr.myapplication.model;


import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    private static final String TAG = "Item";
    @PrimaryKey
    private int id;
    private String login;
    private String avatar_url;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar() {
        return avatar_url;
    }

    static void create(Realm realm, List<UserRest> itemRests) {
        UserParent parent = realm.where(UserParent.class).findFirst();
        RealmList<User> items = parent.getItemList();
        for (UserRest curItem : itemRests) {
            User counter = realm.createObject(User.class, curItem.getId());
            counter.avatar_url = curItem.getAvatar();
            counter.login = curItem.getLogin();
            items.add(counter);
        }
    }


}
