package com.choices.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choices.divider.DividerItemDecoration;
import com.choices.sample.R;
import com.choices.sample.holder.OneHolder;

import java.util.ArrayList;


public class LinearFragment extends Fragment {

    RecyclerView mRecyclerView;
    ArrayList<String> list;
    LayoutInflater factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(String.valueOf(i));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.factory = inflater;
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view;

        LinearAdapter adapter = new LinearAdapter(list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        // default DividerItemDecoration is size = 2 and color = Color.GRAY
        mRecyclerView.addItemDecoration(new DividerItemDecoration());
    }


    class LinearAdapter extends RecyclerView.Adapter {

        ArrayList<String> data;

        public LinearAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = factory.inflate(R.layout.one_cell, parent, false);
            return new OneHolder(root);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            OneHolder oneHolder = (OneHolder) holder;
            oneHolder.mTextView.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }
    }
}
