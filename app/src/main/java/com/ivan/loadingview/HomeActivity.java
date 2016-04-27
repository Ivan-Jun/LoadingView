package com.ivan.loadingview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ivan.library.LoadingView;

/**
 * @author Ivan
 */
public class HomeActivity extends AppCompatActivity {

    LoadingView loading;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    loading.setLoadingState(LoadingView.NET_ERROR);
                    break;
                case 1:
                    loading.setLoadingState(LoadingView.ERROR);
                    break;
                case 2:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loading = (LoadingView) findViewById(R.id.loading);
        handler.sendEmptyMessageDelayed(1, 2000);
        loading.setOnErrorClickListener(new LoadingView.OnErrorClickListener() {
            @Override
            public void onErrorClick() {
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        });
    }
}
