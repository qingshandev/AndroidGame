package com.pg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author Himi
 *
 */
public class Enemy {
	//敌机的种类标识
	public int type;
	//苍蝇
	public static final int TYPE_FLY = 1;
	//鸭子(从左往右运动)
	public static final int TYPE_DUCKL = 2;
	//鸭子(从右往左运动)
	public static final int TYPE_DUCKR = 3;
	//敌机图片资源
	public Bitmap bmpEnemy;
	//敌机坐标
	public int x, y;
	//敌机每帧的宽高
	public int frameW, frameH;
	//敌机当前帧下标
	private int frameIndex;
	//敌机的移动速度
	private int speed;;
	//判断敌机是否已经出屏
	public boolean isDead;

	//敌机的构造函数
	public Enemy(Bitmap bmpEnemy, int enemyType, int x, int y) {
		this.bmpEnemy = bmpEnemy;
		frameW = bmpEnemy.getWidth() / 10;
		frameH = bmpEnemy.getHeight();
		this.type = enemyType;
		this.x = x;
		this.y = y;
		//不同种类的敌机血量不同
		switch (type) {
		//苍蝇
		case TYPE_FLY:
			speed = 25;
			break;
		//鸭子
		case TYPE_DUCKL:
			speed = 3;
			break;
		case TYPE_DUCKR:
			speed = 3;
			break;
		}
	}

	//敌机绘图函数
	public void draw(Canvas canvas, Paint paint) {
		canvas.save();
		canvas.clipRect(x, y, x + frameW, y + frameH);
		canvas.drawBitmap(bmpEnemy, x - frameIndex * frameW, y, paint);
		canvas.restore();
	}

	//敌机逻辑AI
	public void logic() {
		//不断循环播放帧形成动画
		frameIndex++;
		if (frameIndex >= 10) {
			frameIndex = 0;
		}
		//不同种类的敌机拥有不同的AI逻辑
		switch (type) {
		case TYPE_FLY:
			if (isDead == false) {
				//减速出现，加速返回
				speed -= 1;
				y += speed;
				if (y <= -200) {
					isDead = true;
				}
			}
			break;
		case TYPE_DUCKL:
			if (isDead == false) {
				//斜右下角运动
				x += speed / 2;
				y += speed;
				if (x > MySurfaceView.screenW) {
					isDead = true;
				}
			}
			break;
		case TYPE_DUCKR:
			if (isDead == false) {
				//斜左下角运动
				x -= speed / 2;
				y += speed;
				if (x < -50) {
					isDead = true;
				}
			}
			break;
		}
	}

	//判断碰撞(敌机与主角子弹碰撞)
	public boolean isCollsionWith(Bullet bullet) {
		int x2 = bullet.bulletX;
		int y2 = bullet.bulletY;
		int w2 = bullet.bmpBullet.getWidth();
		int h2 = bullet.bmpBullet.getHeight();
		if (x >= x2 && x >= x2 + w2) {
			return false;
		} else if (x <= x2 && x + frameW <= x2) {
			return false;
		} else if (y >= y2 && y >= y2 + h2) {
			return false;
		} else if (y <= y2 && y + frameH <= y2) {
			return false;
		}
		//发生碰撞，让其死亡
		isDead = true;
		return true;
	}
}
