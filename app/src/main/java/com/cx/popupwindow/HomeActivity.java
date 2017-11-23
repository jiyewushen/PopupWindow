package com.cx.popupwindow;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cx.baselibrary.BaseActivity;

public class HomeActivity extends BaseActivity {
    private PopupWindow pw;
    private int[] item_image_id = {R.drawable.pic_item, R.drawable.camera_item, R.drawable.help_item};
    private String[] item_text = {"添加图片", "打开相机", "关于"};
    private View.OnClickListener[]onClickListeners;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void bindView() {
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        onClickListeners=new View.OnClickListener[item_image_id.length];
        onClickListeners[0]=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
                Toast.makeText(HomeActivity.this,"打开图片",Toast.LENGTH_SHORT).show();
            }
        };
        onClickListeners[1]=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
                Toast.makeText(HomeActivity.this,"打开相机",Toast.LENGTH_SHORT).show();

            }
        };
        onClickListeners[2]=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
                Toast.makeText(HomeActivity.this,"关于",Toast.LENGTH_SHORT).show();

            }
        };
        initPopupWindow();
        ImageButton im = findViewById(R.id.pop_up_window);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pw.isShowing()) {
                    pw.dismiss();
                } else {
                    popupWindowShow(view);
                }
            }
        });

    }

    @Override
    protected void bindData() {

    }

    /**
     * 显示弹出窗口
     * @param view
     */
    private void popupWindowShow(View view) {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int w = DisplayUtil.dp2px(HomeActivity.this, 73);
        int h = DisplayUtil.dp2px(HomeActivity.this, 64);
        Point p = new Point();
        wm.getDefaultDisplay().getSize(p);
        int x = p.x - view.getMeasuredWidth() - w;
        pw.showAtLocation(view, Gravity.NO_GRAVITY, x, h);
        changeWindowAlpha(0.7f);
    }

    /**
     * 改变界面的透明度
     * @param alpha
     */
    private void changeWindowAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    private void initPopupWindow() {
        View content = LayoutInflater.from(HomeActivity.this).inflate(R.layout.pop_up_window, null);
        RecyclerView recyclerView = content.findViewById(R.id.rv_pw);
        recyclerView.setLayoutManager(new GridLayoutManager(HomeActivity.this, 1));
        recyclerView.setAdapter(new MyAdapter(item_image_id, item_text,onClickListeners));
        DividerItemDecoration decoration = new DividerItemDecoration(HomeActivity.this);
        recyclerView.addItemDecoration(decoration);
        pw = new PopupWindow(content, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        Drawable drawable = getResources().getDrawable(R.drawable.popupwindow_bg);
        pw.setBackgroundDrawable(drawable);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.setAnimationStyle(R.style.PopupWindowAnimation);
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeWindowAlpha(1.0f);
            }
        });
    }




    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private int[] id;
        private String[] text;
        private View.OnClickListener[]onClickListeners;

        MyAdapter(int[] id, String[] text, View.OnClickListener[]onClickListeners) {
            this.id = id;
            this.text = text;
            this.onClickListeners=onClickListeners;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(HomeActivity.this).inflate(R.layout.pop_up_item, null);
            MyViewHolder h = new MyViewHolder(view);
            h.itemView.setBackgroundResource(R.drawable.pw_item_bg);
            return h;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.iv.setImageResource(id[position]);
            holder.tv.setText(text[position]);
            holder.itemView.setOnClickListener(onClickListeners[position]);
        }

        @Override
        public int getItemCount() {
            return Math.min(id.length, text.length);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item_icon);
            tv = itemView.findViewById(R.id.item_name);
        }
    }
}
