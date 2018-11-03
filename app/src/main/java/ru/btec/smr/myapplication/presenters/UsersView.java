package ru.btec.smr.myapplication.presenters;


import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.btec.smr.myapplication.model.UserRest;


public interface UsersView extends MvpView {
    void setUserList(List<UserRest> userList);
    void showError(Throwable e);

    void startLoad();
    void finishLoad();
}
