package ivan.slavka;

import ivan.slavka.adapters.MyTouchListenerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

public class DrawingView2 extends View{

	//drawing path
	private Path drawPath;
	//drawing and canvas paint
	private Paint drawPaint, canvasPaint;
	//initial color
	private int paintColor = 0xFF660000;
	//canvas
	private Canvas drawCanvas;
	//canvas bitmap
	private Bitmap canvasBitmap;
	public int[] location = {0, 0};

	private int parentWidth;

	RelativeLayout.LayoutParams layoutParams;

	public DrawingView2(Context context, int width){
		super(context);

		this.parentWidth = width;
		this.setupDrawing();

		this.layoutParams = new RelativeLayout.LayoutParams(50, 50);
		this.layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

		//this.layoutParams.leftMargin = this.get
		this.setLayoutParams(this.layoutParams);

		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
		fadeIn.setDuration(5000);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
		//fadeOut.setStartOffset(1000);
		fadeOut.setDuration(5000);

		AnimationSet animation = new AnimationSet(false); //change to false
		//animation.addAnimation(fadeIn);
		animation.addAnimation(fadeOut);
		this.setAnimation(animation);

		this.getLocationInWindow(this.location);

		this.setOnTouchListener(new MyTouchListenerAdapter(this));
	}

	public void removeRestrictions(){
		this.layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, 0);
		this.layoutParams.leftMargin = (this.parentWidth / 2) - (this.getWidth() / 2);


		//this.layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
	}

	private void setupDrawing(){
		//get drawing area setup for interaction
		this.drawPath = new Path();
		this.drawPaint = new Paint();
		this.drawPaint.setColor(Color.RED);

		this.drawPaint.setAntiAlias(true);
		//this.drawPaint.setStrokeWidth(20);
		this.drawPaint.setStyle(Paint.Style.FILL);
		//this.drawPaint.setStrokeJoin(Paint.Join.ROUND);
		//this.drawPaint.setStrokeCap(Paint.Cap.ROUND);

		this.canvasPaint = new Paint(Paint.DITHER_FLAG);

		/*
		this.setOnTouchListener(new SwipeDismissTouchListener(this, null,
				new SwipeDismissTouchListener.DismissCallbacks() {
			@Override
			public void onDismiss(View view, Object token) {
				ViewGroup vg = (ViewGroup)(view.getParent());
				vg.removeView(view);
			}

			@Override
			public boolean canDismiss(Object token) {
				// TODO Auto-generated method stub
				return false;
			}
		}));
		 */
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		//view given size
		super.onSizeChanged(w, h, oldw, oldh);

		this.canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		this.drawCanvas = new Canvas(this.canvasBitmap);
		this.drawCanvas.drawColor(Color.TRANSPARENT);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//draw view
		canvas.drawBitmap(this.canvasBitmap, 0, 0, this.canvasPaint);
		canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, canvas.getHeight()/2, this.drawPaint);
	}
}
