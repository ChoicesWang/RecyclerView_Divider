package com.choices.sample.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.choices.sample.Cell;
import com.choices.sample.MainActivity;
import com.choices.sample.R;
import com.choices.sample.holder.OneHolder;

import java.util.ArrayList;

/**
 * Created by Choices on 2016/3/11.
 * email: zezeviyao@163.com
 */
public class GridFragment extends Fragment {

    private static final String TAG = "GridFragment";

    public static GridFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("Type", type);
        GridFragment fragment = new GridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerView mRecyclerView;

    ArrayList<Cell> list;

    LayoutInflater factory;

    GridAdapter adapter;

    private int span;
    private int type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("Type");

        list = new ArrayList<>();
        if (type == MainActivity.TYPE_DEFAULT) {
            span = 4;
            for (int i = 0; i < 400; i++) {
                list.add(new Cell(0, String.valueOf(i)));
            }
        } else if (type == MainActivity.TYPE_AGILE) {
            span = 6;
            for (int i = 0; i < 400; i++) {
                list.add(new Cell(1, String.valueOf(i++)));
                list.add(new Cell(2, String.valueOf(i++)));
                list.add(new Cell(2, String.valueOf(i++)));
                list.add(new Cell(3, String.valueOf(i++)));
                list.add(new Cell(3, String.valueOf(i++)));
                list.add(new Cell(3, String.valueOf(i++)));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        factory = inflater;
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view;
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), span);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new GridAdapter(list);
        mRecyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new AgileDividerLookup());
        mRecyclerView.addItemDecoration(itemDecoration);

        if (type == MainActivity.TYPE_AGILE) {
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return span / adapter.getItemViewType(position);
                }
            });
        }

    }

    class AgileDividerLookup implements DividerItemDecoration.DividerLookup {
        @Override
        public Divider getVerticalDivider(int position) {
            int type = adapter.getItemViewType(position);
            switch (type) {
                case 0:
                    return new Divider.Builder()
                            .size(2)
                            .color(Color.DKGRAY)
                            .build();
                case 2:
                    return new Divider.Builder()
                            .size(4)
                            .color(Color.GREEN)
                            .build();
                case 3:
                    return new Divider.Builder()
                            .size(10)
                            .color(Color.LTGRAY)
                            .build();
            }
            return null;
        }

        @Override
        public Divider getHorizontalDivider(int position) {
            int type = adapter.getItemViewType(position);
            switch (type) {
                case 0:
                    return new Divider.Builder()
                            .size(2)
                            .color(Color.DKGRAY)
                            .build();
                case 1:
                    return new Divider.Builder()
                            .size(5)
                            .color(Color.RED)
                            .build();
                case 2:
                    return new Divider.Builder()
                            .size(3)
                            .color(Color.MAGENTA)
                            .build();
                case 3:
                    return new Divider.Builder()
                            .size(5)
                            .color(Color.BLUE)
                            .build();
            }
            return null;
        }
    }

    class GridAdapter extends RecyclerView.Adapter {

        ArrayList<Cell> data;

        public GridAdapter(ArrayList<Cell> data) {
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder() called with: " + "parent.width = [" + parent.getWidth() + "], viewType = [" + viewType + "]");
            int itemHeight = 100;
            switch (viewType) {
                case 0:
                    itemHeight = parent.getWidth() / span;
                    break;
                case 1:
                    itemHeight = parent.getWidth() / 6;
                    break;
                case 2:
                    itemHeight = parent.getWidth() / 2 / 16 * 9;
                    break;
                case 3:
                    itemHeight = parent.getWidth() / 3 / 2 * 3;
                    break;
            }
            View root = factory.inflate(R.layout.item_cell, parent, false);
            root.getLayoutParams().height = itemHeight;
            return new OneHolder(root);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            OneHolder oneHolder = (OneHolder) holder;
            oneHolder.mTextView.setText(data.get(position).text);
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public int getItemViewType(int position) {
            return list.get(position).type;
        }
    }
}
