package ivan.slavka;

import ivan.slavka.adapters.MyAnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class MainActivity extends Activity{

	private DrawingView2 mImageView;
	//private ViewGroup mRrootLayout;
	private int _xDelta;
	private int _yDelta;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Display display = this.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		// Creating a new RelativeLayout
		RelativeLayout relativeLayout = new RelativeLayout(this);

		// Defining the RelativeLayout layout parameters.
		// In this case I want to fill its parent
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		/*
        // Defining the layout parameters of the TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);

        // Setting the parameters on the TextView
        tv.setLayoutParams(lp);

        // Adding the TextView to the RelativeLayout as a child
        relativeLayout.addView(tv);
		 */
		// Setting the RelativeLayout as our content view
		this.setContentView(relativeLayout, rlp);

		this.mImageView = new DrawingView2(this, size.x);
		relativeLayout.addView(this.mImageView);

		ObjectAnimator ballAnim = ObjectAnimator.ofFloat(this.mImageView, "y", 200);
		ballAnim.addListener(new MyAnimatorListenerAdapter(this.mImageView));
		ballAnim.setDuration(3000);
		//ballAnim.setRepeatCount(ValueAnimator.INFINITE);
		//ballAnim.setRepeatMode(ValueAnimator.REVERSE);
		//ballAnim.setInterpolator(new QuadraticTimeInterpolator());
		ballAnim.start();

		//this.mRrootLayout = (ViewGroup) this.findViewById(R.id.root);
		//this.mImageView = (DrawingView2) this.findViewById(R.id.drawing);

		//RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
		//this.mImageView.setLayoutParams(this.findViewById(android.R.id.content).getLayoutParams());
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//this.getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
