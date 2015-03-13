package my.first.game.views.inventory;

import my.first.game.enums.InventoryFilterEnum;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myfirstapp.R;

public class InventoryFilterView extends LinearLayout{

	public InventoryFilterView(Context context, AttributeSet attrs){
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.inventory_filter_view, this);

		Button filterButton;
		for(int i = 0; i < InventoryFilterEnum.values().length; i++){
			filterButton = (Button) this.findViewWithTag("inventory_filter_button_" + i);
			filterButton.setText(InventoryFilterEnum.values()[i].toString());
		}
	}
}
