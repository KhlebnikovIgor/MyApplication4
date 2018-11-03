package ru.btec.smr.myapplication.presenters;


import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.btec.smr.myapplication.model.ReposRest;


public interface ReposView extends MvpView {
    void setReposList(List<ReposRest> reposList);
    void showError(Throwable e);

    void startLoad();
    void finishLoad();
}
