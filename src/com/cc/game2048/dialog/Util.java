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
	 * ����ת��
	 * 
	 * @param context
	 * @param des
	 */
	public static void startActivity(Context context, Class des) {
		Intent intent = new Intent();
		intent.setClass(context, des);
		context.startActivity(intent);
		// �ر�ԴActivity
		((Activity) context).finish();

	}
	/**
	 * ��ʾ�Զ���Ի���
	 */
	public static void showDialog(final Context context, String message, final IAlertDialogButtonListener listener) {
		View dialogView = null;
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		dialogView=getView(context, R.layout.dialog);

		//��ʼ����ť,�ؼ�
		ImageButton btnOKView=(ImageButton) dialogView.findViewById(R.id.btn_dialog_ok);
		ImageButton btnCancelView=(ImageButton) dialogView.findViewById(R.id.btn_dialog_cancel);
		TextView txtMessageView=(TextView) dialogView.findViewById(R.id.txt_dialog_message);
		
		
		//����message
		txtMessageView.setText(message);
		//���ð�ť����¼�
		btnOKView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// �رնԻ���
				if (mAlertDialog!=null) {
					mAlertDialog.cancel();
				}
				//�¼��ص�
				if (listener!=null) {
					listener.onClick();
				}
				//������Ч
				MyPlay.playTone(context, Const.INDEX_STONE_ENTER);
			}
		});
		
		btnCancelView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// �رնԻ���
				if (mAlertDialog!=null) {
					mAlertDialog.cancel();
				}
				//������Ч
				MyPlay.playTone(context, Const.INDEX_STONE_CANCEL);
			}
		});
		//Ϊdialog����view
		builder.setView(dialogView);
		mAlertDialog=builder.create();
		
		//��ʾ�Ի���
		mAlertDialog.show();
		
	}
	/**
	 * ��������
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
	 * ��ȡ��Ϸ����
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
