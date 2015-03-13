package com.example.myfirstapp;

import my.first.game.controller.MainController;
import my.first.game.enums.CharacterClassEnum;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	public static final String CHARACTER_NAME = "game.CHARACTER_NAME";
	public static final String CHARACTER_CLASS = "game.CHARACTER_CLASS";

	private MainController controller = MainController.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		Spinner spinner = (Spinner) this.findViewById(R.id.character_class_spinner);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		/*
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.character_class_array, android.R.layout.simple_spinner_item);
		 */
		ArrayAdapter<CharacterClassEnum> adapter = new ArrayAdapter<CharacterClassEnum>(this, android.R.layout.simple_spinner_dropdown_item, CharacterClassEnum.values());

		// Specify the layout to use when the list of choices appears
		//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void sendMessage(View view) {
		Intent intent = new Intent(this, LocationScreenActivity.class);
		EditText editText = (EditText) this.findViewById(R.id.edit_message);
		String characterName = editText.getText().toString();
		//intent.putExtra(CHARACTER_NAME, characterName);

		Spinner spinner = (Spinner) this.findViewById(R.id.character_class_spinner);
		CharacterClassEnum characterClass = (CharacterClassEnum) spinner.getSelectedItem();
		//intent.putExtra(CHARACTER_CLASS, characterClass);

		this.controller.createCharacter(characterName, characterClass);
		this.startActivity(intent);
	}
}
