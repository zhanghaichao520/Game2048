package com.cc.game2048.ui;

import java.util.ArrayList;
import java.util.List;

import com.cc.game2048.dialog.IAlertDialogButtonListener;
import com.cc.game2048.dialog.Util;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout {
	public static final int NUM_WIDTH = 4;
	public static final int NUM_HEIGH = 4;
	public static final int INIT_NUM = 2;
	
	private static Card[][] cards = new Card[NUM_WIDTH][NUM_HEIGH];
	private static List<Point> emptyPoints = new ArrayList<Point>();

	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	/**
	 * 初始化界面
	 */
	private void initGameView() {

		setColumnCount(NUM_WIDTH); // 设置Gridview为四列
		//setBackgroundColor(0xffCC9966);// 设置游戏背景颜色
		// 监听滑动事件
		setOnTouchListener(new OnTouchListener() {
			private float startX, startY, offsetX, offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				// 手指按下
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				// 手指离开
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;
					// 比较水平和竖直方向偏移量大小
					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						// 左右滑动
						if (offsetX < -5) {
							swipeLeft();
							refreshColor();
						} else if (offsetX > 5) {
							swipeRight();
							refreshColor();
						}
					} else {
						if (offsetY < -5) {
							swipeUp();
							refreshColor();
						} else if (offsetY > 5) {
							swipeDown();
							refreshColor();
						}
					}
					break;
				}
				return true;
			}
		});
	}

	/**
	 * 滑动事件
	 */
	private void swipeDown() {
		boolean flag = false;// 是否产生新的数字的标志
		for (int j = 0; j < NUM_HEIGH; j++) {// 遍历每一列
			for (int i = NUM_WIDTH - 1; i >= 0; i--) { // 从每一列的底部开始遍历每个card

				for (int k = i - 1; k >= 0; k--) { // 遍历这一列的这个个card上面的card
					if (cards[k][j].getNum() > 0) { // 如果这个card上面的card不为空
						if (cards[i][j].getNum() <= 0) { // 如果这个card为空
							cards[i][j].setNum(cards[k][j].getNum()); // 把他上面的card移动下来
							cards[k][j].setNum(0); // 上面的那个card以前的位置清空
							i++; // 避免数字相同不合并情况
							flag = true;// 发生了变化。，设置产生新card的标志为true
						} else if (cards[i][j].equals(cards[k][j])) {// 如果这个card和上面的card值相同
							cards[i][j].setNum(cards[i][j].getNum() * 2);// 设置底部的这个card值为他们的
							cards[k][j].setNum(0);// 上面的那个card以前的位置清空
							MainActivity.getMainActivity().addScore(cards[i][j].getNum());// 添加分数为以前card的值
							MainActivity.getMainActivity().setHighSorce(); //设置最高分
							flag = true;// 发生了变化。，设置产生新card的标志为true
						}
						break; // 跳出最内层循环
					}
				}
			}
		}
		if (flag) {
			addRandowNum();// 添加卡片
			checkComplete();
		}
	}

	private void swipeUp() {
		boolean flag = false;
		for (int j = 0; j < NUM_HEIGH; j++) {
			for (int i = 0; i < NUM_WIDTH; i++) {

				for (int k = i + 1; k < NUM_WIDTH; k++) {
					if (cards[k][j].getNum() > 0) {
						if (cards[i][j].getNum() <= 0) {
							cards[i][j].setNum(cards[k][j].getNum());
							cards[k][j].setNum(0);
							i--; // 避免数字相同不合并情况
							flag = true;
						} else if (cards[i][j].equals(cards[k][j])) {
							cards[i][j].setNum(cards[i][j].getNum() * 2);
							cards[k][j].setNum(0);
							MainActivity.getMainActivity().addScore(cards[i][j].getNum());
							MainActivity.getMainActivity().setHighSorce(); //设置最高分
							flag = true;
						}
						break;
					}
				}
			}
		}
		if (flag) {
			addRandowNum();
			checkComplete();
		}
	}

	private void swipeLeft() {
		boolean flag = false;
		for (int i = 0; i < NUM_WIDTH; i++) {
			for (int j = 0; j < NUM_HEIGH; j++) {

				for (int k = j + 1; k < NUM_HEIGH; k++) {
					if (cards[i][k].getNum() > 0) {
						if (cards[i][j].getNum() <= 0) {
							cards[i][j].setNum(cards[i][k].getNum());
							cards[i][k].setNum(0);
							j--; // 避免数字相同不合并情况
							flag = true;
						} else if (cards[i][j].equals(cards[i][k])) {
							cards[i][j].setNum(cards[i][j].getNum() * 2);
							cards[i][k].setNum(0);
							MainActivity.getMainActivity().addScore(cards[i][j].getNum());
							MainActivity.getMainActivity().setHighSorce(); //设置最高分
							flag = true;
						}
						break;
					}
				}
			}
		}
		if (flag) {
			addRandowNum();
			checkComplete();
		}
	}

	private void swipeRight() {
		boolean flag = false;
		for (int i = 0; i < NUM_WIDTH; i++) {
			for (int j = NUM_HEIGH - 1; j >= 0; j--) {

				for (int k = j - 1; k >= 0; k--) {
					if (cards[i][k].getNum() > 0) {
						if (cards[i][j].getNum() <= 0) {
							cards[i][j].setNum(cards[i][k].getNum());
							cards[i][k].setNum(0);
							j++; // 避免数字相同不合并情况
							flag = true;
						} else if (cards[i][j].equals(cards[i][k])) {
							cards[i][j].setNum(cards[i][j].getNum() * 2);
							cards[i][k].setNum(0);

							MainActivity.getMainActivity().addScore(cards[i][j].getNum());
							MainActivity.getMainActivity().setHighSorce(); //设置最高分
							flag = true;
						}
						break;
					}
				}
			}
		}
		if (flag) {
			addRandowNum();
			checkComplete();
		}
	}

	/**
	 * 使卡片大小自适应
	 * 
	 * @param w
	 * @param h
	 * @param oldw
	 * @param oldh
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);

		int cardWidth = (Math.min(w, h) - 10) / 4;
		addCard(cardWidth);
		MainActivity.getMainActivity().setHighSorce();
		startGame();
	}

	/**
	 * 开始游戏
	 */
	public static void startGame() {
		MainActivity.getMainActivity().clearScore();
		for (int j = 0; j < NUM_HEIGH; j++) {
			for (int i = 0; i < NUM_WIDTH; i++) {
				cards[i][j].setNum(0);
			}
		}
		
		addRandowNum();
		addRandowNum();
		refreshColor();
	}

	/**
	 * 添加卡片
	 * 
	 * @param cardWidth
	 */
	private void addCard(int cardWidth) {
		Card card = null;
		for (int i = 0; i < NUM_WIDTH; i++) {
			for (int j = 0; j < NUM_HEIGH; j++) {
				card = new Card(getContext());
				card.setNum(0);
				addView(card, cardWidth, cardWidth);
				cards[i][j] = card;
			}
		}
	}

	/**
	 * 在随机的空地方生成随机数字（2或4）
	 */
	private static void addRandowNum() {
		emptyPoints.clear();
		for (int j = 0; j < NUM_HEIGH; j++) {
			for (int i = 0; i < NUM_WIDTH; i++) {
				if (cards[i][j].getNum() <= 0) {
					emptyPoints.add(new Point(i, j));
				}
			}
		}
		Point point = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
		cards[point.x][point.y].setNum(Math.random() > 0.1 ? 2 : 4);

	}

	private void checkComplete() {
		boolean complete = true;
		ALL: for (int j = 0; j < NUM_HEIGH; j++) {
			for (int i = 0; i < NUM_WIDTH; i++) {
				if (cards[i][j].getNum() == 0 || isMerge()) {
					complete = false;
					break ALL;
				}
			}
		}
		if (complete) {
			/*
			 * new AlertDialog.Builder(getContext()).setTitle("你好").
			 * setMessage("游戏结束").setPositiveButton("重新开始", new
			 * DialogInterface.OnClickListener() {
			 * 
			 * @Override public void onClick(DialogInterface arg0, int arg1) {
			 * // TODO Auto-generated method stub startGame(); } }).show();
			 */
			 Util.showDialog(getContext(), "游戏结束，是否重新开始？",
			 mBtnOkRePlayListener);
		}
	}

	public IAlertDialogButtonListener mBtnOkRePlayListener = new IAlertDialogButtonListener() {

		@Override
		public void onClick() {
			// TODO Auto-generated method stub
			startGame();
		}
	};

	private boolean isMerge() {
		for (int j = 0; j < NUM_HEIGH; j++) {
			for (int i = 0; i < NUM_WIDTH; i++) {
				if (i > 0 && cards[i][j].equals(cards[i - 1][j])) {
					return true;
				}
				if (i < NUM_WIDTH - 1 && cards[i][j].equals(cards[i + 1][j])) {
					return true;
				}
				if (j > 0 && cards[i][j].equals(cards[i][j - 1])) {
					return true;
				}
				if (j < NUM_HEIGH - 1 && cards[i][j].equals(cards[i][j + 1])) {
					return true;
				}
			}
		}
		return false;
	}

	public static void refreshColor() {
		for (int j = 0; j < NUM_HEIGH; j++) {
			for (int i = 0; i < NUM_WIDTH; i++) {
				cards[i][j].chooseColor();
			}
		}
	}
}
