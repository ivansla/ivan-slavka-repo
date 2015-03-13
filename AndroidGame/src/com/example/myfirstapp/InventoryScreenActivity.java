package com.example.myfirstapp;

import my.first.game.adapters.InventoryItemArrayAdapter;
import my.first.game.beans.ItemBean;
import my.first.game.controller.MainController;
import my.first.game.views.inventory.CharacterStatsView;
import my.first.game.views.inventory.InventoryActionBarView;
import my.first.game.views.inventory.ItemPropertyView;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

public class InventoryScreenActivity extends Activity {

	private MainController mainController = MainController.getInstance();

	private InventoryItemArrayAdapter inventoryArrayAdapter;
	private ListView inventoryItemListView;
	private CharacterStatsView characterStatsView;
	private ItemPropertyView itemPropertyView;
	private InventoryActionBarView inventoryActionBarView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_inventory_screen);

		this.inventoryArrayAdapter = new InventoryItemArrayAdapter(this, R.layout.activity_inventory_screen, this.mainController.getCharacterScreenBean().getCharacterBean().getInventory().getActualInventory(),this);

		this.inventoryItemListView = (ListView)this.findViewById(R.id.inventory_item_list);
		this.inventoryItemListView.setAdapter(this.inventoryArrayAdapter);
		this.inventoryItemListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

		this.characterStatsView = (CharacterStatsView) this.findViewById(R.id.character_stats);
		this.characterStatsView.showCharacterStatsData(null);

		this.itemPropertyView = (ItemPropertyView) this.findViewById(R.id.item_property_view);
		this.itemPropertyView.setCharacterStatsView(this.characterStatsView);

		this.inventoryActionBarView = (InventoryActionBarView) this.findViewById(R.id.inventory_action_bar);
		this.inventoryActionBarView.setCharacterStatsView(this.characterStatsView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.inventory_screen, menu);
		return true;
	}

	public void toggleFilter(View view){

		Button button = (Button)view;
		Log.v("InventoryScreenActivity", button.getText().toString());
	}

	public void toggleEquip(View view){
		ItemPropertyView itemPropertyView = (ItemPropertyView) this.findViewById(R.id.item_property_view);
		ItemBean item =itemPropertyView.getSelectedItem();
		if(item.isEquipable()){
			this.mainController.toggleEquipItem();
		}
	}

	public void inventoryDone(View view){
		Button inventoryDoneButton = (Button)view;
		inventoryDoneButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NavUtils.navigateUpFromSameTask(InventoryScreenActivity.this);
			}
		});

		this.mainController.resetTempEquip();
	}
}
