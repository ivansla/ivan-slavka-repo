package ivan.slavka.adapters;

import ivan.slavka.DrawingView2;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

public class MyAnimatorListenerAdapter extends AnimatorListenerAdapter{

	DrawingView2 d;

	public MyAnimatorListenerAdapter(DrawingView2 d){
		super();

		this.d = d;
	}

	@Override
	public void onAnimationEnd(Animator animation){
		this.d.removeRestrictions();
	}
}
