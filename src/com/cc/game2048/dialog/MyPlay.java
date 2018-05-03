package com.cc.game2048.dialog;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

/**
 * 音乐播放类
 * 
 * @author cc 2016年8月22日
 */
public class MyPlay {
	private static MediaPlayer mMediaPlay;
	//音效
	private static MediaPlayer[] mToneMediaPlay=new MediaPlayer[Const.TONE_SONG_NAME.length];

	/**
	 * 播放音效
	 * @param context
	 * @param index
	 */
	public static void playTone(Context context,int index) {
		//加载声音
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
		//播放声音
		mToneMediaPlay[index].start();
	}
	/**
	 * 播放音乐
	 * @param context
	 * @param fileName
	 */
	public static void playMusic(Context context, String fileName) {
		if (mMediaPlay==null) {
			mMediaPlay=new MediaPlayer();
		}
		//强制重置,针对非第一次播放
		mMediaPlay.reset();
		//加载声音
		AssetManager assetManager=context.getAssets();
		try {
			AssetFileDescriptor fileDescriptor=assetManager.openFd(fileName);
			mMediaPlay.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
			mMediaPlay.prepare();
			//播放声音
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
