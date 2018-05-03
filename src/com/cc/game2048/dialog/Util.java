package com.cc.game2048.dialog;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.cc.game2048.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Util {
	private static AlertDialog mAlertDialog;
	public static View getView(Context context, int layoutId) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(layoutId, null);
		return layout;

	}
	/**
	 * 界面转换
	 * 
	 * @param context
	 * @param des
	 */
	public static void startActivity(Context context, Class des) {
		Intent intent = new Intent();
		intent.setClass(context, des);
		context.startActivity(intent);
		// 关闭源Activity
		((Activity) context).finish();

	}
	/**
	 * 显示自定义对话框
	 */
	public static void showDialog(final Context context, String message, final IAlertDialogButtonListener listener) {
		View dialogView = null;
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		dialogView=getView(context, R.layout.dialog);

		//初始化按钮,控件
		ImageButton btnOKView=(ImageButton) dialogView.findViewById(R.id.btn_dialog_ok);
		ImageButton btnCancelView=(ImageButton) dialogView.findViewById(R.id.btn_dialog_cancel);
		TextView txtMessageView=(TextView) dialogView.findViewById(R.id.txt_dialog_message);
		
		
		//设置message
		txtMessageView.setText(message);
		//设置按钮点击事件
		btnOKView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 关闭对话框
				if (mAlertDialog!=null) {
					mAlertDialog.cancel();
				}
				//事件回调
				if (listener!=null) {
					listener.onClick();
				}
				//播放音效
				MyPlay.playTone(context, Const.INDEX_STONE_ENTER);
			}
		});
		
		btnCancelView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 关闭对话框
				if (mAlertDialog!=null) {
					mAlertDialog.cancel();
				}
				//播放音效
				MyPlay.playTone(context, Const.INDEX_STONE_CANCEL);
			}
		});
		//为dialog设置view
		builder.setView(dialogView);
		mAlertDialog=builder.create();
		
		//显示对话框
		mAlertDialog.show();
		
	}
	/**
	 * 保存数据
	 * @param context
	 * @param stageIndex
	 * @param coins
	 */
	public static void saveData(Context context,int currentBestSorce) {
		FileOutputStream mFileOutputStream=null;
		try {
			mFileOutputStream=context.openFileOutput(Const.FILE_NAME_SAVE_DATA, context.MODE_PRIVATE);
			DataOutputStream mDataOutputStream=new DataOutputStream(mFileOutputStream);
			mDataOutputStream.writeInt(currentBestSorce);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (mFileOutputStream!=null) {
				try {
					mFileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 读取游戏数据
	 * @param context
	 * @return
	 */
	public static int loadData(Context context) {
		FileInputStream mFileInputStream=null;
		int data=0;
		try {
			mFileInputStream=context.openFileInput(Const.FILE_NAME_SAVE_DATA);
			DataInputStream mDataInputStream=new DataInputStream(mFileInputStream);
			data=mDataInputStream.readInt();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (mFileInputStream!=null) {
				try {
					mFileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return data;
	}
}
