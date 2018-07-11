package github.goldenstrawberry.appbarlayout_demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //设置标题文字
        toolbar.setTitle("巅峰榜 流行指数 第64天");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        // 显示返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
