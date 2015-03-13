package test;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.opengltutorial.R;

public class MainActivity extends Activity{

	private Stage stage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_layout);

		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		this.stage = (Stage)this.findViewById(R.id.my_stage);
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.stage.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.stage.onResume();
	}
}
