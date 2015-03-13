package com.example.myfirstapp;

import my.first.game.adapters.LocationServiceArrayAdapter;
import my.first.game.adapters.OtherLocationConnectionArrayAdapter;
import my.first.game.controller.MainController;
import my.first.game.enums.LocationEnum;
import my.first.game.utils.ViewHandler;
import my.first.game.views.OtherLocationConnectionView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

public class LocationScreenActivity extends Activity {

	private MainController mainController = MainController.getInstance();

	private LocationServiceArrayAdapter locationServiceAdapter;
	private OtherLocationConnectionArrayAdapter otherLocationAdapter;
	private OtherLocationConnectionView otherLocationConnectionView;
	private ListView locationServicesView;

	private ViewHandler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_location_screen);

		this.locationServiceAdapter = new LocationServiceArrayAdapter(this, R.layout.location_service_view, this.mainController.getCharacterScreenBean().getCharacterBean().getCurrentLocation().getLocationServicesList());
		this.otherLocationConnectionView = (OtherLocationConnectionView) this.findViewById(R.id.other_location_connection_view);

		this.locationServicesView = (ListView) this.findViewById(R.id.location_service_list);
		this.locationServicesView.setAdapter(this.locationServiceAdapter);
		this.locationServicesView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
		//this.otherLocationConnectionListView.setAdapter(this.otherLocationAdapter);
		//this.otherLocationConnectionListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

		this.mHandler = new ViewHandler(){

			@Override
			public void handleMessage(Message inputMessage){
				LocationScreenActivity activity = (LocationScreenActivity) this.getActivity();
				activity.refreshLocationData();
			}
		};

		this.mHandler.setActivity(this);

		this.refreshLocationData();
	}

	private void refreshLocationData(){
		this.otherLocationConnectionView.setLocationBarButton(this.mainController.getPossibleLocationList());
		//List<String> myString = this.mainController.getPossibleLocationList();
		//this.otherLocationAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.location_screen, menu);
		return true;
	}

	public void goToOtherLocation(View view){
		Button otherLocationButton = (Button) view;
		this.mainController.goToOtherLocation(LocationEnum.toEnum(otherLocationButton.getText().toString()), this.mHandler);

		this.refreshLocationData();

		//Message completeMessage = this.mHandler.obtainMessage();
		//completeMessage.sendToTarget();
	}

	public void gather(View view){

	}

	public void exploreLocation(View view){

		Intent intent = new Intent(this, BattleScreenActivity.class);
		this.startActivity(intent);
	}

	public void openInventory(View view){
		Intent intent = new Intent(this, InventoryScreenActivity.class);
		this.startActivity(intent);
	}
}
