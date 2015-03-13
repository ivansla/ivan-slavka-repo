package ivan.slavka.interpolators;

import android.animation.TimeInterpolator;


public class QuadraticTimeInterpolator implements TimeInterpolator{

	@Override
	public float getInterpolation(float input) {
		return input * input * input;
	}

}
