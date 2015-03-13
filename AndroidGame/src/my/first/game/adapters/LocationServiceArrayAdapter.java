package my.first.game.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.myfirstapp.R;

public class LocationServiceArrayAdapter extends ArrayAdapter<String>{

	private final Context context;
	private List<String> locationServiceList;

	public LocationServiceArrayAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);

		this.context = context;
		this.locationServiceList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.location_service_view, parent, false);

		Button locationServiceButton = (Button) rowView.findViewById(R.id.button_location_service);
		locationServiceButton.setText(this.locationServiceList.get(position));

		return rowView;
	}
}
