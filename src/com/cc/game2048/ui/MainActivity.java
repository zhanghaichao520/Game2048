package com.cc.game2048.ui;


import com.cc.game2048.R;
import com.cc.game2048.dialog.Const;
import com.cc.game2048.dialog.IAlertDialogButtonListener;
import com.cc.game2048.dialog.Util;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import net.youmi.android.AdManager;
import net.youmi.android.normal.banner.BannerManager;

public class MainActivity extends Activity {

	private Button mBtnRePlay;
	private TextView tvScore;
	private TextView bestScore;
	private int score=0;
	private int currentBestScore=0;//Util.loadData(MainActivity.this);
	public static MainActivity mainActivity=null;
	public MainActivity() {
		// TODO Auto-generated constructor stub
		mainActivity=this;
	}
	public static MainActivity getMainActivity() {
		return mainActivity;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initYMAds();//初始化广告
		loadYMAds();
		tvScore=(TextView) findViewById(R.id.tv_score);
		bestScore=(TextView) findViewById(R.id.bestscoretext);
		mBtnRePlay=(Button) findViewById(R.id.newgame);
		mBtnRePlay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Util.showDialog(MainActivity.this, "是否重新开始游戏？", mOkRePlayListener);
			}
		});
	}
	public IAlertDialogButtonListener mOkRePlayListener = new IAlertDialogButtonListener() {
		
		@Override
		public void onClick() {
			// TODO Auto-generated method stub
			GameView.startGame();
		}
	};
	public void clearScore() {
		score=0;
		showScore();
	}
	public void showScore() {
		tvScore.setText(score+"");
	}
	public void addScore(int s) {
		score+=s;
		showScore();
	}
	public void setHighSorce() {
		currentBestScore=Math.max(score, currentBestScore);
		//Util.saveData(MainActivity.this, currentBestScore);
		bestScore.setText(currentBestScore+"");
	}

	/**
     * 有米广告
     */
    private void initYMAds() {
		// 初始化ad
		AdManager.getInstance(MainActivity.this).init(Const.APPID, Const.APPSECRET, false, false);
    }
    private void loadYMAds() {
        // 实例化 LayoutParams（重要）
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams( FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
 
        // 设置广告条的悬浮位置
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT; // 这里示例为右下角
        // 实例化广告条
        View adView = BannerManager.getInstance(MainActivity.this).getBanner(MainActivity.this);
     // 调用 Activity 的 addContentView 函数
        this.addContentView(adView, layoutParams);
    }
   
}
