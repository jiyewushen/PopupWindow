package com.cx.baselibrary;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cx on 2017/9/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        bindView();
        bindData();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        bindView();
        bindData();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        bindView();
        bindData();
    }

    protected abstract void bindView();

    protected abstract void bindData();
}
