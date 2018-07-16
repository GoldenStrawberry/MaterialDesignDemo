package com.yszn.bottomsheetbehavior_demo;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AppBarLayout bottomSheetBar;
    private TextView textView;
    private RelativeLayout bottom_sheet;
    private boolean setBottomSheetHeight = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initRecyclerView();

        textView = (TextView) findViewById(R.id.bottom_sheet_tv);
        bottomSheetBar = (AppBarLayout) findViewById(R.id.design_bottom_sheet_bar);
        bottomSheetBar.setVisibility(View.INVISIBLE);
        bottom_sheet = (RelativeLayout) findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottom_sheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    textView.setVisibility(View.GONE);
                }else if(newState == BottomSheetBehavior.STATE_COLLAPSED){
                    textView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                System.out.println("onSlide: "+bottomSheet.getTop() +" ; "+bottomSheetBar.getHeight());
                textView.setVisibility(View.GONE);
                if(bottomSheet.getTop() == bottomSheetBar.getHeight() ){
                    bottomSheetBar.setVisibility(View.VISIBLE);
                    bottomSheetBar.setAlpha(slideOffset);
                    bottomSheetBar.setTranslationY(bottomSheetBar.getHeight() - bottomSheet.getTop());
                }else {
                    bottomSheetBar.setVisibility(View.INVISIBLE);
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //指定design_bottom_sheet的高度 ，根据需要可以自己修改
        if(!setBottomSheetHeight){
            CoordinatorLayout.LayoutParams params =
                    (CoordinatorLayout.LayoutParams) bottom_sheet.getLayoutParams();
            //params.height = coordinatorLayout.getHeight();
            params.height = mRecyclerView.getHeight();
            bottom_sheet.setLayoutParams(params);
            setBottomSheetHeight = true ;
        }
    }
    

    private void initRecyclerView() {
        List<String> getDummyDatas = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            getDummyDatas.add("it is you !" + i) ;
        }
        //创建默认的线性LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置增加或删除条目的动画
        mRecyclerView.setItemAnimator( new DefaultItemAnimator());
        // 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        MyAdapter mAdapter = new MyAdapter(getDummyDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        private final List<String> datas;

        public MyAdapter(List<String> data){
            this.datas = data ;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.test_list_item, null);

            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView tv;
            public ViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(android.R.id.text1);
            }

        }
    }
}
