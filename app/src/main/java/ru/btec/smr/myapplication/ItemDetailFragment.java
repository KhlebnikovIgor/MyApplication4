package ru.btec.smr.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
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
    public static final String ITEM_HTML = "item_html";
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
        Activity activity = getActivity();
        ImageView imageView = activity.findViewById(R.id.avatar_detail);
        if (imageView != null) {
            Glide.with(this)
                    .load(getArguments().getString(ITEM_AVATAR))
                    .into(imageView);
        }

        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(getArguments().getString(ITEM_LOGIN) + " repositories");
        }

        reposPresenter.getReposList(getArguments().getString(ITEM_LOGIN));
        return rootView;
    }

    @Override
    public void setReposList(List<ReposRest> reposList) {
        String repos = "REPOSITORIES";

        for (ReposRest item : reposList) {
            repos = repos + "\n" + item.getHtml_url();
        }
        TextView textView = getActivity().findViewById(R.id.item_detail);
        if (textView != null) {
            textView.setText(repos);
        }
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
