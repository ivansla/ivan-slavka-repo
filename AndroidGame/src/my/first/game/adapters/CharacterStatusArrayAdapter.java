package my.first.game.adapters;

import java.util.List;

import my.first.game.beans.AbstractSheetBean;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class CharacterStatusArrayAdapter extends ArrayAdapter<AbstractSheetBean>{

	private final Context context;
	private List<AbstractSheetBean> sheetBeanList;

	public CharacterStatusArrayAdapter(Context context, int resource, List<AbstractSheetBean> objects) {
		super(context, resource, objects);

		this.context = context;
		this.sheetBeanList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.battle_status_view, parent, false);

		AbstractSheetBean sheetBean = this.sheetBeanList.get(position);

		TextView nameTextView = (TextView) rowView.findViewById(R.id.battle_status_view_name_value);
		nameTextView.setText(sheetBean.getCharacterName());

		TextView currentHitPointsTextView = (TextView) rowView.findViewById(R.id.battle_status_view_health_current_value);
		currentHitPointsTextView.setText(String.valueOf(sheetBean.getCurrentHitPoints()));
		TextView totalHitPointsTextView = (TextView) rowView.findViewById(R.id.battle_status_view_health_total_value);
		totalHitPointsTextView.setText(String.valueOf(sheetBean.getTotalHitPoints()));

		TextView currentManaTextView = (TextView) rowView.findViewById(R.id.battle_status_view_mana_current_value);
		currentManaTextView.setText(String.valueOf(sheetBean.getCurrentMana()));
		TextView totalManaTextView = (TextView) rowView.findViewById(R.id.battle_status_view_mana_total_value);
		totalManaTextView.setText(String.valueOf(sheetBean.getTotalMana()));

		return rowView;
	}

}
