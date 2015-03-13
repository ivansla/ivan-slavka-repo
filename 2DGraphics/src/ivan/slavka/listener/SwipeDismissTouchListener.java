package ivan.slavka.listener;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

public class SwipeDismissTouchListener implements View.OnTouchListener {
	// Cached ViewConfiguration and system-wide constant values
	private int mSlop;
	private int mMinFlingVelocity;
	private int mMaxFlingVelocity;
	private long mAnimationTime;

	// Fixed properties
	private View mView;
	private DismissCallbacks mCallbacks;
	private int mViewWidth = 1; // 1 and not 0 to prevent dividing by zero

	// Transient properties
	private float mDownX;
	private float mDownY;
	private boolean mSwiping;
	private int mSwipingSlop;
	private Object mToken;
	private VelocityTracker mVelocityTracker;
	private float mTranslationX;

	/**
	 * The callback interface used by {@link SwipeDismissTouchListener} to inform its client
	 * about a successful dismissal of the view for which it was created.
	 */
	public interface DismissCallbacks {
		/**
		 * Called to determine whether the view can be dismissed.
		 */
		boolean canDismiss(Object token);

		/**
		 * Called when the user has indicated they she would like to dismiss the view.
		 *
		 * @param view  The originating {@link View} to be dismissed.
		 * @param token The optional token passed to this object's constructor.
		 */
		void onDismiss(View view, Object token);
	}

	/**
	 * Constructs a new swipe-to-dismiss touch listener for the given view.
	 *
	 * @param view     The view to make dismissable.
	 * @param token    An optional token/cookie object to be passed through to the callback.
	 * @param callbacks The callback to trigger when the user has indicated that she would like to
	 *                 dismiss this view.
	 */
	public SwipeDismissTouchListener(View view, Object token, DismissCallbacks callbacks) {
		ViewConfiguration vc = ViewConfiguration.get(view.getContext());
		this.mSlop = vc.getScaledTouchSlop();
		this.mMinFlingVelocity = vc.getScaledMinimumFlingVelocity() * 16;
		this.mMaxFlingVelocity = vc.getScaledMaximumFlingVelocity();
		this.mAnimationTime = view.getContext().getResources().getInteger(
				android.R.integer.config_shortAnimTime);
		this.mView = view;
		this.mToken = token;
		this.mCallbacks = callbacks;
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		Log.v("SwipeDismissTouchListener", "OnTouch");
		// offset because the view is translated during swipe
		motionEvent.offsetLocation(this.mTranslationX, 0);

		if (this.mViewWidth < 2) {
			this.mViewWidth = this.mView.getWidth();
		}

		switch (motionEvent.getActionMasked()) {
		case MotionEvent.ACTION_DOWN: {
			Log.v("SwipeDismissTouchListener", "ACTION_DOWN");
			// TODO: ensure this is a finger, and set a flag
			this.mDownX = motionEvent.getRawX();
			this.mDownY = motionEvent.getRawY();
			if (this.mCallbacks.canDismiss(this.mToken)) {
				this.mVelocityTracker = VelocityTracker.obtain();
				this.mVelocityTracker.addMovement(motionEvent);
			}
			return false;
		}

		case MotionEvent.ACTION_UP: {
			Log.v("SwipeDismissTouchListener", "ACTION_UP");
			if (this.mVelocityTracker == null) {
				break;
			}

			float deltaX = motionEvent.getRawX() - this.mDownX;
			this.mVelocityTracker.addMovement(motionEvent);
			this.mVelocityTracker.computeCurrentVelocity(1000);
			float velocityX = this.mVelocityTracker.getXVelocity();
			float absVelocityX = Math.abs(velocityX);
			float absVelocityY = Math.abs(this.mVelocityTracker.getYVelocity());
			boolean dismiss = false;
			boolean dismissRight = false;
			if (Math.abs(deltaX) > this.mViewWidth / 2 && this.mSwiping) {
				dismiss = true;
				dismissRight = deltaX > 0;
			} else if (this.mMinFlingVelocity <= absVelocityX && absVelocityX <= this.mMaxFlingVelocity
					&& absVelocityY < absVelocityX
					&& absVelocityY < absVelocityX && this.mSwiping) {
				// dismiss only if flinging in the same direction as dragging
				dismiss = (velocityX < 0) == (deltaX < 0);
				dismissRight = this.mVelocityTracker.getXVelocity() > 0;
			}
			if (dismiss) {
				// dismiss
				this.mView.animate()
				.translationX(dismissRight ? this.mViewWidth : -this.mViewWidth)
				.alpha(0)
				.setDuration(this.mAnimationTime)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						SwipeDismissTouchListener.this.performDismiss();
					}
				});
			} else if (this.mSwiping) {
				// cancel
				this.mView.animate()
				.translationX(0)
				.alpha(1)
				.setDuration(this.mAnimationTime)
				.setListener(null);
			}
			this.mVelocityTracker.recycle();
			this.mVelocityTracker = null;
			this.mTranslationX = 0;
			this.mDownX = 0;
			this.mDownY = 0;
			this.mSwiping = false;
			break;
		}

		case MotionEvent.ACTION_CANCEL: {
			Log.v("SwipeDismissTouchListener", "ACTION_CANCEL");
			if (this.mVelocityTracker == null) {
				break;
			}

			this.mView.animate()
			.translationX(0)
			.alpha(1)
			.setDuration(this.mAnimationTime)
			.setListener(null);
			this.mVelocityTracker.recycle();
			this.mVelocityTracker = null;
			this.mTranslationX = 0;
			this.mDownX = 0;
			this.mDownY = 0;
			this.mSwiping = false;
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			Log.v("SwipeDismissTouchListener", "ACTION_MOVE");
			if (this.mVelocityTracker == null) {
				break;
			}

			this.mVelocityTracker.addMovement(motionEvent);
			float deltaX = motionEvent.getRawX() - this.mDownX;
			float deltaY = motionEvent.getRawY() - this.mDownY;
			if (Math.abs(deltaX) > this.mSlop && Math.abs(deltaY) < Math.abs(deltaX) / 2) {
				this.mSwiping = true;
				this.mSwipingSlop = (deltaX > 0 ? this.mSlop : -this.mSlop);
				this.mView.getParent().requestDisallowInterceptTouchEvent(true);

				// Cancel listview's touch
				MotionEvent cancelEvent = MotionEvent.obtain(motionEvent);
				cancelEvent.setAction(MotionEvent.ACTION_CANCEL |
						(motionEvent.getActionIndex() <<
								MotionEvent.ACTION_POINTER_INDEX_SHIFT));
				this.mView.onTouchEvent(cancelEvent);
				cancelEvent.recycle();
			}

			if (this.mSwiping) {
				this.mTranslationX = deltaX;
				this.mView.setTranslationX(deltaX - this.mSwipingSlop);
				// TODO: use an ease-out interpolator or such
				this.mView.setAlpha(Math.max(0f, Math.min(1f,
						1f - 2f * Math.abs(deltaX) / this.mViewWidth)));
				return true;
			}
			break;
		}
		}
		return false;
	}

	private void performDismiss() {
		// Animate the dismissed view to zero-height and then fire the dismiss callback.
		// This triggers layout on each animation frame; in the future we may want to do something
		// smarter and more performant.

		final ViewGroup.LayoutParams lp = this.mView.getLayoutParams();
		final int originalHeight = this.mView.getHeight();

		ValueAnimator animator = ValueAnimator.ofInt(originalHeight, 1).setDuration(this.mAnimationTime);

		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				SwipeDismissTouchListener.this.mCallbacks.onDismiss(SwipeDismissTouchListener.this.mView, SwipeDismissTouchListener.this.mToken);
				// Reset view presentation
				SwipeDismissTouchListener.this.mView.setAlpha(1f);
				SwipeDismissTouchListener.this.mView.setTranslationX(0);
				lp.height = originalHeight;
				SwipeDismissTouchListener.this.mView.setLayoutParams(lp);
			}
		});

		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				lp.height = (Integer) valueAnimator.getAnimatedValue();
				SwipeDismissTouchListener.this.mView.setLayoutParams(lp);
			}
		});

		animator.start();
	}
}