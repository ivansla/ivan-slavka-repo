package my.first.game.views;

import my.first.game.enums.LocationServiceEnum;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.myfirstapp.R;

public class LocationServiceView extends RelativeLayout{

	public LocationServiceView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.location_service_view, this);
	}

	public void setActionBarButton(int position, LocationServiceEnum locationService){
		Button locationServicButton = (Button) this.findViewById(R.id.button_location_service);
		locationServicButton.setText(locationService.toString());
	}
}
