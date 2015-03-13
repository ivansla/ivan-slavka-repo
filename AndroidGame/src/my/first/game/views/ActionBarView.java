package my.first.game.views;

import my.first.game.enums.AbilityEnum;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.myfirstapp.R;

public class ActionBarView extends RelativeLayout{

	public ActionBarView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.action_bar_view, this);
	}

	public void setActionBarButton(int position, AbilityEnum skill){
		Button actionBarButton = null;
		switch(position){
		case 0:
			actionBarButton = (Button) this.findViewById(R.id.button_action_bar_0);
			break;
		case 1:
			actionBarButton = (Button) this.findViewById(R.id.button_action_bar_1);
			break;
		case 2:
			actionBarButton = (Button) this.findViewById(R.id.button_action_bar_2);
			break;
		case 3:
			actionBarButton = (Button) this.findViewById(R.id.button_action_bar_3);
			break;
		case 4:
			actionBarButton = (Button) this.findViewById(R.id.button_action_bar_4);
			break;
		case 5:
			actionBarButton = (Button) this.findViewById(R.id.button_action_bar_5);
			break;
		case 6:
			actionBarButton = (Button) this.findViewById(R.id.button_action_bar_6);
			break;
		case 7:
			actionBarButton = (Button) this.findViewById(R.id.button_action_bar_7);
			break;
		default:
			break;
		}
		actionBarButton.setText(skill.toString());
	}
}
