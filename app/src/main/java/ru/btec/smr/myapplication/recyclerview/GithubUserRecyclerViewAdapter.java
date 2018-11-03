package ru.btec.smr.myapplication.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmRecyclerViewAdapter;
import ru.btec.smr.myapplication.ItemDetailActivity;
import ru.btec.smr.myapplication.ItemDetailFragment;
import ru.btec.smr.myapplication.ItemListActivity;
import ru.btec.smr.myapplication.R;
import ru.btec.smr.myapplication.model.User;


public class GithubUserRecyclerViewAdapter extends RealmRecyclerViewAdapter<User, GithubUserHolder> {
    ItemListActivity mParentActivity;
    private boolean mTwoPane;

    public void onClickItem(View view, String login, String avatar, String html) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(ItemDetailFragment.ITEM_LOGIN, login);
            arguments.putString(ItemDetailFragment.ITEM_AVATAR, avatar);
            arguments.putString(ItemDetailFragment.ITEM_HTML, html);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            mParentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Context context = view.getContext();
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra(ItemDetailFragment.ITEM_LOGIN, login);
            intent.putExtra(ItemDetailFragment.ITEM_AVATAR, avatar);
            intent.putExtra(ItemDetailFragment.ITEM_HTML, html);
            context.startActivity(intent);
        }
    }

    public GithubUserRecyclerViewAdapter(ItemListActivity mParentActivity, boolean mTwoPane) {
        super(mParentActivity.usersPresenter.getItemList(), true);
        setHasStableIds(true);
        mParentActivity.usersPresenter.getData(30);
        this.mParentActivity = mParentActivity;
        this.mTwoPane = mTwoPane;
    }

    @Override
    public void onViewRecycled(@NonNull GithubUserHolder holder) {
        super.onViewRecycled(holder);
        if (getItemCount() - holder.getAdapterPosition() == 30)
            mParentActivity.usersPresenter.getData(30);
    }


    @Override
    public GithubUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return GithubUserHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(GithubUserHolder holder, int position) {
        holder.bind(getItem(position));
        holder.itemView.setOnClickListener((v) -> {
            onClickItem(v, getItem(position).getLogin(), getItem(position).getAvatar(), getItem(position).getHtml_url());
        });
    }

    @Override
    public long getItemId(int index) {
        return getItem(index).getId();
    }

}

