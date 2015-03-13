package my.first.game.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class CombatLogView extends ScrollView{

	public CombatLogView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.combat_log_view, this);
	}

	public void logEvent(String message){
		TextView combatLogTextView = (TextView) this.findViewById(R.id.combat_log);
		combatLogTextView.setText(message + "\n" + combatLogTextView.getText());
	}
}
