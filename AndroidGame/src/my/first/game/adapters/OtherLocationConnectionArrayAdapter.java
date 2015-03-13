package my.first.game.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.myfirstapp.R;

public class OtherLocationConnectionArrayAdapter extends ArrayAdapter<String>{

	private final Context context;
	private List<String> otherLocationConnectionList;

	public OtherLocationConnectionArrayAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);

		this.context = context;
		this.otherLocationConnectionList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.other_location_connection_view, parent, false);

		//Button locationServiceButton = (Button) rowView.findViewById(R.id.button_other_location_connection);
		//locationServiceButton.setText(this.otherLocationConnectionList.get(position));

		return rowView;
	}
}
