package github.goldenstrawberry.appbarlayout_demo;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctbl);
        imageView = (ImageView) findViewById(R.id.imageView);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBar);

        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE); // 也可以在xml中设置属性
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
        // 显示返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int newalpha = 255 + verticalOffset;
                newalpha = newalpha < 0 ? 0 : newalpha;
                imageView.setAlpha(newalpha);
            }
        });
    }
}
