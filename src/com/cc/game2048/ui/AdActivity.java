package com.cc.game2048.ui;

import com.cc.game2048.R;
import com.cc.game2048.dialog.Const;

import android.app.Activity;
import android.os.Bundle;
import net.youmi.android.AdManager;
import net.youmi.android.normal.spot.SpotManager;

public class AdActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ad);
		// ≥ı ºªØad
		AdManager.getInstance(AdActivity.this).init(Const.APPID, Const.APPSECRET, true, true);
		SpotManager.getInstance(AdActivity.this).showSplashSpotAds(AdActivity.this,MainActivity.class);
		//Util.startActivity(AdActivity.this, MainActivity.class);
	}
}
