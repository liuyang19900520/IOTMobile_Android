package com.liuyang19900520.iotmobile_android.view.main;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liuyang19900520.iotmobile_android.R;
import com.ljs.lovelyprogressbar.LovelyProgressBar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Test extends AppCompatActivity {

    SuperLoadingProgress mSuperLoadingProgress;

    int progress = 0;

    LovelyProgressBar mbar;
//    CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mSuperLoadingProgress = (SuperLoadingProgress) findViewById(R.id.pro);
        mbar = (LovelyProgressBar) findViewById(R.id.loadbar);
//        imageView = (CircleImageView) findViewById(R.id.image);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (progress < 100) {
                                Thread.sleep(10);
                                mbar.setProgress(progress);
                                progress++;
                            }
                            mbar.succesLoad();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();


            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                imageView.setVisibility(View.GONE);
//                mSuperLoadingProgress.setVisibility(View.VISIBLE);
//                new Thread() {
//                    @Override
//                    public void run() {
//                        try {
//                            mSuperLoadingProgress.setProgress(0);
//                            while (mSuperLoadingProgress.getProgress() < 100) {
//                                Thread.sleep(10);
//                                mSuperLoadingProgress.setProgress(mSuperLoadingProgress.getProgress() + 1);
//                            }
//                            mSuperLoadingProgress.finishSuccess();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
            }
        });


        mbar.startload();//设置progress前先startload（）
        mbar.setProgress(0);//设置进度

//加载成功调用即可成功动画
//        mbar.errorLoad();
//加载失败调用即可执行失败动画
//        mbar.succesLoad();

        mbar.setOnLoadListener(new LovelyProgressBar.OnLoadListener() {
            @Override
            public void onAnimSuccess() {
                Toast.makeText(Test.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimError() {
                Toast.makeText(Test.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
