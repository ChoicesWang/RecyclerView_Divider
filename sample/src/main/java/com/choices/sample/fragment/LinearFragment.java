package com.choices.sample.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.choices.sample.MainActivity;
import com.choices.sample.R;
import com.choices.sample.holder.OneHolder;

import java.util.ArrayList;


public class LinearFragment extends Fragment {

    public static LinearFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("Type", type);
        LinearFragment fragment = new LinearFragment();
        fragment.setArguments(args);
        return fragment;
    }

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


        Bundle bundle = getArguments();

        DividerItemDecoration itemDecoration = new DividerItemDecoration();
        if (bundle.getInt("Type") == MainActivity.TYPE_DEFAULT) {
            // default DividerItemDecoration is size = 2 and color = Color.GRAY
            mRecyclerView.addItemDecoration(itemDecoration);
        } else {
            itemDecoration.setDividerLookup(new AgileDividerLookup());
            mRecyclerView.addItemDecoration(itemDecoration);
        }
    }


    class AgileDividerLookup extends DividerItemDecoration.SimpleDividerLookup {
        @Override
        public Divider getHorizontalDivider(int position) {
            int style = position % 15;
            switch (style) {
                case 0:
                    return new Divider.Builder()
                            .size(5)
                            .color(Color.RED)
                            .build();
                case 1:
                    return new Divider.Builder()
                            .size(5)
                            .color(Color.RED)
                            .margin(100, 100)
                            .build();
                case 2:
                    return new Divider.Builder()
                            .size(3)
                            .color(Color.MAGENTA)
                            .margin(60, 60)
                            .build();
                case 3:
                    return new Divider.Builder()
                            .size(15)
                            .color(Color.MAGENTA)
                            .margin(20, 20)
                            .build();
                case 4:
                    return new Divider.Builder()
                            .size(30)
                            .color(Color.BLUE)
                            .build();
                default:
                    return new Divider.Builder()
                            .size(2)
                            .color(Color.LTGRAY)
                            .build();

            }
        }
    }


    class LinearAdapter extends RecyclerView.Adapter {

        ArrayList<String> data;

        public LinearAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = factory.inflate(R.layout.item_cell, parent, false);
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
