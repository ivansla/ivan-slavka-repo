package test.animation;

import test.sprites.Sprite;

public interface AnimationHandler {
	public void advance(Sprite sprite, float elapsedTime);
}
