package my.first.game.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

public class ViewHandler extends Handler {

	private Activity activity;

	public ViewHandler(){
		super(Looper.getMainLooper());
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
