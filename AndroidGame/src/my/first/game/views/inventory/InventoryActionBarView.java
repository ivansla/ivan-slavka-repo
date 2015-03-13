package my.first.game.views.inventory;

import my.first.game.controller.MainController;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myfirstapp.R;

public class InventoryActionBarView extends LinearLayout{

	private MainController mainController = MainController.getInstance();
	private CharacterStatsView characterStatsView;

	public InventoryActionBarView(Context context, AttributeSet attrs){
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.inventory_action_bar, this);

		Button equipButton = (Button) this.findViewById(R.id.button_toggle_equip_item);
		equipButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				InventoryActionBarView.this.mainController.toggleEquipItem();
				InventoryActionBarView.this.characterStatsView.resetTextColor();
			}
		});
	}

	public void setCharacterStatsView(CharacterStatsView view){
		this.characterStatsView = view;
	}
}
