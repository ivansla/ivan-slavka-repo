package ivan.slavka.adapters;

import ivan.slavka.DrawingView2;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

public class MyTouchListenerAdapter implements View.OnTouchListener{

	private DrawingView2 d;
	private int _xDelta;
	private int _yDelta;
	private int referencePoint;
	private static int TOLERANCE = 100;
	private boolean isInTolerance = true;
	private static int ANIMATION_TIME = 2000;

	public MyTouchListenerAdapter(DrawingView2 d){
		this.d = d;
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {

		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view. getLayoutParams();
			this._xDelta = X - lParams.leftMargin;
			this._yDelta = Y - lParams.topMargin;

			this.referencePoint = X;

			Log.v("ACTION_DOWN", "xDelta :" + this._xDelta);
			Log.v("ACTION_DOWN", "X :" + X);

			Log.v("ACTION_DOWN", "yDelta :" + this._yDelta);
			Log.v("ACTION_DOWN", "Y :" + Y);

			break;
		case MotionEvent.ACTION_UP:
			Log.v("ACTION_DOWN", "ACTION UP");
			if(this.isInTolerance){
				AnimationSet set = new AnimationSet(false);
				TranslateAnimation ta1 = new TranslateAnimation(
						Animation.ABSOLUTE, 0,
						Animation.ABSOLUTE, this.referencePoint - X,
						Animation.ABSOLUTE, 0.0f,
						Animation.ABSOLUTE, 0.0f);
				ta1.setDuration(ANIMATION_TIME);

				set.addAnimation(ta1);

				view.clearAnimation();
				view.startAnimation(set);
			}
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
			int marginOrigin = layoutParams.leftMargin;
			Log.v("ACTION_MOVE", "marginOrigin before :" + marginOrigin);
			layoutParams.leftMargin = X - this._xDelta;
			layoutParams.topMargin = Y - this._yDelta;

			Log.v("ACTION_MOVE", "marginOrigin after :" + layoutParams.leftMargin);
			layoutParams.rightMargin = -250;
			layoutParams.bottomMargin = -250;

			Log.v("ACTION_MOVE", "xDelta :" + this._xDelta);
			Log.v("ACTION_MOVE", "X :" + X);

			Log.v("ACTION_MOVE", "yDelta :" + this._yDelta);
			Log.v("ACTION_MOVE", "Y :" + Y);

			this.isInTolerance = this.isInTolerance(X);
			if(!this.isInTolerance){
				if(this.isGoingLeft(X)){

					AnimationSet set = new AnimationSet(false);
					AlphaAnimation aa = new AlphaAnimation(1f, 0f);
					aa.setDuration(ANIMATION_TIME);

					TranslateAnimation ta1 = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, -5.0f,
							Animation.ABSOLUTE, 0.0f,
							Animation.ABSOLUTE, 0.0f);
					ta1.setDuration(ANIMATION_TIME);

					set.addAnimation(aa);
					set.addAnimation(ta1);

					view.clearAnimation();
					view.startAnimation(set);
				}

				if(this.isGoingRight(X)){
					AnimationSet set = new AnimationSet(false);
					AlphaAnimation aa = new AlphaAnimation(1f, 0f);
					aa.setDuration(ANIMATION_TIME);

					TranslateAnimation ta1 = new TranslateAnimation(
							Animation.RELATIVE_TO_SELF, 0.0f,
							Animation.RELATIVE_TO_SELF, 5.0f,
							Animation.ABSOLUTE, 0.0f,
							Animation.ABSOLUTE, 0.0f);
					ta1.setDuration(ANIMATION_TIME);

					set.addAnimation(aa);
					set.addAnimation(ta1);

					view.clearAnimation();
					view.startAnimation(set);
				}
			}

			view.setLayoutParams(layoutParams);
			break;
		}
		view.invalidate();
		return true;
	}

	private boolean isInTolerance(int actualPoint){
		int subtraction = Math.abs((this.referencePoint - actualPoint));
		if(subtraction <= TOLERANCE){
			Log.v("isInTolerance", "IN TOLERANCE");
			return true;
		}
		Log.v("isInTolerance", "OFF TOLERANCE");
		return false;
	}

	private boolean isGoingLeft(int actualPoint){
		if(this.referencePoint > actualPoint){
			Log.v("isGoingLeft", "GOING LEFT");
			return true;
		}
		return false;
	}

	private boolean isGoingRight(int actualPoint){
		if(this.referencePoint < actualPoint){
			Log.v("isGoingRight", "GOING RIGHT");
			return true;
		}
		return false;
	}
}
