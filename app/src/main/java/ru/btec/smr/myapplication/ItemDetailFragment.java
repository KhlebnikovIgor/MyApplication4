package ru.btec.smr.myapplication;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

import java.util.List;

import ru.btec.smr.myapplication.model.ReposRest;
import ru.btec.smr.myapplication.presenters.ReposPresenter;
import ru.btec.smr.myapplication.presenters.ReposView;


public class ItemDetailFragment extends MvpAppCompatFragment implements ReposView {
    public static final String ITEM_LOGIN = "item_login";
    public static final String ITEM_AVATAR = "item_avatar";
    private static final String TAG = "ItemDetailFragment";

    @InjectPresenter
    public ReposPresenter reposPresenter;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        Activity activity = this.getActivity();
        ImageView imageView = activity.findViewById(R.id.avatar_detail);
        Glide.with(this)
                .load(getArguments().getString(ITEM_AVATAR))
                .into(imageView);

        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        appBarLayout.setTitle(getArguments().getString(ITEM_LOGIN) + " repositories");

        reposPresenter.getReposList(getArguments().getString(ITEM_LOGIN));
        return rootView;
    }

    @Override
    public void setReposList(List<ReposRest> reposList) {
        Log.e(TAG, Integer.toString(reposList.size()));
        //((TextView) rootView.findViewById(R.id.item_detail)).setText(getArguments().getString(ITEM_LOGIN) + " repositories");
    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void startLoad() {

    }

    @Override
    public void finishLoad() {

    }
}
