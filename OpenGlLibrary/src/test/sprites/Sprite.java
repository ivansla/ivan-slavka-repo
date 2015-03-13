package test.sprites;

import javax.microedition.khronos.opengles.GL10;

import test.animation.AnimationHandler;
import test.types.Point2D;

public abstract class Sprite {
	protected final Point2D pos;
	protected final Point2D scale;
	protected float rotation;
	protected float alpha;

	protected AnimationHandler anim = null;

	protected Sprite() {
		this.pos = new Point2D(0.0f, 0.0f);
		this.scale = new Point2D(1.0f, 1.0f);
		this.rotation = 0.0f;
		this.alpha = 1.0f;
	}

	/**
	 * Sets the animation handler for this {@code Sprite}. Once set, the animation
	 * will be played on this {@code Sprite} automatically, until set to {@code null}.
	 * @param animation	The animation handler, or {@code null} to remove animation.
	 */
	public final void setAnimation(AnimationHandler animation) {
		this.anim = animation;
	}

	public final AnimationHandler getAnimation() {
		return this.anim;
	}

	/**
	 * Advances the animation for this sprite. This method is called
	 * by {@link SpriteCollection} when the scene is being advanced.
	 * @param elapsedTime	The elapsed time since the previous frame.
	 */
	final void advanceAnimation(float elapsedTime) {
		if(this.anim == null)
			return;

		this.anim.advance(this, elapsedTime);
	}

	public final Point2D getPosition() { return this.pos; }
	public final Point2D getScale() { return this.scale; }

	public final void setScale(float scaleValue) {
		this.scale.x = this.scale.y = scaleValue;
	}

	public final void setScale(float scaleX, float scaleY) {
		this.scale.x = scaleX;
		this.scale.y = scaleY;
	}

	public final void setScale(Point2D sc) {
		if(sc != null)
			this.setScale(sc.x, sc.y);
	}

	public final void setPosition(Point2D position) {
		if(position != null)
			this.setPosition(position.x, position.y);
	}

	public void setPosition(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}

	public float getAlpha() {
		return this.alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public float getRotation() {
		return this.rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public final void rotate(float angle) {
		this.rotation += (angle % 360);
	}

	public final void move(float amountX, float amountY) {
		this.pos.x += amountX;
		this.pos.y += amountY;
	}

	public abstract void render(GL10 gl);
}
