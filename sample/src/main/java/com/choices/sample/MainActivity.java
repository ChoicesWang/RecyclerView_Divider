package com.choices.sample;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.choices.sample.fragment.GridFragment;
import com.choices.sample.fragment.LinearFragment;

public class MainActivity extends AppCompatActivity {

    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_AGILE = 1;

    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getFragmentManager();
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LinearFragment.newInstance(TYPE_DEFAULT))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_linear:
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LinearFragment.newInstance(TYPE_DEFAULT))
                        .commit();
                break;
            case R.id.action_linear_agile:
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LinearFragment.newInstance(TYPE_AGILE))
                        .commit();
                break;
            case R.id.action_grid:
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, GridFragment.newInstance(TYPE_DEFAULT))
                        .commit();
                break;
            case R.id.action_grid_agile:
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, GridFragment.newInstance(TYPE_AGILE))
                        .commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
