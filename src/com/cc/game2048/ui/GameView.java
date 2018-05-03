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
	 * ��ʼ������
	 */
	private void initGameView() {

		setColumnCount(NUM_WIDTH); // ����GridviewΪ����
		//setBackgroundColor(0xffCC9966);// ������Ϸ������ɫ
		// ���������¼�
		setOnTouchListener(new OnTouchListener() {
			private float startX, startY, offsetX, offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				// ��ָ����
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				// ��ָ�뿪
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;
					// �Ƚ�ˮƽ����ֱ����ƫ������С
					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						// ���һ���
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
	 * �����¼�
	 */
	private void swipeDown() {
		boolean flag = false;// �Ƿ�����µ����ֵı�־
		for (int j = 0; j < NUM_HEIGH; j++) {// ����ÿһ��
			for (int i = NUM_WIDTH - 1; i >= 0; i--) { // ��ÿһ�еĵײ���ʼ����ÿ��card

				for (int k = i - 1; k >= 0; k--) { // ������һ�е������card�����card
					if (cards[k][j].getNum() > 0) { // ������card�����card��Ϊ��
						if (cards[i][j].getNum() <= 0) { // ������cardΪ��
							cards[i][j].setNum(cards[k][j].getNum()); // ���������card�ƶ�����
							cards[k][j].setNum(0); // ������Ǹ�card��ǰ��λ�����
							i++; // ����������ͬ���ϲ����
							flag = true;// �����˱仯�������ò�����card�ı�־Ϊtrue
						} else if (cards[i][j].equals(cards[k][j])) {// ������card�������cardֵ��ͬ
							cards[i][j].setNum(cards[i][j].getNum() * 2);// ���õײ������cardֵΪ���ǵ�
							cards[k][j].setNum(0);// ������Ǹ�card��ǰ��λ�����
							MainActivity.getMainActivity().addScore(cards[i][j].getNum());// ��ӷ���Ϊ��ǰcard��ֵ
							MainActivity.getMainActivity().setHighSorce(); //������߷�
							flag = true;// �����˱仯�������ò�����card�ı�־Ϊtrue
						}
						break; // �������ڲ�ѭ��
					}
				}
			}
		}
		if (flag) {
			addRandowNum();// ��ӿ�Ƭ
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
							i--; // ����������ͬ���ϲ����
							flag = true;
						} else if (cards[i][j].equals(cards[k][j])) {
							cards[i][j].setNum(cards[i][j].getNum() * 2);
							cards[k][j].setNum(0);
							MainActivity.getMainActivity().addScore(cards[i][j].getNum());
							MainActivity.getMainActivity().setHighSorce(); //������߷�
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
							j--; // ����������ͬ���ϲ����
							flag = true;
						} else if (cards[i][j].equals(cards[i][k])) {
							cards[i][j].setNum(cards[i][j].getNum() * 2);
							cards[i][k].setNum(0);
							MainActivity.getMainActivity().addScore(cards[i][j].getNum());
							MainActivity.getMainActivity().setHighSorce(); //������߷�
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
							j++; // ����������ͬ���ϲ����
							flag = true;
						} else if (cards[i][j].equals(cards[i][k])) {
							cards[i][j].setNum(cards[i][j].getNum() * 2);
							cards[i][k].setNum(0);

							MainActivity.getMainActivity().addScore(cards[i][j].getNum());
							MainActivity.getMainActivity().setHighSorce(); //������߷�
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
	 * ʹ��Ƭ��С����Ӧ
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
	 * ��ʼ��Ϸ
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
	 * ��ӿ�Ƭ
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
	 * ������Ŀյط�����������֣�2��4��
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
			 * new AlertDialog.Builder(getContext()).setTitle("���").
			 * setMessage("��Ϸ����").setPositiveButton("���¿�ʼ", new
			 * DialogInterface.OnClickListener() {
			 * 
			 * @Override public void onClick(DialogInterface arg0, int arg1) {
			 * // TODO Auto-generated method stub startGame(); } }).show();
			 */
			 Util.showDialog(getContext(), "��Ϸ�������Ƿ����¿�ʼ��",
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
