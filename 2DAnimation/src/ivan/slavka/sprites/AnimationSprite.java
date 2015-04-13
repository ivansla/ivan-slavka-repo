package ivan.slavka.sprites;

import ivan.slavka.interfaces.IEconomyProgress;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimationSprite {

	private static int BASIC_SIZE = 64;

	private int size;
	private Bitmap bitmap;
	private float x;
	private float y;
	private int numberOfTransitions;
	private int actualTransition;
	private Rect src, dst;

	private IEconomyProgress economyController;
	private SpriteManager spriteManager;

	public AnimationSprite(){}

	public AnimationSprite(IEconomyProgress economyController){
		this.economyController = economyController;
		//this.spriteManager = spriteManager;
		//this.bitmap = this.spriteManager.getGeneralSprite(animationSpriteEnum);
		this.src = new Rect();
		this.dst = new Rect();
		this.size = BASIC_SIZE;
	}

	public AnimationSprite(IEconomyProgress economyController, int size){
		this.economyController = economyController;
		//this.spriteManager = spriteManager;
		//this.bitmap = this.spriteManager.getGeneralSprite(animationSpriteEnum);
		this.src = new Rect();
		this.dst = new Rect();

		this.size = size;
	}

	public void prepareSprite(Bitmap bmp){
		this.bitmap = bmp;
		this.actualTransition = 1;
		this.numberOfTransitions = (this.bitmap.getWidth() / this.size);
	}

	public void setCoordinates(int x, int y){
		this.x = x;
		this.y = y;

		this.dst.left = x;
		this.dst.top = y;
		this.dst.right = x + this.size;
		this.dst.bottom = y + this.size;
	}

	private void update(){
		this.src.left = this.actualTransition * this.size;
		this.src.top = 0;
		this.src.right = this.src.left + this.size;
		this.src.bottom = this.src.top + this.size;
	}

	public void onDraw(Canvas canvas) {
		this.update();
		//canvas.drawRect(this.position.x, this.position.y, this.position.x + WIDTH_SIZE, this.position.y + HEIGHT_SIZE, this.drawPaint);
		canvas.drawBitmap(this.bitmap, this.src, this.dst, null);
	}
}
