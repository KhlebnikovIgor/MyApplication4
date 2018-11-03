package ru.btec.smr.myapplication.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.btec.smr.myapplication.R;
import ru.btec.smr.myapplication.model.User;


public class GithubUserHolder extends RecyclerView.ViewHolder {
    private TextView titleTextView;
    private ImageView imageView;

    private GithubUserHolder(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.item_title);
        imageView = (ImageView) itemView.findViewById(R.id.avatar);
    }

    public void bind(User item) {
        titleTextView.setText(Html.fromHtml("<font color=#639EFD>Id: </font>" + item.getId() + "<br><font color=#639EFD>Login: </font>" + item.getLogin()));
        Glide.with(itemView)
                .load(item.getAvatar())
                .into(imageView);
    }

    static GithubUserHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);
        return new GithubUserHolder(view);
    }

}