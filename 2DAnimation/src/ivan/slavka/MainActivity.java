package ivan.slavka;

import ivan.slavka.interfaces.IViewSwitcher;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity implements IViewSwitcher{

	public final Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//LoggingUtils.addIgnoreClass(WonderBean.class);

		this.setContentView(new StartGameView(this, this));
	}

	@Override
	public void startGame(){
		this.setContentView(new GameView(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//this.getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
