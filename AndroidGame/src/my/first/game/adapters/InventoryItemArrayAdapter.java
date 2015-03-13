package my.first.game.adapters;

import java.util.List;

import my.first.game.views.inventory.InventoryItemView;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class InventoryItemArrayAdapter extends ArrayAdapter<String>{

	private final Context context;
	private List<String> inventoryItemList;

	private Activity activity;

	public InventoryItemArrayAdapter(Context context, int resource, List<String> objects, Activity activity) {
		super(context, resource, objects);

		this.context = context;
		this.inventoryItemList = objects;
		this.activity = activity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		InventoryItemView rowView = new InventoryItemView(this.context, null, this.activity, this);

		rowView.fillInventoryItemName(this.inventoryItemList.get(position));
		rowView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((InventoryItemView) v).showItemProperties();
			}
		});

		return rowView;
	}
}
