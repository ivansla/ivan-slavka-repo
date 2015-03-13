package my.first.game.views;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.myfirstapp.R;

public class OtherLocationConnectionView extends RelativeLayout{

	public OtherLocationConnectionView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.other_location_connection_view, this);
	}

	public void setLocationBarButton(List<String> locationNameList){

		Button otherLocationButton;
		// Clean Byttons
		for(int i = 0; i < 4; i++){
			otherLocationButton = (Button) this.findViewWithTag("button_other_location_connection_" + i);
			otherLocationButton.setText("");
		}

		for(int i = 0; i < locationNameList.size(); i++){
			otherLocationButton = (Button) this.findViewWithTag("button_other_location_connection_" + i);
			otherLocationButton.setText(locationNameList.get(i));
		}

		for(int i = 0; i < 4; i++){
			otherLocationButton = (Button) this.findViewWithTag("button_other_location_connection_" + i);
			otherLocationButton.setVisibility(View.VISIBLE);
			if(otherLocationButton.getText().equals("")){
				otherLocationButton.setVisibility(View.GONE);
			}
		}
	}
}
