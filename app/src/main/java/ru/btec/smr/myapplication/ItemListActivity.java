package ru.btec.smr.myapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import ru.btec.smr.myapplication.model.UserRest;
import ru.btec.smr.myapplication.presenters.UsersPresenter;
import ru.btec.smr.myapplication.presenters.UsersView;
import ru.btec.smr.myapplication.recyclerview.GithubUserRecyclerViewAdapter;


public class ItemListActivity extends MvpAppCompatActivity implements UsersView {
    @InjectPresenter
    public UsersPresenter usersPresenter;

//    private GithubUserRecyclerViewAdapter adapter;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        setUpRecyclerView((RecyclerView) findViewById(R.id.recycler_view));
    }


    private void setUpRecyclerView(RecyclerView recyclerView) {
        GithubUserRecyclerViewAdapter adapter = new GithubUserRecyclerViewAdapter(this, mTwoPane);//usersPresenter.getItemList(), usersPresenter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    @Override
    public void setUserList(List<UserRest> userList) {

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
