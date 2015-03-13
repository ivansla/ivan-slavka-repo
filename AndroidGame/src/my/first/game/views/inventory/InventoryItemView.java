package my.first.game.views.inventory;

import my.first.game.adapters.InventoryItemArrayAdapter;
import my.first.game.beans.EquipableItemBean;
import my.first.game.beans.ItemBean;
import my.first.game.controller.MainController;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class InventoryItemView extends LinearLayout{

	private static Activity activity = null;
	private ItemBean inventoryItem;
	private final InventoryItemArrayAdapter adapter;

	private MainController controller = MainController.getInstance();

	public InventoryItemView(Context context, AttributeSet attrs, Activity activity, InventoryItemArrayAdapter adapter){
		super(context, attrs);

		this.adapter = adapter;

		//if(InventoryItemView.activity == null){
		InventoryItemView.activity = activity;
		//}

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.inventory_item_view, this);

	}

	public void fillInventoryItemName(String itemName){
		TextView inventoryItemTextView = (TextView) this.findViewById(R.id.inventory_item);
		inventoryItemTextView.setText(itemName);

		this.inventoryItem = this.controller.getCharacterScreenBean().getCharacterBean().getInventory().getItemFromInventory(itemName);
		if(this.inventoryItem.isEquipable()){
			EquipableItemBean equipableItem = (EquipableItemBean) this.inventoryItem;

			if(equipableItem.isTempEquiped()){
				inventoryItemTextView.setTextColor(Color.RED);
			}
		}
	}

	public void showItemProperties(){

		if(this.inventoryItem.isEquipable()){
			EquipableItemBean equipableItem = (EquipableItemBean) this.inventoryItem;
			ItemPropertyView itemPropertyView = (ItemPropertyView) InventoryItemView.activity.findViewById(R.id.item_property_view);
			itemPropertyView.showItemProperty(equipableItem);
			Button toggleEquipButton = (Button) InventoryItemView.activity.findViewById(R.id.button_toggle_equip_item);
			toggleEquipButton.setEnabled(true);
			/*
			if(equipableItem.isTempEquiped()){
				toggleEquipButton.setText(R.string.button_unequip_item);
			} else {
				toggleEquipButton.setText(R.string.button_equip_item);
			}
			 */
		}

		this.adapter.notifyDataSetChanged();
	}
}
