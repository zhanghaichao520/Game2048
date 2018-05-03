package com.cc.game2048.dialog;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

/**
 * ���ֲ�����
 * 
 * @author cc 2016��8��22��
 */
public class MyPlay {
	private static MediaPlayer mMediaPlay;
	//��Ч
	private static MediaPlayer[] mToneMediaPlay=new MediaPlayer[Const.TONE_SONG_NAME.length];

	/**
	 * ������Ч
	 * @param context
	 * @param index
	 */
	public static void playTone(Context context,int index) {
		//��������
		AssetManager assetManager=context.getAssets();
		if (mToneMediaPlay[index]==null) {
			mToneMediaPlay[index]=new MediaPlayer();
			try {
				AssetFileDescriptor fileDescriptor=assetManager.openFd(Const.TONE_SONG_NAME[index]);
				mToneMediaPlay[index].setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
				mToneMediaPlay[index].prepare();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//��������
		mToneMediaPlay[index].start();
	}
	/**
	 * ��������
	 * @param context
	 * @param fileName
	 */
	public static void playMusic(Context context, String fileName) {
		if (mMediaPlay==null) {
			mMediaPlay=new MediaPlayer();
		}
		//ǿ������,��Էǵ�һ�β���
		mMediaPlay.reset();
		//��������
		AssetManager assetManager=context.getAssets();
		try {
			AssetFileDescriptor fileDescriptor=assetManager.openFd(fileName);
			mMediaPlay.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
			mMediaPlay.prepare();
			//��������
			mMediaPlay.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void stopMusic(Context context) {
		if (mMediaPlay!=null) {
			mMediaPlay.stop();
		}
	}
}
