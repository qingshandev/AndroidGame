package com.pg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * @author Himi
 *
 */
public class GameMenu {
	//�˵�����ͼ
	private Bitmap bmpMenu;
	//��ťͼƬ��Դ(���º�δ����ͼ)
	private Bitmap bmpButton, bmpButtonPress;
	//��ť������
	private int btnX, btnY;
	//��ť�Ƿ��±�ʶλ
	private Boolean isPress;
	//�˵���ʼ��
	public GameMenu(Bitmap bmpMenu, Bitmap bmpButton, Bitmap bmpButtonPress) {
		this.bmpMenu = bmpMenu;
		this.bmpButton = bmpButton;
		this.bmpButtonPress = bmpButtonPress;
		//X���У�Y������Ļ�ײ�
		btnX = MySurfaceView.screenW / 2 - bmpButton.getWidth() / 2;
		btnY = MySurfaceView.screenH - bmpButton.getHeight();
		isPress = false;
	}
	//�˵���ͼ����
	public void draw(Canvas canvas, Paint paint) {
		//���Ʋ˵�����ͼ
		canvas.drawBitmap(bmpMenu, 0, 0, paint);
		//����δ���°�ťͼ
		if (isPress) {//�����Ƿ��»��Ʋ�ͬ״̬�İ�ťͼ
			canvas.drawBitmap(bmpButtonPress, btnX, btnY, paint);
		} else {
			canvas.drawBitmap(bmpButton, btnX, btnY, paint);
		}
	}
	//�˵������¼���������Ҫ���ڴ���ť�¼�
	public void onTouchEvent(MotionEvent event) {
		//��ȡ�û���ǰ����λ��
		int pointX = (int) event.getX();
		int pointY = (int) event.getY();
		//���û��ǰ��¶������ƶ�����
		if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
			//�ж��û��Ƿ����˰�ť
			if (pointX > btnX && pointX < btnX + bmpButton.getWidth()) {
				if (pointY > btnY && pointY < btnY + bmpButton.getHeight()) {
					isPress = true;
				} else {
					isPress = false;
				}
			} else {
				isPress = false;
			}
			//���û���̧����
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			//̧���ж��Ƿ�����ť����ֹ�û��ƶ�����
			if (pointX > btnX && pointX < btnX + bmpButton.getWidth()) {
				if (pointY > btnY && pointY < btnY + bmpButton.getHeight()) {
					//��ԭButton״̬Ϊδ����״̬
					isPress = false;
					//�ı䵱ǰ��Ϸ״̬Ϊ��ʼ��Ϸ
					MySurfaceView.gameState = MySurfaceView.GAMEING;
				}
			}
		}
	}
}
