package com.choices.sample.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Choices on 2016/3/11.
 * email: zezeviyao@163.com
 */
public class OneHolder extends RecyclerView.ViewHolder {

    public TextView mTextView;

    public OneHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView;
    }
}
