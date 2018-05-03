package com.cc.game2048.ui;

import com.cc.game2048.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {
	private int num = 0;
	private TextView lable;
	public Card(Context context) {
		super(context);
		lable = new TextView(getContext());
		lable.setTextSize(34);
		lable.setTextColor(Color.BLACK);
		lable.setGravity(Gravity.CENTER);
		chooseColor();
		LayoutParams mLayoutParams = new LayoutParams(-1,-1);
		mLayoutParams.setMargins(10, 10, 0, 0);
		addView(lable,mLayoutParams);
		setNum(0);
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
		if (num<=0) {
			lable.setText("");
		}else {
			lable.setText(num+"");
		}
	}

	public boolean equals(Card o) {
		// TODO Auto-generated method stub
		return getNum()==o.getNum();
	}
	public void chooseColor()
	{
		Resources resources=getContext().getResources(); 
		int mNumber=getNum();
		 switch (mNumber)  
	        {  
	        case 0:  
	        	//lable.setBackgroundColor(0xffCCC0B3);
	        	Drawable drawable=resources.getDrawable(R.drawable.photo0); 
	        	lable.setBackgroundDrawable(drawable); 
	            break;  
	        case 2:  
	        	//lable.setTextColor(0xffEEE4DA);  
	        	drawable=resources.getDrawable(R.drawable.photo2); 
	        	lable.setBackgroundDrawable(drawable); 
	            break;  
	        case 4:  
	        	//lable.setTextColor(0xffEDE0C8);  
	        	drawable=resources.getDrawable(R.drawable.photo4); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        case 8:  
	        	lable.setBackgroundColor(0xffF2B179);// #F2B179  
	        	drawable=resources.getDrawable(R.drawable.photo8); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        case 16:  
	        	lable.setBackgroundColor(0xffF49563);  
	        	drawable=resources.getDrawable(R.drawable.photo16); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        case 32:  
	        	lable.setBackgroundColor(0xffF5794D);  
	        	drawable=resources.getDrawable(R.drawable.photo32); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        case 64:  
	        	lable.setBackgroundColor(0xffF55D37);  
	        	drawable=resources.getDrawable(R.drawable.photo64); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        case 128:  
	        	lable.setBackgroundColor(0xffEEE863);  
	        	drawable=resources.getDrawable(R.drawable.photo128); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        case 256:  
	        	lable.setBackgroundColor(0xffEDB04D);  
	        	drawable=resources.getDrawable(R.drawable.photo256); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        case 512:  
	        	lable.setBackgroundColor(0xffECB04D);  
	        	drawable=resources.getDrawable(R.drawable.photo512); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        case 1024:  
	        	lable.setBackgroundColor(0xffEB9437);  
	        	drawable=resources.getDrawable(R.drawable.photo1024); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        case 2048:  
	        	lable.setBackgroundColor(0xffEA7821);  
	        	drawable=resources.getDrawable(R.drawable.photo2048); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        default:  
	        	lable.setBackgroundColor(0xffEA7821);  
	        	drawable=resources.getDrawable(R.drawable.photo4096); 
	        	lable.setBackgroundDrawable(drawable);
	            break;  
	        }  
	  
	}
}
