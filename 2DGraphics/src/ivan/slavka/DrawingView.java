package ivan.slavka;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View{

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

	public DrawingView(Context context, AttributeSet attrs){
		super(context, attrs);
		this.setupDrawing();
	}

	private void setupDrawing(){
		//get drawing area setup for interaction
		this.drawPath = new Path();
		this.drawPaint = new Paint();
		this.drawPaint.setColor(this.paintColor);

		this.drawPaint.setAntiAlias(true);
		this.drawPaint.setStrokeWidth(20);
		this.drawPaint.setStyle(Paint.Style.STROKE);
		this.drawPaint.setStrokeJoin(Paint.Join.ROUND);
		this.drawPaint.setStrokeCap(Paint.Cap.ROUND);

		this.canvasPaint = new Paint(Paint.DITHER_FLAG);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		//view given size
		super.onSizeChanged(w, h, oldw, oldh);

		this.canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		this.drawCanvas = new Canvas(this.canvasBitmap);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//draw view
		canvas.drawBitmap(this.canvasBitmap, 0, 0, this.canvasPaint);
		canvas.drawPath(this.drawPath, this.drawPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//detect user touch
		float touchX = event.getX();
		float touchY = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.drawPath.moveTo(touchX, touchY);
			break;
		case MotionEvent.ACTION_MOVE:
			this.drawPath.lineTo(touchX, touchY);
			break;
		case MotionEvent.ACTION_UP:
			this.drawCanvas.drawPath(this.drawPath, this.drawPaint);
			this.drawPath.reset();
			break;
		default:
			return false;
		}

		this.invalidate();
		return true;
	}

	public void setColor(String newColor){
		//set color
		this.invalidate();

		this.paintColor = Color.parseColor(newColor);
		this.drawPaint.setColor(this.paintColor);
	}
}
